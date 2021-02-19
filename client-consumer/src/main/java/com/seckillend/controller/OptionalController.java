package com.seckillend.controller;


import cn.hutool.core.util.IdUtil;
import com.seckillend.enums.RedisTime;
import com.seckillend.result.ResultLP;
import com.seckillend.service.SecKillService;
import com.seckillend.service.VerifyImageService;
import com.seckillend.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import static com.seckillend.enums.ResultStatus.EXCEPTION;
import static com.seckillend.enums.ResultStatus.SYSTEM_DB_ERROR;

@Slf4j
@Controller
@RequestMapping("/optional")
public class OptionalController {

    @Value("${redis.key.image}")
    private String image;
    @Value("${redis.key.seckillImg}")
    private String seckillImg;
    @Autowired
    private VerifyImageService verifyImageService;
    @Autowired
    private SecKillService secKillService;

    /**
     * 验证码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/verifyCodeRegister")
    @ResponseBody
    public ResultLP<String> verifyCodeRegister(HttpServletRequest request, HttpServletResponse response){

        ResultLP<String> result = ResultLP.build();
        String imageId = this.image + IdUtil.randomUUID();
        CookieUtils.setCookie(request, response, "registerKey", imageId, (int) RedisTime.VERIFICATION_TIME);
        try {
            OutputStream out = response.getOutputStream();
            BufferedImage image = this.verifyImageService.createVerifyCode(imageId);
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
            return result;
        } catch (Exception e) {
            //-1 系统异常
            log.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
            result.withError(EXCEPTION.getCode(), EXCEPTION.getMessage());
            return result;
        }
    }

    @RequestMapping("/verifyCodeSecPath")
    @ResponseBody
    public ResultLP<Object> verifyCodeSecPath(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成隐藏接口
        String hidePath = secKillService.createHidePath();
        // 如果生成失败返回错误
        if (hidePath == null){
            ResultLP<Object> result = ResultLP.build();
            result.withError(SYSTEM_DB_ERROR.getCode(), SYSTEM_DB_ERROR.getMessage());
            return result;
        }
        // 生成成功把 path 放入 result 中
        ResultLP<Object> result = ResultLP.build(hidePath);
        String imageId = this.seckillImg + IdUtil.randomUUID();
        CookieUtils.setCookie(request, response, "msKey", imageId, (int) RedisTime.KILL_VERIFICATION_TIME);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            BufferedImage image = this.verifyImageService.createVerifyCode(imageId);
            ImageIO.write(image, "png", out);
            byte[] bytes = out.toByteArray();
            result.setData(bytes);
            out.flush();
            return result;
        } catch (Exception e) {
            //-1 系统异常
            log.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
            result.withError(EXCEPTION.getCode(), EXCEPTION.getMessage());
            return result;
        }finally {
            out.close();
        }



    }



}
