package com.xiaotian.demo.component.center.base;

/**
 * 组件的Key
 */
public interface ComponentKey {

    /**
     * 组件实现该接口，获取组件的key。
     * 需要确保同一类型的组件的Key不重复。
     *
     * @return 组件的Key
     */
    String getKey();
}
