package com.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PasswordModifyRecord {
    private String userCode;
    private String password;
    private boolean admitSet;
    private Date createAt;
    private String createBy;

    public PasswordModifyRecord() {

    }

    public PasswordModifyRecord(String accountCode, String password, String operator, boolean admitSet) {
        this.setUserCode(accountCode);
        this.setPassword(password);
        this.setCreateBy(operator);
        this.setAdmitSet(admitSet);
    }

}
