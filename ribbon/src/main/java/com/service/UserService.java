package com.service;

import com.common.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户端负载均衡service
 */
@FeignClient(name = "user-service")
public interface UserService {

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    String hello1(@RequestParam("hello") String hello);

    @RequestMapping(value = "/hello2", method = RequestMethod.POST)
    String hello2(@RequestBody Map<String, Object> paramMap);

    /**
     * 用户密码登录接口
     *
     * @param paramMap 传入参数存储集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/loginUserWithPwd", method = RequestMethod.POST)
    Result loginUser(@RequestBody HashMap<String, Object> paramMap);

    /**
     * 用户短信验证码登录接口（手机号+短信验证码登录）
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/loginUserWithCode", method = RequestMethod.POST)
    Result loginWithCode(@RequestBody HashMap<String, Object> paramMap);

    /**
     * 用户忘记密码登录接口
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/forgetUserPwd", method = RequestMethod.POST)
    public Result forgetPwd(@RequestBody HashMap<String, Object> paramMap);

    /**
     * 用户重置密码接口
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public Result resetPwd(@RequestBody HashMap<String, Object> paramMap);

    /**
     * 用户获取验证码接口
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/getCode", method = RequestMethod.POST)
    public Result getCode(@RequestBody HashMap<String, Object> paramMap);

    /**
     * 用户注册接口
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Result addUser(@RequestBody HashMap<String, Object> paramMap);

    /**
     * 用户注销接口
     *
     * @param paramMap 传入存储参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public Result deleteUser(@RequestBody HashMap<String, Object> paramMap);

    /**
     * 获取用户信息接口
     *
     * @param paramMap 传入存储结果参数集合
     * @return 返回Result类型
     */
    @RequestMapping(value = "/getBasicInfo", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result getBasicInfo(@RequestBody HashMap<String, Object> paramMap);

    /**
     * 用户实名制接口
     *
     * @param paramMap 传入存储参数集合
     * @return 返回Result接口
     */
    @RequestMapping(value = "/realUser", method = RequestMethod.POST)
    public Result realUser(@RequestBody HashMap<String, Object> paramMap);

    /**
     * 获取用户取货地址
     *
     * @param paramMap 传入存储参数集合
     * @return 返回Result接口
     */
    @RequestMapping(value = "/getUserAddress", method = RequestMethod.POST)
    public Result getAddress(@RequestBody HashMap<String, Object> paramMap);
}