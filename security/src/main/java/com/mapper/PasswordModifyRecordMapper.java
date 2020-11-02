package com.mapper;

import com.domain.PasswordModifyRecord;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordModifyRecordMapper {

    int insertRecord(PasswordModifyRecord record);

    PasswordModifyRecord selectRecord(@Param("userCode") String userCode);
}
