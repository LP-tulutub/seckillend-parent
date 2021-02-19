package com.seckillend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seckillend.entity.Admin;
import com.seckillend.entity.MiaoshaUser;
import com.seckillend.enums.ResultStatus;
import com.seckillend.mapper.AdminMapper;
import com.seckillend.mapper.MiaoshaUserMapper;
import com.seckillend.pojo.Account;
import com.seckillend.result.ResultLP;
import com.seckillend.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AdminMapper adminMapper;
    @Resource
    private MiaoshaUserMapper miaoshaUserMapper;

    @Override
    public ResultLP<Object> login(Account account) {
        ResultLP<Object> resultLP = ResultLP.build();
        // 用户和管理员登录
        if (!account.getUsername().equals("root")){
            QueryWrapper<MiaoshaUser> wrapper = new QueryWrapper<>();
            wrapper.eq("nickname", account.getUsername());
            wrapper.eq("password", account.getPassword());
            MiaoshaUser user = this.miaoshaUserMapper.selectOne(wrapper);
            if (user != null) resultLP.setData(user);
        }else {
            QueryWrapper<Admin> wrapper = new QueryWrapper<>();
            wrapper.eq("name", account.getUsername());
            wrapper.eq("password", account.getPassword());
            Admin admin = this.adminMapper.selectOne(wrapper);
            if (admin != null) resultLP.setData(admin);
        }

        return resultLP;
    }

    @Override
    public ResultLP<Object> register(Map map) {
        //判断是否已经注册
        QueryWrapper<MiaoshaUser> wrapper = new QueryWrapper<>();
        wrapper.eq("nickname", map.get("nickname"));
        if (this.miaoshaUserMapper.selectOne(wrapper) != null) return ResultLP.error(ResultStatus.RESIGETER_FAIL);
        //没有注册就注册
        ResultLP<Object> resultLP = ResultLP.build();
        MiaoshaUser user = new MiaoshaUser();
        user.setNickname((String) map.get("nickname"));
        user.setPassword((String) map.get("password"));
        user.setSalt((String) map.get("salt"));
        resultLP.setData(this.miaoshaUserMapper.insert(user));
        return resultLP;
    }
}
