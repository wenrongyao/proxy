package com.wry.dynamicproxy;

import java.lang.reflect.Method;

/**
 * @author rongyao wen
 * @date 2018/7/29
 */
public interface InvocationHandler {
    public void invoke(Method m);
}
