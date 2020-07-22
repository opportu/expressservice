package com.controller;

import com.common.Result;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户模块请求负载均衡
 */
@RestController
@RequestMapping("/userInfo")
public class UserRibbonController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;
    /*
    @RequestMapping("/Consumer")
    public String helloWorld(String s){
        System.out.println("传入的值为："+s);
        //第一种调用方式:直接调用：不经过注册中心那服务列表，直接访问的service
        //String forObject = new RestTemplate().getForObject("http://localhost:8071/Hello/World?s=" + s, String.class);

        //第二种调用方式
        //根据服务名 获取服务列表 根据算法选取某个服务 并访问某个服务的网络位置。
        //ServiceInstance serviceInstance = loadBalancerClient.choose("EUREKA-SERVICE");
        //String forObject = new RestTemplate().getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/Hello/World?s="+s,String.class);

        //第三种调用方式 需要restTemplate注入的方式:常用第三种调用方式
        String forObject = restTemplate.getForObject("http://EUREKA-SERVICE/Hello/World?s=" + s, String.class);
        return forObject;
    }*/

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello(@RequestParam("hello") String hello) {
        return userService.hello1(hello);
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.POST)
    public String hello(@RequestBody Map<String, Object> paramMap) {
        return userService.hello2(paramMap);
    }

    /**
     * 用户密码登录接口
     *
     * @param paramMap 传入参数存储集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/loginUserWithPwd", method = RequestMethod.POST)
    public Result loginUser(@RequestBody HashMap<String, Object> paramMap) {
        return userService.loginUser(paramMap);
    }

    /**
     * 用户短信验证码登录接口（手机号+短信验证码登录）
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/loginUserWithCode", method = RequestMethod.POST)
    public Result loginWithCode(@RequestBody HashMap<String, Object> paramMap) {
        return userService.loginWithCode(paramMap);
    }

    /**
     * 用户忘记密码登录接口
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/forgetUserPwd", method = RequestMethod.POST)
    public Result forgetPwd(@RequestBody HashMap<String, Object> paramMap) {
        return userService.forgetPwd(paramMap);
    }


    /**
     * 用户重置密码接口
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public Result resetPwd(@RequestBody HashMap<String, Object> paramMap){
        return userService.resetPwd(paramMap);
    }

    /**
     * 用户获取验证码接口
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/getCode", method = RequestMethod.POST)
    public Result getCode(@RequestBody HashMap<String, Object> paramMap) {
        return userService.getCode(paramMap);
    }

    /**
     * 用户注册接口
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Result addUser(@RequestBody HashMap<String, Object> paramMap) {
        return userService.addUser(paramMap);
    }

    /**
     * 用户注销接口
     *
     * @param paramMap 传入存储参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public Result deleteUser(@RequestBody HashMap<String, Object> paramMap) {
        return userService.deleteUser(paramMap);
    }

    /**
     * 获取用户信息接口
     *
     * @param paramMap 传入存储结果参数集合
     * @return 返回Result类型
     */
    @RequestMapping(value = "/getBasicInfo", method = RequestMethod.POST)
    public Result getBasicUser(@RequestBody HashMap<String, Object> paramMap) {
        return userService.getBasicInfo(paramMap);
    }

    /**
     * 用户实名制接口
     *
     * @param paramMap 传入存储参数集合
     * @return 返回Result接口
     */
    @RequestMapping(value = "/realUser", method = RequestMethod.POST)
    public Result realUser(@RequestBody HashMap<String, Object> paramMap) {
        return userService.realUser(paramMap);
    }

    /**
     * 获取用户取货地址
     *
     * @param paramMap 传入存储参数集合
     * @return 返回Result接口
     */
    @RequestMapping(value = "/getUserAddress", method = RequestMethod.POST)
    public Result getAddress(@RequestBody HashMap<String, Object> paramMap) {
        return userService.getAddress(paramMap);
    }

}