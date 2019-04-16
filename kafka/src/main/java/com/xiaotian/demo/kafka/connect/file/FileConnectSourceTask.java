package com.xiaotian.demo.kafka.connect.file;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FileConnectSourceTask extends SourceTask {

    private final static Logger logger = LoggerFactory.getLogger(FileConnectSourceTask.class);

    private String fileName;
    private RandomAccessFile randomAccessFile;
    private String sinkTopic;

    @Override
    public String version() {
        return FileConnectInfo.VERSION;
    }

    @Override
    public void start(Map<String, String> props) {
        this.fileName = props.get("fileName");
        this.sinkTopic = props.get("sinkTopic");
        try {
            randomAccessFile = new RandomAccessFile(fileName, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        ArrayList<SourceRecord> records = new ArrayList<>();
        while (records.isEmpty()) {
            try {
                LineAndOffset line = readToNextLine(randomAccessFile);
                if (line != null) {
                    Map<String, Object> sourcePartition = Collections.singletonMap("fileName", fileName);
                    Map<String, Object> sourceOffset = Collections.singletonMap("position", randomAccessFile.getFilePointer());
                    records.add(new SourceRecord(sourcePartition, sourceOffset, sinkTopic, Schema.STRING_SCHEMA, line));
                } else {
                    Thread.sleep(1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return records;
    }

    private LineAndOffset readToNextLine(RandomAccessFile randomAccessFile) throws IOException {
        String line = randomAccessFile.readLine();
        long filePointer = randomAccessFile.getFilePointer();
        return new LineAndOffset(line, filePointer);
    }

    @Override
    public synchronized void stop() {
        try {
            randomAccessFile.close();
        } catch (IOException e) {
            logger.error("关闭文件失败" + fileName);
            logger.error("错误信息: " + e.getMessage(), e);
        }
    }
}
