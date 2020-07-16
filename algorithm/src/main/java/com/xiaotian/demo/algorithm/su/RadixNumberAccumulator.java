package com.xiaotian.demo.algorithm.su;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 进制数累加器
 *
 * @author sunfeilong [2020/7/16 15:06]
 */
public class RadixNumberAccumulator<T> {

    /**
     * 基础元素数组
     */
    private final T[] elementData;

    /**
     * 基础元素数组的长度
     */
    private int length;

    /**
     * 累加到第几个
     */
    private final AtomicLong num;


    /**
     * 构造累加器
     *
     * @param elementData 累加器内元素
     * @param initNum     跳过多少个
     */
    public RadixNumberAccumulator(T[] elementData, long initNum) {
        if (Objects.isNull(elementData)) {
            throw new IllegalArgumentException("element data is null!");
        }
        if (elementData.length <= 1) {
            throw new IllegalArgumentException("base data's length less or equal 1 !");
        }
        if (initNum < 0) {
            throw new IllegalArgumentException("init num msut grater than 0!");
        }
        this.elementData = elementData;
        length = this.elementData.length;
        num = new AtomicLong(0);
    }

    public RadixNumberAccumulator(T[] elementData) {
        if (Objects.isNull(elementData)) {
            throw new IllegalArgumentException("element data is null!");
        }
        if (elementData.length <= 1) {
            throw new IllegalArgumentException("base data's length less or equal 1 !");
        }
        this.elementData = elementData;
        length = this.elementData.length;
        num = new AtomicLong(0);
    }


    /**
     * 获取下一个数
     *
     * @return 下一个数
     */
    String next() {
        long currNum = num.getAndAdd(1);
        return getValue(currNum);
    }

    /**
     * 根据元素个数计算出下一个元素
     *
     * @param num 元素个数
     * @return 下一个元素
     */
    private String getValue(long num) {
        int elementIndex = ((int) (num % length));
        T element = elementData[elementIndex];
        num = num / length;
        if (num != 0) {
            return getValue(num) + element.toString();
        } else {
            return element.toString();
        }
    }
}
