package com.xiaotian.demo.kafka.connect.file;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;

import java.util.List;
import java.util.Map;

public class FileConnectSink extends SinkConnector {

    private String sourceTopic;
    private String fileName;

    public FileConnectSink(String sourceTopic, String fileName) {
        this.sourceTopic = sourceTopic;
        this.fileName = fileName;
    }

    @Override
    public String version() {
        return FileConnectInfo.VERSION;
    }

    @Override
    public void start(Map<String, String> props) {

    }

    @Override
    public Class<? extends Task> taskClass() {
        return FileConnectSinkTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        return null;
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {
        return null;
    }
}
