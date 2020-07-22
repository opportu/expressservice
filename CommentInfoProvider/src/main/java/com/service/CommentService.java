package com.service;

import com.pojo.Comment;

import java.util.HashMap;
import java.util.List;

/**
 * 评论模块业务层接口
 */

public interface CommentService {
    //增加评论
    long insertComment(HashMap<String, Object> paramMap) throws Exception;

    //删除评论
    long deleteComment(HashMap<String, Object> paramMap) throws Exception;

    //查看所有未读评论
    List<Comment> getCommentList(HashMap<String, Object> paramMap) throws Exception;

    //查看所有未读数评论接口
    Integer unlookCommentCounts(HashMap<String, Object> paramMap) throws Exception;

    //评论状态改变：未读变已读
    int commentStatus(HashMap<String, Object> paramMap) throws Exception;

    //查看某个评论
    List<Comment> queryComment(HashMap<String, Object> paramMap) throws Exception;
}
