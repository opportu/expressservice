package com.service;


import com.common.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * 微信小程序负载均衡service
 */
@FeignClient(name = "wechatapp-service", fallbackFactory = ServerExceptionFallback.class)
public interface WeChatService {

    /**
     * 获取用户信息接口
     *
     * @param paramMap 传入存储结果参数集合
     * @return 返回Result类型
     */
    @RequestMapping(value = "/appGetBasicInfo", method = RequestMethod.POST)
    Result getBasicInfo(@RequestBody HashMap<String, Object> paramMap);

    /**
     * 用户密码登录接口
     *
     * @param paramMap 传入参数存储集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/appUserLogin", method = RequestMethod.POST)
    Result loginUser(@RequestBody HashMap<String, Object> paramMap);

    /**
     * 向数据库中添加快递的详尽信息,发单
     *
     * @param paramMap 传入参数存储集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/appWriteInfo", method = RequestMethod.POST)
    Result writeInfo(@RequestBody HashMap<String, Object> paramMap);

    /**
     * 展示所有用户的有效订单信息
     *
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/appShowAllOrderInfoList", method = RequestMethod.POST)
    Result showAllOrderInfoList(@RequestBody HashMap<String, Object> paramMap);
}
