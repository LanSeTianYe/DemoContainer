package com.xiaotian.demo.kafka.connect.file;

public class LineAndOffset {

    private String line;
    private long offset;

    public LineAndOffset(String line, long offset) {
        this.line = line;
        this.offset = offset;
    }
}
