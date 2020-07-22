package com.utils;
import java.io.IOException;
import java.util.ArrayList;

import com.pojo.Url;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 读取properties文件工具
 * 通过获取文件名以及其中的Key值来得到要获取的参数
 */
public class PropertiesUtil {

    public static String getValue(String fileName,String key) {
        String value = null;
        try {
            value = PropertiesLoaderUtils.loadAllProperties(fileName).getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 从intercept_url.properties文件获取拦截器接口地址
     * @param param 传入参数
     * @return 返回list集合
     */
    public static ArrayList<Url> getUrls (String param) {
        ArrayList<Url> urls = new ArrayList<Url>();
        String[] urlsStr = param.split(",");
        for (String urlStr : urlsStr) {
            Url url = new Url();
            String[] urlAndMethod = urlStr.split("_");
            url.setIntercept_type(Integer.parseInt(urlAndMethod[0]));
            url.setUrl(urlAndMethod[1]);
            url.setRequest_method(urlAndMethod[2]);
            urls.add(url);
        }
        return urls;
    }
}