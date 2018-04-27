package com.sun.xiaotian.demo.storm.word;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WordCountBolt extends BaseRichBolt {

    private OutputCollector collector;

    private Map<String, Integer> countMap = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        String word = input.getString(0);
        countMap.put(word, countMap.getOrDefault(word, 1) + 1);
        collector.emit(input, new Values(countMap.get(word), word));
        collector.ack(input);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("WordCountBolt"));
    }
}
