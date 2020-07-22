package com.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 反馈模块pojo
 */
public class FeedbackInfo implements Serializable {

    public long getFeedbackUserID() {
        return feedbackUserID;
    }

    public void setFeedbackUserID(long feedbackUserID) {
        this.feedbackUserID = feedbackUserID;
    }

    /**
     * 反馈用户ID
     */
    private long feedbackUserID;

    /**
     * 反馈类型ID
     */
    private long feedbackTypeID;

    /**
     * 反馈信息内容
     */
    private String content;

    /**
     * 反馈用户电话
     */
    private String telephone;

    /**
     * 被举报者ID
     */
    private long respondentID;

    /**
     * 反馈信息ID
     */
    private long feedbackInfoID;


    /**
     * 创建人
     */
    private String creator;

    /**
     * 状态ID
     */
    private long statusID;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 反馈类型描述
     */
    private String descp;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public long getFeedbackTypeID() {
        return feedbackTypeID;
    }

    public void setFeedbackTypeID(long feedbackTypeID) {
        this.feedbackTypeID = feedbackTypeID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getRespondentID() {
        return respondentID;
    }

    public void setRespondentID(long respondentID) {
        this.respondentID = respondentID;
    }

    public long getFeedbackInfoID() {
        return feedbackInfoID;
    }

    public void setFeedbackInfoID(long feedbackInfoID) {
        this.feedbackInfoID = feedbackInfoID;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public long getStatusID() {
        return statusID;
    }

    public void setStatusID(long statusID) {
        this.statusID = statusID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    @Override
    public String toString() {
        return "种类为：" + feedbackUserID ;
    }
}


