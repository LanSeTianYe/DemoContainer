package com.xiaotian.demo.algorithm.leetcode;

import java.util.*;

public class A_18_4Sum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (null == nums || nums.length < 4) {
            return Collections.emptyList();
        }

        List<List<Integer>> resultList = new ArrayList<>();
        int length = nums.length;

        Map<Integer, List<Index>> twoNodeSum = new HashMap<>();

        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                Index index = new Index(i, j);
                int sum = nums[i] + nums[j];
                List<Index> targetReduceTwoSum = twoNodeSum.get(target - sum);
                if (null != targetReduceTwoSum) {
                    for (Index temp : targetReduceTwoSum) {
                        if (!index.hasCommonNode(temp)) {
                            List<Integer> resultNumber = Arrays.asList(nums[i], nums[j], nums[temp.indexA], nums[temp.indexB]);
                            Collections.sort(resultNumber);
                            if (!contains(resultList, resultNumber)) {
                                resultList.add(resultNumber);
                            }
                        }
                    }
                }
                List<Index> sumList = twoNodeSum.computeIfAbsent(sum, s -> new ArrayList<>());
                sumList.add(index);
            }
        }

        return resultList;
    }

    private boolean contains(List<List<Integer>> resultList, List<Integer> result) {
        for (List<Integer> temp : resultList) {
            int sameNumber = 0;
            for (int i = 0; i < temp.size(); i++) {
                if (((int) temp.get(i)) == (int) result.get(i)) {
                    sameNumber++;
                }
                if (sameNumber == 4) {
                    return true;
                }
            }
        }
        return false;
    }


    private class Index {

        private final int indexA;
        private final int indexB;

        public Index(int indexA, int indexB) {
            this.indexA = indexA;
            this.indexB = indexB;
        }

        public boolean hasCommonNode(Index other) {
            return this.indexA == other.indexA || this.indexA == other.indexB || this.indexB == other.indexA || this.indexB == other.indexB;
        }
    }
}
