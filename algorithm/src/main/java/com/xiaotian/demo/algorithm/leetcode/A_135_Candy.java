package com.xiaotian.demo.algorithm.leetcode;


/**
 * @see <a href="https://leetcode.com/problems/candy/description/"></a>
 */

public class A_135_Candy {

    public int candy(int[] ratings) {
        if(ratings.length == 1) {
            return 1;
        }

        int result = ratings.length;
        int leftMinDistanceMaxIndex = -1;
        int leftMinDistanceMaxIndexCandyNumber = 0;
        int lastCandyNumber = 1;

        for (int currIndex  = 0; currIndex  < ratings.length; currIndex ++) {
            //第一个元素
            if(currIndex == 0) {
                if(ratings[currIndex] > ratings[currIndex + 1]) {
                    leftMinDistanceMaxIndex = currIndex;
                }
                continue;
            }
            //平
            if(ratings[currIndex] == ratings[currIndex - 1]) {
                lastCandyNumber = 1;
                leftMinDistanceMaxIndex = currIndex;
                leftMinDistanceMaxIndexCandyNumber = 1;
            //降
            } else if(ratings[currIndex] < ratings[currIndex - 1]) {
                if(lastCandyNumber == 1) {
                    result = result + (currIndex - (leftMinDistanceMaxIndex + 1));
                    leftMinDistanceMaxIndexCandyNumber--;
                    if(leftMinDistanceMaxIndexCandyNumber <= 1) {
                        result++;
                    }
                } else {
                    lastCandyNumber = 1;
                }
            //升
            } else {
                result = result + lastCandyNumber;
                lastCandyNumber = lastCandyNumber + 1;
                leftMinDistanceMaxIndex = currIndex;
                leftMinDistanceMaxIndexCandyNumber = lastCandyNumber;
            }
        }
        return result;
    }
}
