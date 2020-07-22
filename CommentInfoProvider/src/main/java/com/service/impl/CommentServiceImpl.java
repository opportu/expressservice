package com.service.impl;

import com.basic.CommentStatus;
import com.mapper.CommentMapper;
import com.pojo.Comment;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 评论ServiceImpl
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 发表评论
     * 发布者自己评论自己的需求默认消息已读
     * @param paramMap
     * @return
     * @throws Exception
     */
    @Override
    public long insertComment(HashMap<String, Object> paramMap) throws Exception {

        //从前端获取用户id、订单id、评论id，评论者id，评论内容
        Comment comment = new Comment();
        comment.setOrderID(Long.valueOf(paramMap.get("orderID").toString()));
        comment.setCommentatorID(Long.valueOf(paramMap.get("commentatorID").toString()));
        comment.setOwnerID(Long.valueOf(paramMap.get("ownerID").toString()));
        comment.setCreateContent(String.valueOf(paramMap.get("createContent")));
        commentMapper.insertComment(comment);

        return comment.getCommentID();
    }

    /**
     * 删除评论
     */
    @Override
    public long deleteComment(HashMap<String, Object> paramMap) throws Exception {
        //从前端获取commentID
        Comment comment = new Comment();
        comment.setCommentID(Long.valueOf(paramMap.get("commentID").toString()));
        comment.setDeleteOrKeep(CommentStatus.DELETE);

        return commentMapper.deleteComment(comment);
    }

    /**
     * 获取评论列表
     * 评论状态不改变
     */
    @Override
    public List<Comment> getCommentList(HashMap<String, Object> paramMap) throws Exception {

        Comment comment = new Comment();
        comment.setCommentatorID(Long.valueOf(paramMap.get("userID").toString()));
        comment.setOrderID(Long.valueOf(paramMap.get("orderID").toString()));
        return commentMapper.getCommentList(comment);
    }

    /**
     * 查看所有未读评论数
     * 评论状态不改变
     */
    @Override
    public Integer unlookCommentCounts(HashMap<String, Object> paramMap) throws Exception {
        long userID = Long.parseLong(paramMap.get("userID").toString());
        return commentMapper.unlookCommentCounts(userID);
    }

    /**
     * 评论状态改变：未读变已读
     * */
    @Override
    public int commentStatus(HashMap<String ,Object> paramMap){
        Comment comment = new Comment();
        comment.setOwnerID(Long.parseLong(String.valueOf(paramMap.get("userID"))));
        comment.setOrderID(Long.parseLong(String.valueOf(paramMap.get("orderID"))));
        comment.setOwnerCommentStatus(CommentStatus.READED);

        return commentMapper.commentStatus(comment);
    }

    /**
     * 查看某个评论
     */
    @Override
    public List<Comment> queryComment(HashMap<String, Object> paramMap) throws Exception {

        List<Comment> commentList = commentMapper.queryComment(Long.parseLong(String.valueOf(paramMap.get("userID")))
                ,Long.parseLong(String.valueOf(paramMap.get("orderID"))));

        return commentList;
    }
}
