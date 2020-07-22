package com.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 反馈模块POJO
 */
public class Comment implements Serializable {
    //评论id
    private long commentID;
    //订单id
    private long orderID;

    //评论者id
    private long commentatorID;

    //被评论者id
    private long ownerID;

    //评论内容
    private String createContent;

    //评论时间
    private Date createTime;

    //评论删除时间
    private Date deleteTime;

    //评论者自己评论是否已读,未读为0，已读为1
    private int commentatorCommentStatus;

    //被评论者评论是否已读,未读为0，已读为1
    private int ownerCommentStatus;

    //评论是否删除，未删除0，删除为1
    private int deleteOrKeep;

    public long getCommentID() {
        return commentID;
    }

    public void setCommentID(long commentID) {
        this.commentID = commentID;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getCommentatorID() {
        return commentatorID;
    }

    public void setCommentatorID(long commentatorID) {
        this.commentatorID = commentatorID;
    }

    public String getCreateContent() {
        return createContent;
    }

    public void setCreateContent(String createContent) {
        this.createContent = createContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public int getCommentatorCommentStatus() {
        return commentatorCommentStatus;
    }

    public void setCommentatorCommentStatus(int commentatorCommentStatus) {
        this.commentatorCommentStatus = commentatorCommentStatus;
    }

    public int getOwnerCommentStatus() {
        return ownerCommentStatus;
    }

    public void setOwnerCommentStatus(int ownerCommentStatus) {
        this.ownerCommentStatus = ownerCommentStatus;
    }

    public int getDeleteOrKeep() {
        return deleteOrKeep;
    }

    public void setDeleteOrKeep(int deleteOrKeep) {
        this.deleteOrKeep = deleteOrKeep;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", orderID=" + orderID +
                ", commentatorID=" + commentatorID +
                ", ownerID=" + ownerID +
                ", createContent='" + createContent + '\'' +
                ", createTime=" + createTime +
                ", deleteTime=" + deleteTime +
                ", commentatorCommentStatus=" + commentatorCommentStatus +
                ", ownerCommentStatus=" + ownerCommentStatus +
                ", deleteOrKeep=" + deleteOrKeep +
                '}';
    }
}
