package com.sun.xiaotian.demo.storm.word;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

public class SplitWordBolt extends BaseRichBolt {

    private OutputCollector outputCollector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple input) {
        String[] words = input.getString(0).split(" ");
        for (String word : words) {
            outputCollector.emit(input, new Values(word));
        }
        outputCollector.ack(input);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("SplitCountBolt"));
    }
}
