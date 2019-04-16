package com.sun.xiaotian.demo.kafka.connect.file;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileConnectSource extends SourceConnector {

    private final static Logger logger = LoggerFactory.getLogger(FileConnectSource.class);

    private String fileName;
    private String sinkTopic;

    public FileConnectSource(String fileName, String sinkTopic) {
        this.fileName = fileName;
        this.sinkTopic = sinkTopic;
    }

    @Override
    public String version() {
        return FileConnectInfo.VERSION;
    }

    @Override
    public void start(Map<String, String> map) {
        logger.info("FileConnectSource Start .... ");
    }

    @Override
    public Class<? extends Task> taskClass() {
        return FileConnectSourceTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTask) {
        ArrayList<Map<String, String>> configs = new ArrayList<>();
        Map<String, String> config = new HashMap<>();
        config.put("fileName", fileName);
        config.put("sinkTopic", sinkTopic);
        configs.add(config);
        return configs;
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {
        ConfigDef configDef = new ConfigDef();
        return configDef;
    }
}
