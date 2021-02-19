package com.seckillend.service;

import com.seckillend.pojo.Account;
import com.seckillend.result.ResultLP;

import java.util.Map;

public interface AccountService {

    public ResultLP<Object> login(Account account);

    public ResultLP<Object> register(Map map);
}
