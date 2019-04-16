package com.sun.xiaotian.demo.kafka.connect.file;

import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.Map;

public class FileConnectSinkTask extends SinkTask {

    private final static Logger logger = LoggerFactory.getLogger(FileConnectSinkTask.class);

    private String fileName;
    private String sourceTopic;
    private RandomAccessFile randomAccessFile;

    @Override
    public String version() {
        return FileConnectInfo.VERSION;
    }

    @Override
    public void start(Map<String, String> props) {
        this.fileName = props.get("fileName");
        this.sourceTopic = props.get("sourceTopic");
        try {
            randomAccessFile = new RandomAccessFile(fileName, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        for (SinkRecord record : records) {
            logger.info(record.toString());
        }
    }

    @Override
    public void stop() {

    }
}
