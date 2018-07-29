package com.wry.dynamicproxy;

import java.lang.reflect.Method;

/**
 * @author rongyao wen
 * @date 2018/7/29
 */
public class LogInvocationHandler implements InvocationHandler {
    Object target;

    public LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public void invoke(Method md) {
        System.out.println("日志记录开始");
        try {
            md.invoke(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("日志记录结束");
    }
}
