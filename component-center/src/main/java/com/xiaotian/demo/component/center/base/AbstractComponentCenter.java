package com.xiaotian.demo.component.center.base;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbstractComponentCenter<T extends ComponentKey> {

    private final List<T> componentList;

    private Map<String, T> componentMap;


    /**
     * 抽象组件中心
     *
     * @param componentList 组件列表
     */
    protected AbstractComponentCenter(List<T> componentList) {
        this.componentList = componentList;
        if (CollectionUtils.isEmpty(componentList)) {
            log.warn("not component find for {}", this.getClass().getCanonicalName());
        } else {
            this.componentMap = new HashMap<>();
            for (T t : componentList) {
                this.componentMap.put(t.getKey(), t);
            }
        }
    }

    /**
     * 获取对应组件
     *
     * @param componentKey 组件的Key
     * @return key 对应的组件，不存在返回空
     */
    public T getComponent(String componentKey) {
        return componentMap.get(componentKey.toLowerCase());
    }
}
