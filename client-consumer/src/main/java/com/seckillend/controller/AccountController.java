package com.seckillend.controller;

import com.seckillend.enums.RedisTime;
import com.seckillend.feign.AccountFeign;
import com.seckillend.pojo.Account;
import com.seckillend.result.ResultLP;
import com.seckillend.service.LoginService;
import com.seckillend.service.VerifyImageService;
import com.seckillend.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;

import static com.seckillend.enums.ResultStatus.CODE_FAIL;
import static com.seckillend.enums.ResultStatus.RESIGETER_FAIL;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountFeign accountFeign;
    @Autowired
    private LoginService loginService;
    @Autowired
    private VerifyImageService verifyImageService;

    /**
     * 登陆
     * @param account
     * @param req
     * @param rep
     * @return
     */
    @PostMapping("/login")
    public String login(Account account, HttpServletRequest req, HttpServletResponse rep){
        Object obj = this.accountFeign.login(account);
        if (obj!=null && !obj.equals("")){
            String cookiesId = this.loginService.setLoginInRedis(obj);
            CookieUtils.setCookie(req, rep, "TT_TOKEN", cookiesId, (int) RedisTime.LOGIN_TIME);

            return "redirect:/goods/to_list";
        }
        return "error";
    }

    /**
     * 注册请求
     * @param req
     * @param account 注册用户信息
     * @param salt 加密字段
     * @param verifyCode 注册码信息
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public ResultLP<String> register(HttpServletRequest req, Account account, String salt, int verifyCode){
        ResultLP<String> result = ResultLP.build();
        String imageKey = CookieUtils.getCookieValue(req, "registerKey");
        boolean checkCode = this.verifyImageService.checkVerifyCode(verifyCode, imageKey);

        if (!checkCode){
            //200002 验证码不正确
            result.withError(CODE_FAIL.getCode(),CODE_FAIL.getMessage());
            return result;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("nickname", account.getUsername());
        map.put("password", account.getPassword());
        map.put("salt", salt);

        ResultLP<Object> resultLP = this.accountFeign.register(map);
        Integer val = (Integer) resultLP.getData();
        if(val == null || val!=1 || val==2){
            result.withError(RESIGETER_FAIL.getCode(),RESIGETER_FAIL.getMessage());
            return result;
        }
        return result;
    }


}
