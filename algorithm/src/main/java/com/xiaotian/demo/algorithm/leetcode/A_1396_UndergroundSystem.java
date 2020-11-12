package com.xiaotian.demo.algorithm.leetcode;


import java.util.HashMap;
import java.util.Map;

public class A_1396_UndergroundSystem {

    private Map<Integer, ChecnInInfo> checkInInfoMap;
    private Map<String, StatisticsInfo> resultMap;

    public A_1396_UndergroundSystem() {
        resultMap = new HashMap<>();
        checkInInfoMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        ChecnInInfo checnInInfo = checkInInfoMap.computeIfAbsent(id, key -> new ChecnInInfo(stationName, t));
        checnInInfo.setStation(stationName);
        checnInInfo.setTime(t);
    }

    public void checkOut(int id, String stationName, int t) {
        ChecnInInfo checnInInfo = this.checkInInfoMap.get(id);
        String statisticsInfoKey = getStatisticsInfoKey(checnInInfo.getStation(), stationName);
        this.resultMap.computeIfAbsent(statisticsInfoKey, (k) -> new StatisticsInfo(0, 0)).addTimeAndCount(t - checnInInfo.getTime());

    }

    public double getAverageTime(String startStation, String endStation) {
        String statisticsInfoKey = getStatisticsInfoKey(startStation, endStation);
        StatisticsInfo statisticsInfo = resultMap.get(statisticsInfoKey);
        return null == statisticsInfo ? 0 : 1.0 * statisticsInfo.getSumTime() / statisticsInfo.getCount();
    }

    private String getCheckInKey(int id, String start) {
        return id + "_" + start;
    }

    private String getStatisticsInfoKey(String start, String end) {
        return start + "_" + end;
    }

    class ChecnInInfo {
        // checkin station
        private String station;
        // checkin time
        private int time;

        public ChecnInInfo(String station, int time) {
            this.station = station;
            this.time = time;
        }

        public String getStation() {
            return station;
        }

        public int getTime() {
            return time;
        }

        public void setStation(String station) {
            this.station = station;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }

    class StatisticsInfo {
        //sum ccount
        private int count;
        //sum time
        private long sumTime;

        public StatisticsInfo(int count, long sumTime) {
            this.count = count;
            this.sumTime = sumTime;
        }

        private void addCount() {
            this.count++;
        }

        public void addTimeAndCount(int time) {
            this.sumTime += time;
            addCount();
        }

        public int getCount() {
            return count;
        }

        public long getSumTime() {
            return sumTime;
        }
    }
}
