package com.mapper;
/**
 * 信息模块mybatis接口
 */

import com.pojo.OrderInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * function:发单人填写发单信息的接口
     * @param orderInfo
     */
    @Insert("insert into info (telephone,shipAddress,sendAddress,size,money,note,sex,deadline,createtime) values (\n" +
            "        #{telephone},#{shipAddress},#{sendAddress},#{size},#{money},#{note},#{sex},#{deadline},now())")
     int addinfo(OrderInfo orderInfo);

    /**
     * 查询所有订单接口
     * @return
     */
    @Select("select * from info")
     List<OrderInfo> findAllInfo();

    /**
     * 接单用户接单pojo
     * @param orderInfo
     * @return
     */
    @Insert("insert into info(receiverID,receiver_telephone,orderStatusID,receivetime) values (#{receiverID},#{receiver_telephone},#{orderStatusID},now()) where id = #{id}")
     int order(OrderInfo orderInfo);

    /**
     * 查找用户的发单列表
     * @param orderInfo
     * @return
     */
    @Select("select * from info where sendUserID = #{sendUserID}")
    List<OrderInfo> findsendInfo(OrderInfo orderInfo);

    /**
     * 查找用户的接单列表
     * @param orderInfo
     * @return
     */
    @Select("select * from info where receiver_telephone = #{receiver_telephone}")
    List<OrderInfo> findgetInfo(OrderInfo orderInfo);

    /**
     * 检查订单状态
     * @param id
     * @return
     */
    @Select("select * from info where id=#{id}")
    OrderInfo checkInfo(int id);

    /**
     * 改变订单状态
     * @param orderInfo
     * @return
     */
    @Update("update info set orderStatusID = #{orderStatusID} where id = #{id}")
    int changeInfostatus(OrderInfo orderInfo);

    /**
     * 查询某条订单
     * @param orderInfo
     * @return
     */
    @Select("select  * from info where id = #{id}")
    List<OrderInfo> querySingleInfoOrder(OrderInfo orderInfo);

    /**
     * 发单用户取消发单，删除订单
     * @param orderInfo
     * @return
     */
    @Delete("delete sendUserID from info where id = #{id} and id >20")
     int deletesenderInfo(OrderInfo orderInfo);

    /**
     * 更新操作，在删除主键后使主键连续
     * @param id
     * @return
     */
    @Update("update info set id = id-1 where id > #{id}")
     int update(int id);

    /**
     * 接单用户取消接单,删除接单记录
     * @param orderInfo
     */
    @Update("update info set receiverID = null,receiver_telephone = null where id = #{id}")
     void deletereceiverInfo(OrderInfo orderInfo);

    /**
     *发单人编辑订单
     * @param orderInfo
     * @return
     */
    @Update("update info set telephone = #{telephone} where id = #{id}")
     int senderEditInfo(OrderInfo orderInfo);

    /**
     * 接单人编辑订单
     * @param orderInfo
     * @return
     */
    @Update("update info set receiver_telephone = #{receiver_telephone} where id = #{id}")
     int geterEditInfo(OrderInfo orderInfo);

    /**
     * 改变订单有效性
     * @param id
     * @return
     */
    @Update("update info set isValid = 0 where id = #{id}")
     int changeisValidInfo(int id);

    /**
     * 用户长时间未点击确认，自动确认
     * @param id
     * @return
     */
    @Update("update info set orderStatusID = 8 where id = #{id}")
     int updateorderstatus(int id);

}
