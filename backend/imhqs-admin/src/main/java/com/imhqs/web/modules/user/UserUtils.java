package com.imhqs.web.modules.user;

import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Method;


/**
 * 用户静态工具类
 */
public class UserUtils {


    /**
     * 获取当前登录用户的ID
     *
     * @param throwable
     * @return
     */
    public static String getUserId(boolean throwable) {

       Object o= SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try{
        Method[] declaredMethods = o.getClass().getDeclaredMethods();
        for(Method method:declaredMethods){
            if(method.getName().equals("getUserId")){
                Object a=method.invoke(o);
               return a.toString();
            }
        }}
        catch (Exception e){
            return "";
        }
        return "";
    }

    /**
     * 获取当前登录用户的ID，默认是会抛异常的
     *
     * @return
     */
    public static String getUserId() {
        return getUserId(true);
    }
}
