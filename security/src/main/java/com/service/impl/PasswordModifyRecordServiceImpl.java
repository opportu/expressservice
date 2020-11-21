package com.service.impl;

import com.domain.PasswordModifyRecord;
import com.mapper.PasswordModifyRecordMapper;
import com.service.PasswordModifyRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordModifyRecordServiceImpl implements PasswordModifyRecordService {

    private static Logger logger = LoggerFactory.getLogger(PasswordModifyRecordServiceImpl.class);

    @Autowired
    private PasswordModifyRecordMapper passwordModifyRecordMapper;


    @Override
    public void savePasswordModifyRecord(PasswordModifyRecord record) {
        passwordModifyRecordMapper.insertRecord(record);

    }

    @Override
    public PasswordModifyRecord getLastPasswordModifyRecord(String userCode) {
        return passwordModifyRecordMapper.selectRecord(userCode);
    }
}
