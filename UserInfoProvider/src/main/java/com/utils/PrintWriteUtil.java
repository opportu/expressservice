package com.utils;

import com.common.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * session返回登录状态工具类
 */
public class PrintWriteUtil {

    public static void print(HttpServletResponse httpServletResponse, Result result){
        PrintWriter out = null;
        try{
            out = httpServletResponse.getWriter();
            out.print(result);
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(out != null){
                try{
                    out.flush();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }

    }
}
