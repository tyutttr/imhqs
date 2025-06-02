package com.imhqs.web.core.api;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 全局错误码定义，用于定义接口的响应数据，
 * 枚举名称全部使用代码命名，在系统中调用。
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ApiError implements Serializable {


    /**
     * 通用错误，接口参数不全
     */
    ERROR_10010002("您还未登录，请先登录！"),
    ERROR_90010001("管理员账号不存在！"),
    ERROR_90010002("账号或密码错误！"),
    ERROR_90010003("至少要包含一个角色！"),
    ERROR_90010005("账号被禁用，请联系管理员！");



    public String msg;

    /**
     * 生成Markdown格式文档，用于更新文档
     * @param args
     */
    public static void main(String[] args) {
        for (ApiError e : ApiError.values()) {
            System.out.println("'"+e.name().replace("ERROR_", "")+"':'"+e.msg+"',");
        }
    }

    /**
     * 获取错误码
     * @return
     */
    public Integer getCode(){
        return Integer.parseInt(this.name().replace("ERROR_", ""));
    }
}
