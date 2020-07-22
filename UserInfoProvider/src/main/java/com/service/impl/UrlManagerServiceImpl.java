package com.service.impl;

import com.basic.UserCommonStatus;
import com.mapper.UrlManagerMapper;
import com.pojo.Url;
import com.service.UrlManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UrlManagerServiceImpl implements UrlManagerService {


    @Autowired
    private UrlManagerMapper urlManagerMapper;

    @Override
    public int addUrl(HashMap<String, Object> paramMap) throws Exception{
        Url url = new Url();
        url.setIntercept_type(Integer.parseInt(String.valueOf(paramMap.get("intercept_type"))));
        url.setUrl(String.valueOf(paramMap.get("url")));
        url.setRequest_method(String.valueOf(paramMap.get("request_method")));
        urlManagerMapper.addUrl(url);
        return 0;
    }

    @Override
    public int deleteUrl(HashMap<String, Object> paramMap) throws Exception {
        int id = Integer.valueOf(String.valueOf(paramMap.get("id")));
        urlManagerMapper.deleteUrl(id);
        return UserCommonStatus.getCodeByName("SUCCESS");
    }

    @Override
    public int update(HashMap<String, Object> paramMap) throws Exception {
        Url url = new Url();
        url.setIntercept_type(Integer.parseInt(String.valueOf(paramMap.get("intercept_type"))));
        url.setUrl(String.valueOf(paramMap.get("url")));
        url.setRequest_method(String.valueOf(paramMap.get("request_method")));
        urlManagerMapper.update(url);
        return UserCommonStatus.getCodeByName("SUCCESS");
    }

    @Override
    public ArrayList<Url> selectUrl() throws Exception {
        return urlManagerMapper.selectUrl();
    }

}
