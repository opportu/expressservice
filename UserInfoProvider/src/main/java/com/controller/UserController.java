package com.controller;

/**
 * 用户模块
 */

import com.basic.UserCommonStatus;
import com.common.Result;
import com.pojo.User;
import com.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/userInfo")
@SessionAttributes("user")
public class UserController {

    /**
     * 日志管理工具
     */
    protected static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1(@RequestParam("hello") String hello) {
        try {
            System.out.println("hello ==" + hello);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("userID", 1);
            return userService.getBasicUser(map).get("nickname").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "-111111";
        }
    }


    @RequestMapping(value = "/hello2", method = RequestMethod.POST)
    public String hello2(@RequestBody Map<String, Object> paramMap) {
        try {
            System.out.println("hello2");
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("userID", 1);
            return userService.getBasicUser(map).get("nickname").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "-111111";
        }
    }

    /**
     * 用户密码登录接口
     *
     * @param paramMap 传入参数存储集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/loginUserWithPwd", method = RequestMethod.POST)
    public Result loginUser(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        logger.info("[用户密码登录]开始------");
        try {
            User user = userService.loginUserWithPwd(paramMap);
            //SessionHandler.save(user, httpSession);
            if (user != null) {
                result.setIsSuccess(true);
                result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
                result.setMessage("用户密码登录成功！");
                result.setData(userService.getAllInfo(paramMap));
                logger.info("[用户密码登录] => loginUser success!");
                return result;
            } else {
                logger.warn("[用户密码登录失败] => loginUser failed!");
                result.setCode(UserCommonStatus.getCodeByName("NOT_EXIST"));
                result.setMessage("用户手机号码不存在");
                return result;
            }
        } catch (Exception e) {
            logger.error("[用户密码登录] => loginUser failed!" + e);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            result.setIsSuccess(false);
            result.setMessage("用户密码登录失败");
            return result;
        }
    }

    /**
     * 用户短信验证码登录接口（手机号+短信验证码登录）
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/loginUserWithCode", method = RequestMethod.POST)
    public Result loginWithCode(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        try {
            User user = userService.loginWithCode(paramMap);
            //SessionHandler.save(user, httpSession);
            result.setIsSuccess(true);
            result.setData(userService.getAllInfo(paramMap));
            result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
            result.setMessage("用户短信验证码登录成功！");
            logger.info("[用户短信验证码登录] => loginWithCode success!");
            return result;
        } catch (Exception e) {
            result.setIsSuccess(false);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            result.setMessage("用户短信验证码登录失败！");
            logger.error("[用户短信验证码登录] => loginWithCode failed!", e);
            return result;
        }

    }

    /**
     * 用户忘记密码登录接口
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/forgetUserPwd", method = RequestMethod.POST)
    public Result forgetPwd(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        logger.info("[用户忘记密码登录]开始-----");
        try {
            User user = userService.loginWithCode(paramMap);
            //SessionHandler.save(user, httpSession);
            logger.info("[用户忘记密码登录] forgetPwd success!");
            result.setIsSuccess(true);
            result.setData(userService.getAllInfo(paramMap));
            result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
            result.setMessage("用户忘记密码登录成功！");
            return result;
        } catch (Exception e) {
            logger.info("[用户忘记密码登录] forgetPwd failed!", e);
            result.setIsSuccess(false);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            result.setMessage("用户忘记密码登录失败！");
            return result;
        }
    }

    /**
     * 用户重置密码接口
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public Result resetPwd(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        logger.info("[用户重置密码]开始-----");
        try {
            userService.resetPwd(paramMap);
            logger.info("[用户重置密码] resetPwd success!");
            result.setMessage("用户重置密码成功！");
            result.setIsSuccess(true);
            result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
            return result;
        } catch (Exception e) {
            logger.error("[用户重置密码] resetPwd failed!", e);
            result.setMessage("用户重置密码失败！");
            result.setIsSuccess(false);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            return result;
        }
    }

    /**
     * 用户获取验证码接口
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/getCode", method = RequestMethod.POST)
    public Result getCode(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        logger.info("[用户获取验证码]开始-----");
        try {
            logger.info("[用户获取验证码] getCode success!");
            result.setMessage("用户获取验证码成功！");
            result.setData(userService.getCode(paramMap));
            result.setIsSuccess(true);
            result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
            return result;
        } catch (Exception e) {
            logger.error("[用户获取验证码] getCode failed!", e);
            result.setMessage("用户获取验证码失败！");
            result.setIsSuccess(false);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            return result;
        }
    }

    /**
     * 用户注册接口
     *
     * @param paramMap 传入储存参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Result addUser(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        logger.info("[用户注册]开始------");
        try {
            if (userService.addUser(paramMap) == 200) {
                result.setIsSuccess(true);
                result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
                result.setData(userService.getAllInfo(paramMap));
                result.setMessage("用户注册成功！");
                logger.info("[用户注册] => addUser success!");
            } else if (userService.addUser(paramMap) == 404) {
                result.setIsSuccess(false);
                logger.error("[用户注册] => addUser failed!");
                result.setCode(UserCommonStatus.getCodeByName("REPEAT"));
                result.setMessage("用户注册失败，该手机号已被注册！");
                logger.error("该手机号已经被注册过了");
            }
            return result;
        } catch (Exception e) {
            result.setIsSuccess(false);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            result.setMessage("用户注册失败！");
            logger.error("[用户注册] => addUser failed!", e);
            return result;
        }
    }

    /**
     * 用户注销接口
     *
     * @param paramMap 传入存储参数集合
     * @return 返回Result对象
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public Result deleteUser(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        try {
            userService.deleteUser(paramMap);
            logger.info("[用户注销] => deleteUser success!");
            result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
            result.setIsSuccess(true);
            result.setMessage("用户注销成功");
            return result;
        } catch (Exception e) {
            logger.error("[用户注销] => deleteUser failed!", e);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            result.setIsSuccess(false);
            result.setMessage("用户注销失败");
            return result;
        }
    }

    /**
     * 获取用户信息接口
     *
     * @param paramMap 传入存储结果参数集合
     * @return 返回Result类型
     */
    @RequestMapping(value = "/getBasicInfo", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result getBasicUser(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        logger.info("[用户信息获取] 开始-----");
        try {
            Map<String, Object> map = userService.getBasicUser(paramMap);
            result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
            logger.info("[用户信息获取] getBasicUser success!");
            result.setIsSuccess(true);
            result.setMessage("获取用户信息成功！");
            result.setData(map);
            return result;
        } catch (Exception e) {
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            logger.error("[用户信息获取] getBasicUser failed!", e);
            result.setIsSuccess(false);
            result.setMessage("获取用户信息失败！");
            return result;
        }
    }

    /**
     * 用户实名制接口
     *
     * @param paramMap 传入存储参数集合
     * @return 返回Result接口
     */
    @RequestMapping(value = "/realUser", method = RequestMethod.POST)
    public Result realUser(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        logger.info("[用户实名制] 开始-----");
        try {
            userService.realUser(paramMap);
            result.setIsSuccess(true);
            result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
            result.setMessage("用户实名制成功");
            logger.info("[用户实名制] => realUser success！");
            return result;
        } catch (Exception e) {
            result.setIsSuccess(false);
            logger.error("[用户实名制] => realUser failed!", e);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            result.setMessage("[用户实名制] => realUser failed!");
            return result;
        }
    }

    /**
     * 获取用户取货地址
     *
     * @param paramMap 传入存储参数集合
     * @return 返回Result接口
     */
    @RequestMapping(value = "/getUserAddress", method = RequestMethod.POST)
    public Result getAddress(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();
        logger.info("[获取用户默认地址] 开始-----");
        try {
            result.setData(userService.getAddress(paramMap));
            result.setIsSuccess(true);
            result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
            result.setMessage("获取用户地址成功");
            logger.info("[获取用户地址成功] => getUserAddress success！");
            return result;
        } catch (Exception e) {
            result.setIsSuccess(false);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            logger.error("[获取用户地址失败] => getUserAddress failed！", e);
            result.setMessage("[获取用户地址失败] => getUserAddress failed!");
            return result;
        }
    }
}


