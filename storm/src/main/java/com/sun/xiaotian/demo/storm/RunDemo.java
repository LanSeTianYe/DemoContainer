package com.sun.xiaotian.demo.storm;


import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

import java.util.concurrent.TimeUnit;

public class RunDemo {

    public static void main(String[] args) throws InterruptedException {
        String topologyName = "test";

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology(topologyName, getConfig(), getStormTopology());
        TimeUnit.SECONDS.sleep(100);
        localCluster.killTopology(topologyName);
        localCluster.shutdown();
    }


    private static Config getConfig() {
        Config config = new Config();
        config.setDebug(true);
        config.setNumWorkers(2);
        return config;
    }


    private static StormTopology getStormTopology() {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("words", new TestWordSpout(), 10);
        builder.setBolt("exclaim1", new ExclamationBolt(), 3)
                .shuffleGrouping("words");
        builder.setBolt("exclaim2", new ExclamationBolt(), 2)
                .shuffleGrouping("exclaim1");
        return builder.createTopology();
    }
}
