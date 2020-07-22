package com.service;

import com.common.Result;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ServerExceptionFallback implements FallbackFactory<WeChatService> {
    @Override
    public WeChatService create(Throwable throwable) {
        return new WeChatService() {
            @Override
            public Result getBasicInfo(HashMap<String, Object> paramMap) {
                Result result = new Result();
                result.setIsSuccess(false);
                result.setMessage("调用了weChatService中getBasicInfo的fallback方法");
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("userID", "default_1");
                map.put("method", "Fallback Method");
                result.setData(map);
                result.setCode(600);
                return result;
            }

            @Override
            public Result loginUser(HashMap<String, Object> paramMap) {
                Result result = new Result();
                result.setIsSuccess(false);
                result.setMessage("調用了weChatService中loginUser的fallback方法");
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("user", "default_user");
                map.put("method", "Fallback Method");
                result.setData(map);
                result.setCode(600);
                return result;
            }

            @Override
            public Result writeInfo(HashMap<String, Object> paramMap) {
                Result result = new Result();
                result.setIsSuccess(false);
                result.setMessage("調用了WeChatService中的writeInfo的fallback方法");
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("order", "default_order");
                map.put("method", "Fallback Method");
                result.setData(map);
                result.setCode(600);
                return result;
            }

            @Override
            public Result showAllOrderInfoList(HashMap<String, Object> paramMap) {
                Result result = new Result();
                result.setIsSuccess(false);
                result.setMessage("调用了WeChatService中的showAllOrderInfoList的fallback方法");
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("orderList", "default_orderList");
                map.put("method", "Fallback Method");
                result.setData(map);
                result.setCode(600);
                return result;
            }
        };
    }
}
