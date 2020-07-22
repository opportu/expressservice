package com.controller;

import com.common.Result;
import com.pojo.FeedbackInfo;
import com.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 反馈模块
 */
@RestController
@RequestMapping("/feedback")
public class FeenbackController {

    /**
     * 日志管理工具
     */
    Logger logger = LoggerFactory.getLogger(FeenbackController.class);

    @Autowired
    private FeedbackService feedbackService;

    /**
     * 测试接口
     *
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test success!";
    }

    /**
     * 添加反馈接口(面向用户人员)
     *
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/addFeedback", method = RequestMethod.POST)
    public Result addFeedback(@RequestBody Map<String, Object> paramMap) {
        Result results = new Result();
        try {
            feedbackService.addFeedback(paramMap);
            results.setIsSuccess(true);
            logger.info("[增加反馈成功]，addFeedback success!");
            return results;
        } catch (Exception e) {
            results.setIsSuccess(false);
            logger.error("[增加反馈失败]，addFeedback failed!", e);
            return results;
        }
    }

    /**
     * 编辑反馈接口（面向管理人员）
     *
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/editFeedbackInfo", method = RequestMethod.POST)
    public Result editFeedbackInfo(@RequestBody Map<String, Object> paramMap) {
        Result results = new Result();
        try {
            feedbackService.editFeedbackInfo(paramMap);
            results.setIsSuccess(true);
            logger.info("[编辑反馈成功]，editFeedback success!");
            return results;
        } catch (Exception e) {
            results.setIsSuccess(false);
            logger.error("[编辑反馈失败]，editFeedback failed!", e);
            return results;
        }
    }

    /**
     * 查看反馈列表接口（面向管理人员）
     *
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/queryFeedbackInfo", method = RequestMethod.POST)
    public Result queryFeedbackInfo(@RequestBody Map<String, Object> paramMap) {
        Result results = new Result();
        try {
            List<FeedbackInfo> list = feedbackService.queryFeedbackInfo(paramMap);
            results.setData(list);
            results.setIsSuccess(true);
            logger.info("[查询反馈列表成功]，queryFeedbackInfo success!");
            return results;
        } catch (Exception e) {
            results.setIsSuccess(false);
            logger.error("[查询反馈列表失败]，queryFeedbackInfo failed!", e);
            return results;
        }
    }

    /**
     * 查看某一具体反馈接口（面向管理人员）
     *
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/queryDTFeedbackInfo", method = RequestMethod.GET)
    public Result queryDTFeedbackInfo(@RequestBody Map<String, Object> paramMap) {
        Result results = new Result();
        try {
            feedbackService.queryDTFeedbackInfo(paramMap);
            results.setIsSuccess(true);
            logger.info("[查询某一具体反馈成功]，queryDTFeedbackInfo success!");
            return results;
        } catch (Exception e) {
            results.setIsSuccess(false);
            logger.error("[查询某一具体反馈失败]，queryDTFeedbackInfo failed!", e);
            return results;
        }
    }

}
