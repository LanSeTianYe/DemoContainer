package com.xiaotian.demo.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class A_210_CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        if (null == prerequisites || prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }

        //build prerequisite matrix and prerequisite course count
        boolean[][] prerequisitesMatrix = new boolean[numCourses][numCourses];
        int[] dependenceCourseCount = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int dependenceCourse = prerequisite[1];
            if (!prerequisitesMatrix[course][dependenceCourse] && course != dependenceCourse) {
                prerequisitesMatrix[course][dependenceCourse] = true;
                dependenceCourseCount[course]++;
            }
        }


        int remainCourseCount = numCourses;
        Set<Integer> remainCourse = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            remainCourse.add(i);
        }

        int nextCourse = getNextCourse(dependenceCourseCount, remainCourse);
        while (nextCourse >= 0 && remainCourseCount > 0) {
            result[numCourses - remainCourseCount] = nextCourse;
            removeDependency(nextCourse, prerequisitesMatrix, dependenceCourseCount);
            remainCourse.remove(nextCourse);
            remainCourseCount--;
            nextCourse = getNextCourse(dependenceCourseCount, remainCourse);
        }

        return remainCourseCount > 0 ? new int[]{} : result;
    }

    private int getNextCourse(int[] dependenceCourseCount, Set<Integer> remainCourse) {
        for (Integer course : remainCourse) {
            if (dependenceCourseCount[course] == 0) {
                return course;
            }
        }
        return -1;
    }

    private void removeDependency(int courseNumber,
                                  boolean[][] prerequisitesMatrix,
                                  int[] dependenceCourseCount
    ) {

        for (int i = 0; i < prerequisitesMatrix.length; i++) {
            if (prerequisitesMatrix[i][courseNumber]) {
                prerequisitesMatrix[i][courseNumber] = false;
                dependenceCourseCount[i]--;
            }
        }
        dependenceCourseCount[courseNumber]--;
    }
}
