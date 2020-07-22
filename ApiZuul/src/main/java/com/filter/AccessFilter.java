package com.filter;

import com.google.common.base.Charsets;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccessFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 前置过滤器类型
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否必须执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //RequestContext requestContext = RequestContext.getCurrentContext();
        //HttpServletRequest httpServletRequest = requestContext.getRequest();

        //Object accessToken = httpServletRequest.getParameter("accessToken");
        /*if (accessToken == null) {
            logger.warn("access token is empty!");

            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);

            return null;
        }*/

        RequestContext requestContext = RequestContext.getCurrentContext();
        String requestBody = null;
        try {
            requestBody = StreamUtils.copyToString(requestContext.getRequest().getInputStream(), Charsets.UTF_8);
        } catch (IOException e) {
            logger.error("", e);
        }

        requestContext.addOriginResponseHeader("content-type", "application/json;charset=utf-8");
        //设置可以跨域访问
        requestContext.addZuulResponseHeader("Access-Control-Allow-Headers", "content-type,x-requested-with");
        requestContext.addZuulResponseHeader("Access-Control-Allow-Origin", "*");
        requestContext.addZuulResponseHeader("content-type", "application/json;charset=utf-8");
        // 如果为options请求则一定要返回200状态码
        if ("options".equals(requestContext.getRequest().getMethod().toLowerCase())) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.OK.value());
            return null;
        }

        logger.info("access token is ok!");
        return null;
    }
}
