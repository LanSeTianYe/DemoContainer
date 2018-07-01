package com.sun.xiaotian.demo.multithread;

//cache line 64 子节
public class CacheLineEffect {

    //考虑一般缓存行大小是64字节，一个 long 类型占8字节
    private static int col = 1024 * 1024;
    private static int row = 8;
    private static long[][] arr = new long[col][row];

    public static void main(String[] args) {

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                arr[i][j] = 0L;
            }
        }

        long sum = 0L;
        long marked = System.currentTimeMillis();
        for (int i = 0; i < col; i += 1) {
            for (int j = 0; j < row; j++) {
                sum = arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");

        sum = 0L;
        marked = System.currentTimeMillis();
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < col; j++) {
                sum = arr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
    }
}
