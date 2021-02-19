package com.seckillend.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.seckillend.feign.GoodsFeign;
import com.seckillend.pojo.SecKillGoods;
import com.seckillend.result.ResultLP;
import com.seckillend.service.UserService;
import com.seckillend.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Resource
    private GoodsFeign goodsFeign;
    @Autowired
    private UserService userService;

    /**
     * 商品列表页面
     * @param model
     * @param req
     * @return
     */
    @GetMapping("/to_list")
    @SentinelResource("goods-list")
    public String to_list(Model model, HttpServletRequest req){
        String token = CookieUtils.getCookieValue(req, "TT_TOKEN");
        if (token != null && !token.equals("")){
            LinkedHashMap hashMap = userService.getUser(token);
            model.addAttribute("userId", ((HashMap) hashMap.get("data")).get("id"));
            model.addAttribute("username", ((HashMap) hashMap.get("data")).get("nickname"));
        }
        // 查询秒杀的商品
        ResultLP<List<SecKillGoods>> resultLP = this.goodsFeign.goodsList(1, 10);
        model.addAttribute("goodsList", resultLP.getData());
        return "goods_list";
    }

    /**
     * 秒杀页面
     * @param model
     * @param id 当前商品id
     * @param req
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id, HttpServletRequest req){
        String token = CookieUtils.getCookieValue(req, "TT_TOKEN");
        if (token != null && !token.equals("")){
            LinkedHashMap hashMap = userService.getUser(token);
            model.addAttribute("username", ((HashMap) hashMap.get("data")).get("nickname"));
        }
        // 查询详细
        ResultLP<SecKillGoods> resultLP = this.goodsFeign.goodsDetail(id);
        SecKillGoods secKillGoods = resultLP.getData();
        model.addAttribute("goods", secKillGoods);

        // 秒杀状态
        long startAt = secKillGoods.getStartDate().getTime();
        long endAt = secKillGoods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) { // 秒杀还没开始，倒计时
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){ // 秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else { // 秒杀进行中
            miaoshaStatus = 1;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }

}
