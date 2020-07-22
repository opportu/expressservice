package com.mapper;

import com.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论模块mapper接口
 */

@Mapper
public interface CommentMapper {
    //增加评论
    @Insert("insert into comment(orderID,commentatorID,ownerID,createContent,createTime)\n" +
            "        values (#{orderID},#{commentatorID},#{ownerID},#{createContent},now())")
    int insertComment(Comment comment);

    //删除评论
    @Update("update comment set deleteOrKeep = #{deleteOrKeep},deleteTime = now() where commentID = #{commentID}")
    int deleteComment(Comment comment);

    //查看所有评论列表
    @Select("select * from comment where ownerID = #{userID}")
    List<Comment> getCommentList(Comment comment);

    //查看所有未读评论数
    @Select("select COUNT(*) from comment where ownerID = #{userID} and ownerCommentStatus = 0")
    Integer unlookCommentCounts(long userID);

    //评论状态改变：未读变已读
    @Update("update comment set ownerCommentStatus = #{ownerCommentStatus} where ownerID = #{ownerID} and orderID = #{orderID}")
    int commentStatus(Comment comment);

    //查看某个评论
    @Select("select * from comment where ownerID = #{ownerID} and orderID = #{orderID} order by createTime")
    ArrayList<Comment> queryComment(@Param(value = "ownerID") Long ownerID, @Param(value = "orderID") Long orderID);

}
