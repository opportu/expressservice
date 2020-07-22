package com.service;

import com.pojo.FeedbackInfo;
import java.util.List;
import java.util.Map;

/**
 * 反馈模块业务层接口
 */
public interface FeedbackService {

    /**
     * 增加反馈
     * @param paramMap
     * @return
     */
    int addFeedback(Map<String, Object> paramMap) throws Exception;

    /**
     * 编辑反馈
     * @param paramMap
     * @return
     */
    int editFeedbackInfo(Map<String, Object> paramMap) throws Exception;

    /**
     * 查询反馈列表
     * @param paramMap
     * @return
     */
    List<FeedbackInfo> queryFeedbackInfo(Map<String, Object> paramMap) throws Exception;

    /**
     * 查询某一具体反馈
     * @param paramMap
     * @return
     */
    FeedbackInfo queryDTFeedbackInfo(Map<String, Object> paramMap) throws Exception;

}
