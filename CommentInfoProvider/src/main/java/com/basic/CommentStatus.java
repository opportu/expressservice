package com.basic;

import java.io.Serializable;

/**
 * @Auther:穆文成
 * @Description:评论状态基类
 * @Data:2019/11/22 12:04
 */

public class CommentStatus implements Serializable {

    /**
     * 未读
     */
    public static final int NOT_READED = 0;

    /**
     * 已读
     */
    public static final int READED = 1;

    /**
     * 未删除
     * */
    public static final int KEEP = 0;

    /**
     * 已删除
     * */
    public static final int DELETE = 1;

}
