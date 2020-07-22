package com.controller;

import com.common.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Comment;
import com.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 评论模块控制层
 */
@RestController
@RequestMapping("/onlineChat")
public class CommentController {
    /**
     * 日志管理工具
     */
    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    /**
     * 测试接口
     */
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test success";
    }

    /**
     * 发表评论接口
     */
    @RequestMapping(value = "/insertComment", method = RequestMethod.POST)
    public Result insertComment(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();

        try {
            commentService.insertComment(paramMap);
            result.setIsSuccess(true);
            logger.info("[评论成功] insertComment success!");
        } catch (Exception e) {
            result.setIsSuccess(false);
            logger.error("[评论失败] insertComment fail！", e);
        }

        return result;
    }

    /**
     * 删除评论接口
     */
    @RequestMapping(value = "/deleteComment",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteComment(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();

        try {
            commentService.deleteComment(paramMap);
            result.setIsSuccess(true);
            logger.info("[删除评论成功] deleteComment success!");
        } catch (Exception e) {
            result.setIsSuccess(false);
            logger.error("[删除评论失败] deleteComment failed!", e);
        }

        return result;
    }

    /**
     * 查看所有评论接口
     */
    @RequestMapping(value = "/getCommentList", method = RequestMethod.POST)
    @ResponseBody
    public Result getCommentList(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();

        try {
            PageHelper.startPage(Integer.valueOf(paramMap.get("pageNo").toString()),
                    Integer.valueOf(paramMap.get("pageSize").toString()));

            List<Comment> list = commentService.getCommentList(paramMap);

            PageInfo pageInfo = new PageInfo(list,10);

            result.setData(list);
            result.setIsSuccess(true);
            logger.info("[查询所有未读评论成功] unlookCommentList success!");
        } catch (Exception e) {
            result.setIsSuccess(false);
            logger.error("[查询所有未读评论失败] unlookCommentList failed！", e);
        }

        return result;
    }

    /**
     * 查看所有未读评论数接口
     */
    @RequestMapping(value = "/unlookCommentCounts",method = RequestMethod.POST)
    @ResponseBody
    public Result unlookCommentCounts(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();

        try {
            Integer counts = commentService.unlookCommentCounts(paramMap);
            result.setIsSuccess(true);
            result.setData(counts);
            logger.info("[查询所有已读评论成功] lookCommentList success!");
        } catch (Exception e) {
            result.setIsSuccess(false);
            logger.error("[查询所有已读评论失败] lookCommentList failed！", e);
        }

        return result;
    }

    /**
     * 查看某个评论详情接口
     */
    @PostMapping("/commentDetails")
    @ResponseBody
    public Result commentDetails(@RequestBody HashMap<String, Object> paramMap) {
        Result result = new Result();

        try {
            commentService.commentStatus(paramMap);
            logger.info("[评论未读变已读成功] commentStatus change success！");

            List<Comment> comments = commentService.queryComment(paramMap);
            result.setIsSuccess(true);
            result.setData(comments);
            logger.info("[查询某个评论成功] listAllComment success!");
        } catch (Exception e) {
            result.setIsSuccess(false);
            logger.error("[查询某个评论失败] listAllComment failed！", e);
        }

        return result;
    }
}
