package com.controller;

/**
 * 微信小程序模块
 */

import com.basic.UserCommonStatus;
import com.common.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.OrderInfo;
import com.pojo.User;
import com.service.OrderService;
import com.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WeChatController {

    /**
     * 小程序管理日志工具
     */
    protected static Logger logger = Logger.getLogger(WeChatController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/appGetCode")
    public Result getCode(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        try {
            logger.info("进入测试方法##########");
            result.setData(userService.getCode(paramMap));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("没有进入测试方法%%%%%%%%");
            result.setData(null);
            return result;
        }
    }

    @PostMapping(value = "/appAddUser")
    public Result addUser(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        try {
            logger.info("进入测试方法 => 添加用户");
            int flag = userService.addUser(paramMap);
            if (flag == 200) {
                paramMap.get("password");
                result.setData(paramMap);
                result.setIsSuccess(true);

            }
            if (flag == 404) {
                result.setData(null);
                result.setIsSuccess(false);
                result.setMessage("该手机号码已经进行注册");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("没有进入测试方法 => 添加用户");
            result.setData(null);
            return result;
        }
    }

    @RequestMapping(value = "/appGetBasicInfo", method = RequestMethod.POST)
    public Result getBasicInformation(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        try {
            logger.info("进入测试方法 => 获取用户基本信息");
            Map<String, Object> map = userService.getBasicUser(paramMap);
            if (map == null) {
                result.setCode(UserCommonStatus.getCodeByName("ERROR"));
                result.setData(null);
                result.setMessage("用户信息为空");
                result.setIsSuccess(false);
            } else {
                result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
                result.setData(map);
                result.setMessage("获取用户信息成功");
                result.setIsSuccess(true);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取不到用户信息，请检查过程");
            result.setIsSuccess(false);
            return result;
        }
    }

    @PostMapping(value = "/appUserLogin")
    public Result userLogin(@RequestBody HashMap<String, Object> paramMap) throws Exception {
        Result result = new Result();
        try {
            User user = userService.loginUserWithPwd(paramMap);
            if (user.getTelephone() != null) {
                result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
                result.setIsSuccess(true);
                result.setMessage("用户电话号码 + 密码 => 登录成功!");
                result.setData(user);
            } else {
                result.setCode(UserCommonStatus.getCodeByName("ERROR"));
                result.setIsSuccess(true);
                result.setMessage("用户电话号码 + 密码 => 登录失败!");
                result.setData(null);
            }
            return result;
        } catch (Exception e) {
            result.setIsSuccess(false);
            result.setCode(UserCommonStatus.getCodeByName("FAIL"));
            result.setMessage("后端服务器，没有查询到用户信息，请进行注册或其他操作");
            return result;
        }
    }

    @PostMapping("/appWriteInfo")
    public Result writeInfo(@RequestBody HashMap<String, Object> paramMap) throws Exception {
        Result result = new Result();
        try {
            logger.info("进入测试方法 => 填写订单信息");
            int orderID = orderService.add(paramMap);
            if (orderID < 10) {
                result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
                result.setData(orderID);
                result.setMessage("请求成功");
                result.setIsSuccess(true);
            } else {
                result.setCode(UserCommonStatus.getCodeByName("ERROR"));
                result.setData(0);
                result.setMessage("请求失败");
                result.setIsSuccess(false);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("订单填写失败，请检查是否填写正确！");
            result.setIsSuccess(false);
            return result;
        }
    }

    @RequestMapping(value = "/appShowAllOrderInfoList", method = RequestMethod.POST)
    public Result showAllOrderInfoList(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        try {
            logger.info("进入微信端【查询用户所有发单信息开始】 showAllUserSendOrdersList start");
            PageHelper.startPage(Integer.valueOf(paramMap.get("pageCount").toString()),
                    Integer.valueOf(paramMap.get("countPerPage").toString()));
            List<OrderInfo> list = orderService.findall();
            PageInfo pageInfo = new PageInfo(list, 10);
            result.setIsSuccess(true);
            logger.info("进入微信端【查询所有订单成功】 showAllOrderInfoList success");
            result.setData(pageInfo);
            return result;
        } catch (Exception e) {
            result.setIsSuccess(false);
            logger.error("进入微信端【查询所有订单成功】 showAllOrderInfoList failed", e);
            return result;
        }
    }
}
