package com.seckillend.controller;

import com.seckillend.pojo.Account;
import com.seckillend.result.ResultLP;
import com.seckillend.service.AccountService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    
    @Resource
    private AccountService accountServiceImpl;

    @PostMapping("/login")
    public ResultLP<Object> login(@RequestBody Account account){
        return this.accountServiceImpl.login(account);
    }

    @Transactional
    @PostMapping("/register")
    public ResultLP<Object> register(@RequestBody Map map){
        return this.accountServiceImpl.register(map);
    }


}
