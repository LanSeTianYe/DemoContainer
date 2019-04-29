package com.xiaotian.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class A_210_CourseScheduleII {

    private int courseIndex = 0;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        courseIndex = 0;
        int[] result = new int[numCourses];
        if (null == prerequisites || prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }

        List<List<Integer>> dependency = new ArrayList<>();

        boolean[] hasTakes = new boolean[numCourses];
        boolean[] inStacks = new boolean[numCourses];
        int[] index = {0};

        for (int i = 0; i < numCourses; i++)
            dependency.add(new ArrayList<>());

        for (int[] prerequisite : prerequisites)
            dependency.get(prerequisite[0]).add(prerequisite[1]);

        for (int i = 0; i < numCourses; i++) {
            if (!take(i, dependency, hasTakes, inStacks, result)) return new int[]{};
        }
        return result;
    }

    private boolean take(int index, List<List<Integer>> dependency, boolean[] hasTake, boolean[] inStack, int[] result) {
        if (hasTake[index]) return true;
        if (inStack[index]) return false;

        inStack[index] = true;
        for (Integer subIndex : dependency.get(index)) {
            boolean take = take(subIndex, dependency, hasTake, inStack, result);
            if (!take) {
                return false;
            }
        }
        result[courseIndex++] = index;
        inStack[index] = false;
        hasTake[index] = true;
        return true;
    }
}
