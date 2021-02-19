package com.seckillend.controller;

import com.seckillend.pojo.SeckillMessage;
import com.seckillend.result.ResultLP;
import com.seckillend.service.MiaoshaMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2020-08-20
 */
@RestController
@RequestMapping("//miaosha-message")
public class MiaoshaMessageController {

    @Autowired
    private MiaoshaMessageService miaoshaMessageServiceImpl;

    @GetMapping("/list/{userId}")
    public ResultLP<List<SeckillMessage>> messageList(@PathVariable Long userId){
        ResultLP<List<SeckillMessage>> resultLP = ResultLP.build();
        resultLP.setData(this.miaoshaMessageServiceImpl.messageList(userId));
        return resultLP;
    }
}
