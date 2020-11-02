package com.service;

import com.domain.PasswordModifyRecord;

public interface PasswordModifyRecordService {

    void savePasswordModifyRecord(PasswordModifyRecord record);

    PasswordModifyRecord getLastPasswordModifyRecord(String userCode);
}
