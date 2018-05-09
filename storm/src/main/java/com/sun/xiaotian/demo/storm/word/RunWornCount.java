package com.sun.xiaotian.demo.storm.word;


import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import java.util.concurrent.TimeUnit;

public class RunWornCount {

    public static void main(String[] args) throws InterruptedException {
        String topologyName = "test";

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology(topologyName, getConfig(), getStormTopology());
        TimeUnit.SECONDS.sleep(50);
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
        builder.setSpout("sentence",new RandomSentenceSpout(), 10);
        builder.setBolt("split", new SplitWordBolt(), 3)
                .shuffleGrouping("sentence");
        builder.setBolt("wordcount", new WordCountBolt(), 2)
                .shuffleGrouping("split", "SplitCountBolt");
        return builder.createTopology();
    }
}
