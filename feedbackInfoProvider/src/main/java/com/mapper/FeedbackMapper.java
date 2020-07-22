package com.mapper;

import com.pojo.FeedbackInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 反馈模块mapper接口
 */
@Mapper
public interface FeedbackMapper {

    /**
     * 添加反馈
     * @param feedbackInfo
     * @return
     */
    @Insert("insert into feedbackInfo (feedbackTypeID,feedbackUserID,content,telephone,respondentID,createTime,statusID)\n" +
            "         values (\n" +
            "        #{feedbackTypeID},#{feedbackUserID},#{content},#{telephone},#{respondentID},now(),#{statusID})")
    int addFeedback(FeedbackInfo feedbackInfo);

    /**
     * 编辑反馈
     * @param feedbackInfo
     * @return
     */
    @Update("update feedbackInfo set feedbackTypeID=#{feedbackTypeID},\n" +
            "        feedbackUserID=#{feedbackUserID},content=#{content},\n" +
            "        telephone=#{telephone},respondentID=#{respondentID},\n" +
            "        statusID=#{statusID}\n" +
            "         where id=#{feedbackUserID}")
    int editFeedbackInfo(FeedbackInfo feedbackInfo);

    /**
     * 根据页面要求显示的反馈数量查询反馈列表
     * @param pageCount
     * @return
     */
    @Select("select * from feedbackInfo\n" +
            "        where content like #{content} and creator like #{creator}\n" +
            "        and statusID = #{statusID} and telephone=#{telephone} and\n" +
            "        if #{page} == 1 then\n" +
            "          limit 0,#{pageCount}\n" +
            "        else\n" +
            "          limit (#{page} - 1) * #{pageCount} + #{page} - 2, #{pageCount}\n" +
            "        END IF")
    List<FeedbackInfo> queryFeedbackInfo(int page,int pageCount, FeedbackInfo feedbackInfo);

    /**
     * 查询某一反馈信息
     * @param feedbackInfoID
     * @return
     */
    @Select("select * from feedbackInfo\n" +
            "        where id = #{feedbackInfoID}\n" +
            "        order by createTime DESC")
    FeedbackInfo queryDTFeedbackInfo(int feedbackInfoID);

}

