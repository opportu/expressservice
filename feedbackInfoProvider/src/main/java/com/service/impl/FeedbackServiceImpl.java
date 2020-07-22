package com.service.impl;
/**
 * 反馈模块业务层处理
 */

import com.basic.FeedbackStatus;
import com.mapper.FeedbackMapper;
import com.pojo.FeedbackInfo;
import com.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 添加反馈
     *
     * @param paramMap
     * @return
     */
    @Override
    public int addFeedback(Map<String, Object> paramMap) throws Exception {
        FeedbackInfo feedbackInfo = new FeedbackInfo();
        feedbackInfo.setFeedbackTypeID(Integer.valueOf(paramMap.get("feedbackTypeID").toString()));
        feedbackInfo.setFeedbackUserID(Integer.valueOf(paramMap.get("feedbackUserID").toString()));
        feedbackInfo.setContent(String.valueOf(paramMap.get("content").toString()));
        feedbackInfo.setTelephone(String.valueOf(paramMap.get("telephone").toString()));
        feedbackInfo.setRespondentID(Integer.valueOf(paramMap.get("respondentID").toString()));
        feedbackInfo.setStatusID(FeedbackStatus.NOT_SOLVED);
        return feedbackMapper.addFeedback(feedbackInfo);
    }

    /**
     * 编辑反馈
     *
     * @param paramMap
     * @return
     */
    @Override
    public int editFeedbackInfo(Map<String, Object> paramMap) throws Exception {
        FeedbackInfo feedbackInfo = new FeedbackInfo();
        feedbackInfo.setFeedbackInfoID(Integer.valueOf(paramMap.get("feedbackInfoID").toString()));
        feedbackInfo.setCreator(String.valueOf(paramMap.get("creator").toString()));
        feedbackInfo.setStatusID(FeedbackStatus.SOLVED);
        return feedbackMapper.editFeedbackInfo(feedbackInfo);
    }

    /**
     * 查询反馈列表
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<FeedbackInfo> queryFeedbackInfo(Map<String, Object> paramMap) throws Exception {
        FeedbackInfo feedbackInfo = new FeedbackInfo();
        feedbackInfo.setContent(String.valueOf(paramMap.get("content").toString()));
        feedbackInfo.setCreator(String.valueOf(paramMap.get("creator").toString()));
        feedbackInfo.setStatusID(Integer.valueOf(paramMap.get("statusID").toString()));
        feedbackInfo.setTelephone(String.valueOf(paramMap.get("telephone").toString()));
        int page = Integer.parseInt(paramMap.get("page").toString());
        int pageCount = Integer.parseInt(paramMap.get("pageCount").toString());
        return feedbackMapper.queryFeedbackInfo(page, pageCount, feedbackInfo);
    }

    /**
     * 查询具体某一反馈
     *
     * @param paramMap
     * @return
     * @throws Exception
     */
    public FeedbackInfo queryDTFeedbackInfo(Map<String, Object> paramMap) throws Exception {

        int feedbackInfoID = Integer.valueOf(paramMap.get("feedbackInfoID").toString());
        int statusID = Integer.valueOf(paramMap.get("statusID").toString());
        return feedbackMapper.queryDTFeedbackInfo(feedbackInfoID);
    }
}
