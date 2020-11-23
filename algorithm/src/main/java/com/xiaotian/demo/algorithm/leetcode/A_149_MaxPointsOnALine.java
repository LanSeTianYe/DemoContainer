package com.xiaotian.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 假设 p1(x1,y1) p2(x2,y2) p3(x3,y3) 在一条直线上,假设:
 * (y2-y1)/(x2-x1)= a/b
 * (y3-y1)/(x3-x1)= c/d
 * 则有 a/b = c/d => ad = bc
 * 两个for循环O(n^2)
 */
public class A_149_MaxPointsOnALine {

    public int maxPoints(int[][] points) {

        if (null == points) {
            return 0;
        }
        if (points.length < 3) {
            return points.length;
        }

        List<Line> allLine = findAllLine(points);
        if (allLine.size() == 0) {
            return points.length;
        }

        for (int[] point : points) {
            for (Line line : allLine) {
                if (onLine(point[0], point[1], line)) {
                    line.addOne();
                }
            }
        }
        allLine.sort(Comparator.comparingInt(Line::getPointCount));
        return allLine.get(allLine.size() - 1).getPointCount();
    }

    private List<Line> findAllLine(int[][] points) {
        List<Line> hasFindLine = new ArrayList<>();
        List<Point> hasAccessPoints = new ArrayList<>();

        for (int[] point : points) {
            if (hasAccessPoints.size() == 0) {
                hasAccessPoints.add(initPoint(point));
                continue;
            }
            // add new line
            for (Point hasAccessPoint : hasAccessPoints) {
                //filter same point
                if (hasAccessPoint.x == point[0] && hasAccessPoint.y == point[1]) {
                    continue;
                }
                boolean needAndLine = true;
                for (Line line : hasFindLine) {
                    if (sameLine(line, point[0], point[1], hasAccessPoint.x, hasAccessPoint.y)) {
                        needAndLine = false;
                        break;
                    }
                }
                if (needAndLine) {
                    hasFindLine.add(initLine(point[0], point[1], hasAccessPoint.x, hasAccessPoint.y));
                }
            }
            hasAccessPoints.add(initPoint(point));
        }

        hasFindLine.sort(Comparator.comparingInt(Line::getPointCount));
        return hasFindLine;
    }

    private boolean onLine(long x, long y, Line line) {
        return ((line.y2 - y) * (line.x1 - x) == (line.y1 - y) * (line.x2 - x));
    }

    private boolean sameLine(Line line, long x3, long y3, long x4, long y4) {
        return onLine(x3, y3, line) && onLine(x4, y4, line);
    }

    private Point initPoint(int[] point) {
        return new Point(point[0], point[1]);
    }

    private Line initLine(int x1, int y1, long x2, long y2) {
        return new Line(x1, y1, x2, y2);
    }

    private class Point {
        private final long x;
        private final long y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    private class Line {

        private final long x1;
        private final long y1;

        private final long x2;
        private final long y2;

        private int pointCount = 0;

        public Line(long x1, long y1, long x2, long y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public int getPointCount() {
            return pointCount;
        }

        private void addOne() {
            pointCount++;
        }
    }

}
