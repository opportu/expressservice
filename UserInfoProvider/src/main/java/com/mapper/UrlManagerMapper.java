package com.mapper;

import com.pojo.Url;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * 接口相对地址管理Mapper
 */

@Mapper
public interface UrlManagerMapper {

    @Insert("insert into require_url values (#{id}, #{intercept_type}, #{url}, #{request_method})")
    int addUrl(Url url);

    @Delete("delete from require_url where id = #{id}")
    int deleteUrl(int id);

    @Update("update require_url set intercept_type = #{intercept_type}, url = #{url},request_method = #{request_method}")
    int update(Url url);

    @Select("select * from require_url")
    ArrayList<Url> selectUrl();

}
