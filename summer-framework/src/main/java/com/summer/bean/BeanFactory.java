package com.summer.bean;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author panyox
 * @date 2020/7/21 6:12 下午
 */
public abstract class BeanFactory {

    protected ConcurrentHashMap<String, Object> beans;

    public abstract Object getBean(String id);

    public abstract void put(String id, Object object);
}
