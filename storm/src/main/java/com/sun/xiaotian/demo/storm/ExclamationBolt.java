package com.sun.xiaotian.demo.storm;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

public class ExclamationBolt extends BaseRichBolt {

    private OutputCollector outputCollector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.outputCollector = collector;
    }

    @Override
    public void execute(Tuple tuple) {
        outputCollector.emit(tuple, new Values(tuple.getString(0) + "!!!"));
        outputCollector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
