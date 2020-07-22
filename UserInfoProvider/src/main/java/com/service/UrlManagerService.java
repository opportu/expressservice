package com.service;

import com.pojo.Url;

import java.util.ArrayList;
import java.util.HashMap;

public interface UrlManagerService {

    int addUrl(HashMap<String, Object> paramMap) throws Exception;

    int deleteUrl(HashMap<String, Object> paramMap) throws Exception;

    int update(HashMap<String, Object> paramMap) throws Exception;

    ArrayList<Url> selectUrl() throws Exception;
}
