package com.controller;

import com.basic.UserCommonStatus;
import com.common.Result;
import com.pojo.Url;
import com.service.UrlManagerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目提供的所有接口的相对地址的接口管理
 */
@RestController
@RequestMapping("/urlManager")
public class UrlManagerController {

    /**
     * 日志管理工具
     */
    private static Logger logger = Logger.getLogger(UrlManagerController.class);

    @Autowired
    private UrlManagerService urlManagerService;

    @RequestMapping("/addUrl")
    public Result addUrl(@RequestBody HashMap<String ,Object> paramMap) {
        Result result = new Result();
        logger.info("[用户登录]开始------");
        try {
            urlManagerService.addUrl(paramMap);
            result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
            result.setIsSuccess(true);
            result.setMessage("用户登录成功！");
            logger.info("[用户登录] => addUrl success!");
            return result;
        } catch (Exception e) {
            logger.error("[用户登录] => addUrl failed!" + e);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            result.setIsSuccess(false);
            result.setMessage("用户登录失败");
            return result;
        }
    }

    @RequestMapping("/deleteUrl")
    public Result deleteUrl(@RequestBody HashMap<String ,Object> paramMap) {
        Result result = new Result();
        logger.info("[用户登录]开始------");
        try {
            urlManagerService.deleteUrl(paramMap);
            result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
            result.setIsSuccess(true);
            result.setMessage("用户登录成功！");
            logger.info("[用户登录] => deleteUrl success!");
            return result;
        } catch (Exception e) {
            logger.error("[用户登录] => deleteUrl failed!" + e);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            result.setIsSuccess(false);
            result.setMessage("用户登录失败");
            return result;
        }
    }

    @RequestMapping("/updateUrl")
    public Result updateUrl(@RequestBody HashMap<String ,Object> paramMap) {
        Result result = new Result();
        logger.info("[用户登录]开始------");
        try {
            urlManagerService.update(paramMap);
            result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
            result.setIsSuccess(true);
            result.setMessage("用户登录成功！");
            logger.info("[用户登录] => updateUrl success!");
            return result;
        } catch (Exception e) {
            logger.error("[用户登录] => updateUrl failed!" + e);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            result.setIsSuccess(false);
            result.setMessage("用户登录失败");
            return result;
        }
    }

    @RequestMapping("/selectUrl")
    public Result selectUrl() {
        Result result = new Result();
        logger.info("[用户登录]开始------");
        try {
            ArrayList<Url> list = urlManagerService.selectUrl();
            result.setCode(UserCommonStatus.getCodeByName("SUCCESS"));
            result.setIsSuccess(true);
            result.setData(list);
            result.setMessage("用户登录成功！");
            logger.info("[用户登录] => selectUrl success!");
            return result;
        } catch (Exception e) {
            logger.error("[用户登录] => selectUrl failed!" + e);
            result.setCode(UserCommonStatus.getCodeByName("ERROR"));
            result.setIsSuccess(false);
            result.setMessage("用户登录失败");
            return result;
        }
    }

}
