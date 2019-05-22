package com.xiaotian.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A_763_PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        if (null == S || S.length() == 0) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();
        int[] lastIndex = new int[26];
        char[] sArr = S.toCharArray();

        for (int i = 0; i < sArr.length; i++) {
            lastIndex[sArr[i] - 'a'] = i;
        }

        int start = 0;
        int currIndex = start;
        int end = lastIndex[sArr[0] - 'a'];
        while (true) {
            while (currIndex < end) {
                currIndex++;
                end = Math.max(lastIndex[sArr[currIndex] - 'a'], end);
            }
            result.add(end - start + 1);
            start = end + 1;
            if (start > sArr.length - 1) {
                break;
            }
            currIndex = start;
            end = lastIndex[sArr[start] - 'a'];
        }

        return result;
    }

}
