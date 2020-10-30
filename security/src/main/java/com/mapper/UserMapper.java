package com.mapper;


import com.domain.LoginUserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int insertUser(LoginUserInfo userInfo);


}
