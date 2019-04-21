package com.xiaotian.demo.algorithm;

public class A_371_Sum_Of_Two_Integers {

    public int getSum(int a, int b) {
        int result = a;
        int need = b;
        while (need != 0) {
            int temp_need = (result & need) << 1;
            result = result ^ need;
            need = temp_need;
        }
        return result;
    }
}
