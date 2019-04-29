package com.xiaotian.demo.algorithm.leetcode;

import java.util.BitSet;

public class A_210_CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        if (null == prerequisites || prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }

        BitSet[] courseDependence = new BitSet[numCourses];
        BitSet remainCourse = new BitSet();

        for (int i = 0; i < numCourses; i++) {
            remainCourse.set(i);
            courseDependence[i] = new BitSet();
        }

        for (int[] prerequisite : prerequisites)
            courseDependence[prerequisite[0]].set(prerequisite[1]);

        int nextCourse = getNextCourse(remainCourse, courseDependence);
        int index = 0;
        while (nextCourse >= 0) {
            result[index++] = nextCourse;
            remainCourse.set(nextCourse, false);
            nextCourse = getNextCourse(remainCourse, courseDependence);
        }
        return remainCourse.cardinality() > 0 ? new int[]{} : result;
    }

    private int getNextCourse(BitSet remainCourse, BitSet[] courseDependence) {
        int nextIndex = remainCourse.nextSetBit(0);
        while (nextIndex >= 0) {
            courseDependence[nextIndex].and(remainCourse);
            if (courseDependence[nextIndex].cardinality() == 0) {
                return nextIndex;
            }
            nextIndex = remainCourse.nextSetBit(nextIndex + 1);
        }
        return -1;
    }
}
