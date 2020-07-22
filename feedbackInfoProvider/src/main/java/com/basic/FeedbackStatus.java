package com.basic;

import java.io.Serializable;

/**     * 反馈状态
 */
public class FeedbackStatus implements Serializable {

    /**
     * 为解决
     */
    public static final int NOT_SOLVED = 1;

    /**已解决
     *
     */
    public static final int SOLVED = 2;


    /** 解决中
     *
     */
    public static final int SOLVING = 3;

}
