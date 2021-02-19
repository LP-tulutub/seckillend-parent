package com.seckillend.controller;



import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.seckillend.feign.MessageFeign;
import com.seckillend.pojo.SeckillMessage;
import com.seckillend.pojo.SendMessage;
import com.seckillend.result.ResultLP;
import com.seckillend.service.SecKillSendService;
import com.seckillend.service.SecKillService;
import com.seckillend.service.UserService;
import com.seckillend.service.VerifyImageService;
import com.seckillend.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.seckillend.enums.ResultStatus.*;

@Slf4j
@Controller
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageFeign messageFeign;
    @Autowired
    private SecKillService secKillService;
    @Autowired
    private SecKillSendService secKillSendService;
    @Autowired
    private UserService userService;
    @Autowired
    private VerifyImageService verifyImageService;

    public static HashMap<Long, Boolean> localOverMap = new HashMap<>();

    /**
     * 消息中心
     * @param model
     * @param userId
     * @return
     */
    @GetMapping("/list")
    @SentinelResource("message-list")
    public String list(Model model, @PathParam("userId") Long userId){

        ResultLP<List<SeckillMessage>> listResultLP = this.messageFeign.messageList(userId);
        // 如果 ResultLP 出现错误 ResultStatus，如断路
        if (listResultLP.getStatus().getCode() != 1){
            model.addAttribute("message", listResultLP.getMessage());
            return "error";
        }
        List<SeckillMessage> data = listResultLP.getData();
        // 如果 ResultLP 中内容是空的
        if (data == null || data.toString().equals("[]")) return "message_list";
        model.addAttribute("data", data);
        model.addAttribute("userid", data.get(0).getUserId());
        return "message_list";
    }

    /**
     * 秒杀
     * @param path 隐藏的地址
     * @param map 一次性接收所有内容
     * @param req
     * @return
     */
    @RequestMapping("/{path}/do_seckill")
    @ResponseBody
    @SentinelResource("message-do-seckill")
    public ResultLP<Object> secKill(@PathVariable("path") String path, @RequestBody HashMap<String, String> map, HttpServletRequest req){
        ResultLP<Object> result = ResultLP.build();
        // 检查隐藏的接口是否正确
        if (!secKillService.checkPath(path)){
            result.withError(PATH_ERROR.getCode(), PATH_ERROR.getMessage());
            return result;
        }
        // 检查验证码是否正确
        String msKey = CookieUtils.getCookieValue(req, "msKey");
        if (msKey == null || msKey.equals("")){
            result.withError(USER_NOT_EXIST.getCode(), USER_NOT_EXIST.getMessage());
            return result;
        }
        if (!verifyImageService.checkVerifyCode(Integer.parseInt(map.get("verifyCode")), msKey)){
            result.withError(CODE_FAIL.getCode(), CODE_FAIL.getMessage());
            return result;
        }
        // 开始业务
        String token = CookieUtils.getCookieValue(req, "TT_TOKEN");
        LinkedHashMap<String, Object> linkedHashMap = userService.getUser(token);
        Long goodsId = Long.parseLong(map.get("gId"));
        final String userId = String.valueOf(((LinkedHashMap) linkedHashMap.get("data")).get("id"));
        final String secKillId = userId + goodsId;
        //1.是否已经秒杀到
        boolean bl = this.secKillService.checkSecKill(secKillId);
        if (bl){
            result.withError(REPEATE_MIAOSHA.getCode(), REPEATE_MIAOSHA.getMessage());
            return result;
        }
        //2.当商品已经被秒杀完时，内存标记，减少redis访问
        boolean bl2 = localOverMap.containsKey(goodsId);
        if (bl2) {
            result.withError(MIAO_SHA_OVER.getCode(), MIAO_SHA_OVER.getMessage());
            return result;
        }
        //3.预见库存，步骤2需要此步骤
        boolean bl3 = this.secKillService.checkRepertory(goodsId);
        if (!bl3){
            localOverMap.put(goodsId, true);
            result.withError(MIAO_SHA_OVER.getCode(), MIAO_SHA_OVER.getMessage());
            return result;
        }
        //4.发送信息
        BigDecimal gMPrice = BigDecimal.valueOf(Double.parseDouble(map.get("gMPrice")));
        SendMessage sendMessage = new SendMessage(Long.parseLong(userId), goodsId, map.get("gName"), gMPrice);
        Message msg = MessageBuilder.withPayload(sendMessage).build();
        this.secKillSendService.sendMessage(msg);
        return result;
    }

}
