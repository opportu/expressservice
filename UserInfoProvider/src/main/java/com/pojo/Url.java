package com.pojo;


import java.io.Serializable;

/**
 * 项目接口相对地址
 */
public class Url implements Serializable {

    private int id;

    private int intercept_type;

    private String url;

    private String request_method;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntercept_type() {
        return intercept_type;
    }

    public void setIntercept_type(int intercept_type) {
        this.intercept_type = intercept_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequest_method() {
        return request_method;
    }

    public void setRequest_method(String request_method) {
        this.request_method = request_method;
    }




}
