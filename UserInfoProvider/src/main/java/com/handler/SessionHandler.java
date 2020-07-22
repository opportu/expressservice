package com.handler;

import com.common.CommonParams;
import com.common.Result;
import com.pojo.Url;
import com.pojo.User;

import com.utils.PropertiesUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * session处理器
 *功能包括用户登录状态的判断
 */
public class SessionHandler {

    /*@Value    ("${INTERCEPT_URL}")
    private static String urlAndMethods;*/

    /**
     * 定义一个存储拦截接口相对地址的集合
     */
    private static ArrayList<String> INTERCEPT_URL = new ArrayList<String>();

    /**
     * 定义一个存储不拦截接口相对地址的集合
     */
    private static ArrayList<String> ANY_PASS = new ArrayList<String>();


    /**
     * 处理请求，对不同请求进行单独的处理
     * @param request 传入请求
     * @param response 传入响应
     * @return 返回boolean
     * @throws ServletException 抛出异常
     */
    public static boolean handle(HttpServletRequest request, HttpServletResponse response) throws ServletException{

        String urlAndMethods = PropertiesUtil.getValue("Intercept_url.properties","INTERCEPT_URL");
        //使用缓存进行数据的获取

        for (Url url : PropertiesUtil.getUrls(urlAndMethods)) {

            INTERCEPT_URL.add(url.getUrl());

            if (url.getIntercept_type() == 2) {
                ANY_PASS.add(url.getUrl());
            }
        }

        //请求相对地址
        String uri = request.getRequestURI();//获取请求的相对路径
        /**
         * 对于拦截的URl所拦截的接口相对地址进行拦截
         */
        if (INTERCEPT_URL.contains(uri)) {
            System.out.println("进来了1");
            //session判断用户登录状态
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(CommonParams.SESSION_KEY);

            //如果用户在登陆状态下想要退出，前端请求发送到这里，将session设置为invalidate
            if(user != null && uri.contains("/userInfo/exitLogin")){
                System.out.println("进来了取消登录界面");
                session.invalidate();
                return true;
            }

            if (user == null) {
                //未登录，放出ANY_PASS
                //包括登录注册，展示订单信息列表
                if (!ANY_PASS.contains(uri)) {
                    System.out.println("进来了2");
                    Result result = new Result();
                    //提示用户去登陆
                    /* result.setCode(UserCommonStatus.getCodeByName("NOT_EXIST"));
                     result.setMessage("该用户还未登录！");
                     result.setIsSuccess(false);
                     PrintWriteUtil.print(response, result);*/
                    return false;
                }
                else{
                    return true;
                }
            }
            else{
                //刷新session用户登录时间,时间为1小时
                session.setMaxInactiveInterval(60 * 60);
                System.out.println("进来了3！");
                /*Result result = new Result();
                result.setCode(UserCommonStatus.getCodeByName("REPEAT"));
                result.setMessage("该用户已经登录");
                result.setIsSuccess(false);
                PrintWriteUtil.print(response, result);*/
                return true;
            }
        }
        else {
            return false;
        }
    }

    /**
     * 给session保存用户登录状态
     * @param user 传入user
     */
    public static void save(User user, HttpSession session) {
        //session保存用户登录状态
        session.setAttribute(CommonParams.SESSION_KEY, user);
        //登录过期时间为1小时
        session.setMaxInactiveInterval(60 * 60);
    }

}
