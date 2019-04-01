package com.xiaotian.demo.algorithm.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/largest-perimeter-triangle/">LargestPerimeterTriangle</a>
 */
public class A_976_LargestPerimeterTriangle {

    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 3; i >= 0; i--) {
            if (A[i] + A[i + 1] > A[i + 2]) {
                return A[i] + A[i + 1] + A[i + 2];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        A_976_LargestPerimeterTriangle largestPerimeterTriangle_976 = new A_976_LargestPerimeterTriangle();
        System.out.println(largestPerimeterTriangle_976.largestPerimeter(new int[]{2, 1, 2}));
        System.out.println(largestPerimeterTriangle_976.largestPerimeter(new int[]{1, 2, 1}));
        System.out.println(largestPerimeterTriangle_976.largestPerimeter(new int[]{3, 2, 3, 4}));
        System.out.println(largestPerimeterTriangle_976.largestPerimeter(new int[]{3, 6, 2, 3}));
    }
}
