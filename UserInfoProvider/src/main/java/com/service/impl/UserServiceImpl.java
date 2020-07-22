package com.service.impl;

import com.basic.UserCommonStatus;
import com.mapper.UserMapper;
import com.pojo.User;
import com.service.UserService;
import com.utils.ImageUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    /**
     * 日志管理工具
     */
    protected static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册业务方法实现
     *
     * @param paramMap 传入储存结果参数
     * @return 返回注册状态
     * @throws Exception 抛出异常
     */
    @Override
    public int addUser(HashMap<String, Object> paramMap) throws Exception {
        int flag = 0;
        User user = new User();
        user.setNickname(String.valueOf(paramMap.get("nickname")));
        user.setTelephone(String.valueOf(paramMap.get("telephone")));
        user.setPassword(String.valueOf(paramMap.get("password")));
        user.setSex(Boolean.parseBoolean(String.valueOf(paramMap.get("sex"))));
//        user.setCreate_time(TimeStampUtil.getTimeStamp());
        User user1 = userMapper.getUserByTel(user.getTelephone());
        if (user1 == null) {
            userMapper.addUser(user);
            logger.info("[用户注册] => 成功！");
            flag = 200;//表示用户在后端注册成功
        } else {
            if (user.getTelephone().equals(user.getTelephone())) {
//                logger.error("[用户注册] => 失败！");
                flag = 404;//表示手机号已经被注册

            } else {
                userMapper.addUser(user);
                logger.info("[用户注册] => 成功1！");
                flag = 200;//表示用户在后端注册成功

            }
        }
        return flag;
    }

    /**
     * 用户登录+短信验证码（手机号码+短信验证码）
     *
     * @param paramMap 传入储存参数集合
     * @return 返回登录结果
     * @throws Exception 抛出异常
     */
    @Override
    public User loginWithCode(HashMap<String, Object> paramMap) throws Exception {
        String telephone = String.valueOf(paramMap.get("telephone"));
        User isLoginUser = userMapper.getUserByTel(telephone);
        if (isLoginUser != null) {
            String code = String.valueOf(paramMap.get("code"));//判断验证码是否正确以及验证码是否有效，是否超时
            if (code.equals("123456")) {
                return isLoginUser;
            }
        }
        return new User();
    }

    /**
     * 用户注销方法实现
     *
     * @param paramMap 传入存储参数集合
     * @return 放回注销状态
     * @throws Exception 抛出异常
     */
    @Override
    public int deleteUser(HashMap<String, Object> paramMap) throws Exception {
        userMapper.deleteUser(Long.parseLong(String.valueOf(paramMap.get("userID"))));
        return UserCommonStatus.getCodeByName("SUCCESS");
    }

    /**
     * 获取用户基本信息方法实现
     *
     * @param paramMap 传入存储参数集合
     * @return 放回注销状态
     * @throws Exception 抛出异常
     */
    @Override
    public Map<String, Object> getBasicUser(HashMap<String, Object> paramMap) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userMapper.getUserByUserID(Long.parseLong(String.valueOf(paramMap.get("userID"))));
        map.put("nickname", user.getNickname());
        map.put("telephone", user.getTelephone());
        map.put("sex", user.getSex());
        map.put("totalOrderCount", user.getTotalOrderCount());
        map.put("successOrderCount", user.getSuccessOrderCount());
        map.put("failOrderCount", user.getFailOrderCount());
        return map;
    }

    /**
     * 用户实名制方法实现
     *
     * @param paramMap 传入存储结果参数
     * @return 返回User对象
     * @throws Exception 抛出异常
     */
    @Override
    public void realUser(HashMap<String, Object> paramMap) throws Exception {
        User user = userMapper.getUserByUserID(Long.parseLong(String.valueOf(paramMap.get("userID"))));
        /*user.setNickname(String.valueOf(paramMap.get("nickname")));
        user.setTelephone(String.valueOf(paramMap.get("telephone")));
        user.setPassword(String.valueOf(paramMap.get("password")));
        user.setSex(Boolean.parseBoolean(String.valueOf(paramMap.get("sex"))));*/
        user.setAddress(String.valueOf(paramMap.get("address")));
        user.setName(String.valueOf(paramMap.get("name")));
        user.setId_card(String.valueOf(paramMap.get("id_card")));
        user.setIdCardImage(ImageUtil.change_to_Stream(String.valueOf(paramMap.get("idCardImage"))));
        user.setStuCardImage(ImageUtil.change_to_Stream(String.valueOf(paramMap.get("stuCardImage"))));
        userMapper.changeUserInfo(user);
    }

    /**
     * 手机号码获取验证码
     *
     * @param paramMap 传入存储结果参数
     * @return 返回验证码
     * @throws Exception 抛出异常
     */
    @Override
    public String getCode(HashMap<String, Object> paramMap) throws Exception {
        String telephone = String.valueOf(paramMap.get("telephone"));
        boolean getCode = Boolean.parseBoolean(String.valueOf(paramMap.get("getCode")));//获取短信验证码
        if (telephone != null && getCode) {
            return "123456";
        } else {
            return null;
        }

    }


    /**
     * 查看用户信息是否完整
     *
     * @param paramMap
     * @return
     * @throws Exception
     */
    @Override
    public User viewIfFullUserInformation(HashMap<String, Object> paramMap) throws Exception {
        User user = userMapper.getUserByUserID(Long.parseLong(String.valueOf(paramMap.get("userID"))));
        if (user != null) {
            return user;
        } else {
            logger.error("[用户信息不完善]");
            return null;
        }
    }

    /**
     * 用户重置密码
     *
     * @param paramMap 传入存储结果参数
     * @return 返回更改状态
     * @throws Exception 抛出异常
     */
    @Override
    public int resetPwd(HashMap<String, Object> paramMap) throws Exception {
        User user = userMapper.getUserByTel(String.valueOf(paramMap.get("telephone")));
        user.setPassword(String.valueOf(paramMap.get("password")));
        userMapper.changeUserInfo(user);
        return UserCommonStatus.getCodeByName("SUCCESS");
    }

    /**
     * 获取用户全部信息方法实现
     *
     * @param paramMap 传入存储结果参数
     * @return 返回用户User信息
     * @throws Exception 抛出异常
     */
    @Override
    public User getAllInfo(HashMap<String, Object> paramMap) throws Exception {
        User user = userMapper.getUserByTel(String.valueOf(paramMap.get("telephone")));
        if (user != null) {
            user.setStuCardImage(null);
            user.setIdCardImage(null);
            user.setPassword("");
        }
        return user;
    }

    /**
     * 获取用户取货地址方法实现
     *
     * @param paramMap 传入储存参数集合
     * @return 返回具体地址信息
     * @throws Exception 抛出异常
     */
    @Override
    public String getAddress(HashMap<String, Object> paramMap) throws Exception {
        User user = userMapper.getUserByUserID(Long.parseLong(String.valueOf(paramMap.get("userID"))));
        return user.getAddress();
    }

    /**
     * 用户登录业务方法实现（手机号码+密码）
     *
     * @param paramMap 传入存储参数集合
     * @return 返回int类型
     */
    @Override
    public User loginUserWithPwd(HashMap<String, Object> paramMap) throws Exception {
        User user  = new User();
        user.setTelephone(String.valueOf(paramMap.get("telephone")));
        user.setPassword((String.valueOf(paramMap.get("password"))));
        User isLoginUser = userMapper.getUserByTel(String.valueOf(paramMap.get("telephone")));
        if (isLoginUser != null) {
            if (isLoginUser.getPassword().equals(String.valueOf(paramMap.get("password")))) {
                return isLoginUser;
            } else {
                return user;
            }
        } else {
            return null;
        }
    }
}
