package com.sun.xiaotian.demo.storm;


import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

import java.util.concurrent.TimeUnit;

public class RunDemo {

    public static void main(String[] args) throws InterruptedException, InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        String topologyName = "test";
        StormSubmitter.submitTopology(topologyName, getConfig(), getStormTopology());
    }


    private static Config getConfig() {
        Config config = new Config();
        config.setNumWorkers(2);
        config.setMaxSpoutPending(5000);
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
