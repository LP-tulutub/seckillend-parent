package com.seckillend.service;

import com.seckillend.enums.RedisTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class VerifyImageService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 生成验证图
     * @param imageId
     * @return
     */
    public BufferedImage createVerifyCode(String imageId){

        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(rdm);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode, 8, 24);
        g.dispose();
        //存到redis
        int rnd = calc(verifyCode);
        this.redisTemplate.opsForValue().set(imageId, rnd, RedisTime.VERIFICATION_TIME, TimeUnit.SECONDS);
        //输出图片
        return image;
    }

    /**
     * 判断用户是否提交了正确的验证码
     * @param verifyCode
     * @return
     */
    public boolean checkVerifyCode(int verifyCode, String imageId) {
        Integer codeOld = (Integer) this.redisTemplate.opsForValue().get(imageId);
        this.redisTemplate.delete(imageId);
        if(codeOld == null || codeOld - verifyCode != 0 ) {
            return false;
        }
        return true;
    }

    /**
     * 随机生成计算式
     */
    private static char[] ops = new char[] {'+', '-', '*'};
    private static String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        String exp = ""+ num1 + op1 + num2 + op2 + num3;
        return exp;
    }

    /**
     * 算出随机生成计算式的答案
     * @param exp
     * @return
     */
    private static int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            Integer catch1 = (Integer)engine.eval(exp);
            return catch1.intValue();
        }catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
