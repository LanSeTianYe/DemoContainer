package com.xiaotian.demo.kafka.stream.wordcount;

import com.xiaotian.demo.kafka.factory.ConfigFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Scanner;

public class WordCountProducer {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        KafkaProducer<String, String> producer1 = new KafkaProducer<>(ConfigFactory.getProducerConfig());
        String next;
        while (true) {
            try {
                next = scanner.nextLine();
                if ("quit".equalsIgnoreCase(next)) {
                    System.out.println("Bye Bye!");
                    return;
                }
                System.out.println(next);
                producer1.send(new ProducerRecord<>(WordCountStream.input, next, next));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
