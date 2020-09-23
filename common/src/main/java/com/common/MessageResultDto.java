package com.common;

public class MessageResultDto {

    /**
     * 发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。
     */
    String bizId;


    /**
     * 请求状态码。
     *
     * 返回OK代表请求成功。
     * 其他错误码详见错误码列表。
     */
    String code;



    /**
     * 状态码的描述。
     */
    String message;


    /**
     * 请求ID。
     */
    String requiredId;

    @Override
    public String toString() {
        return "MessageResultDto{" +
                "bizId='" + bizId + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", requiredId='" + requiredId + '\'' +
                '}';
    }


}
