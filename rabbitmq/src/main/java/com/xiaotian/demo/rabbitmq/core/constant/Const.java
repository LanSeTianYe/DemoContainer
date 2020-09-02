package com.xiaotian.demo.rabbitmq.core.constant;

public interface Const {

    String FANOUT_EXCHANG = "fanout_exchange";
    String DIRECT_EXCHANG = "direct_exchange";
    String TOPIC_EXCHANGE = "topic_exchange";

    String FRUIT_APPLE_RED_KEY = "fruit.apple.red";
    String FRUIT_APPLE_GREEN_KEY = "fruit.apple.green";
    String FRUIT_BANANA_RED_KEY = "fruit.banana.red";
    String FRUIT_BANANA_YELLOW_KEY = "fruit.banana.yellow";

    String FOOD_APPLE_RED_KEY = "food.apple.red";
    String FOOD_APPLE_GREEN_KEY = "food.apple.green";
    String FOOD_BANANA_RED_KEY = "food.banana.red";
    String FOOD_BANANA_YELLOW_KEY = "food.banana.yellow";


    String FRUIT = "fruit";
    String FRUIT_BIND = "fruit.*";

    String FRUIT_APPLE_RED = "fruit_apple_red";
    String FRUIT_APPLE_RED_BIND = "fruit.apple.red";


    String FOOD = "food";
    String FOOD_BIND = "food.*";

    String FOOD_APPLE_RED = "food_apple_red";
    String FOOD_APPLE_RED_BIND = "food.apple.red";

    String RED = "red";
    String RED_BIND = "*.red";
    String GREEN = "green";
    String GREEN_BIND = "*.green";

    String ALL_TOPIC = "all_topic";
    String ALL_TOPIC_BIND = "#";

    String ALL_FANOUT = "all_fanout";
}
