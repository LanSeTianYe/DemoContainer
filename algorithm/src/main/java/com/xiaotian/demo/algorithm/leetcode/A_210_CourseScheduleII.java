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
        boolean[] taking = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++)
            dependency.add(new ArrayList<>());

        for (int[] prerequisite : prerequisites)
            dependency.get(prerequisite[0]).add(prerequisite[1]);

        for (int i = 0; i < numCourses; i++) {
            if (!take(i, dependency, hasTakes, taking, result)) return new int[]{};
        }
        return result;
    }

    private boolean take(int index, List<List<Integer>> dependency, boolean[] hasTake, boolean[] taking, int[] result) {
        if (hasTake[index]) return true;
        if (taking[index]) return false;

        taking[index] = true;
        for (Integer subIndex : dependency.get(index)) {
            if (!take(subIndex, dependency, hasTake, taking, result)) {
                return false;
            }
        }
        result[courseIndex++] = index;
        taking[index] = false;
        hasTake[index] = true;
        return true;
    }
}
