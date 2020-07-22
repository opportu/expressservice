package com.controller;

import com.common.Result;
import com.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 微信小程序登录负载均衡
 */
@RestController
@RequestMapping("/weChatApp")
public class WeChatRibbonController {

    @Autowired
    private WeChatService weChatService;

    /**
     * 获取用户信息接口
     *
     * @param paramMap 传入存储结果参数集合
     * @return 返回Result类型
     */
    @RequestMapping(value = "/appGetBasicInfo", method = RequestMethod.POST)
    public Result getBasicUser(@RequestBody HashMap<String, Object> paramMap) {
        return weChatService.getBasicInfo(paramMap);
    }

    /**
     * 用户密码登录接口
     *
     * @param paramMap 传入参数存储集合
     * @return 返回Result对象
     */
    @RequestMapping(value ="/appUserLogin", method = RequestMethod.POST)
    public Result loginUser(@RequestBody HashMap<String, Object> paramMap) {
        return weChatService.loginUser(paramMap);
    }

    /**
     * 展示所有用户的有效订单信息
     *
     * @param paramMap
     * @return
     */
    @PostMapping("/appShowAllOrderInfoList")
    public Result showAllOrderInfoList(@RequestBody HashMap<String, Object> paramMap) {
        return weChatService.showAllOrderInfoList(paramMap);
    }

    /**
     * 向数据库中添加快递的详尽信息,发单
     *
     * @param paramMap 传入参数存储集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/appWriteInfo", method = RequestMethod.POST)
     public Result writeInfo(@RequestBody HashMap<String, Object> paramMap) {
        return weChatService.writeInfo(paramMap);
    };



}
