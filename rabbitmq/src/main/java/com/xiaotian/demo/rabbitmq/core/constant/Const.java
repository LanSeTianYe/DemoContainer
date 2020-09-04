package com.xiaotian.demo.rabbitmq.core.constant;

public interface Const {

    String FANOUT_EXCHANG = "fanout_exchange";
    String DIRECT_EXCHANG = "direct_exchange";
    String TOPIC_EXCHANGE = "topic_exchange";

    String FRUIT_APPLE_RED_KEY = "fruit.apple.red";
    String FRUIT_APPLE_RED_KEY_QUEUE = "fruit_apple_red";
    String FRUIT_APPLE_GREEN_KEY = "fruit.apple.green";
    String FRUIT_APPLE_GREEN_KEY_QUEUE = "fruit_apple_green";
    String FRUIT_BANANA_RED_KEY = "fruit.banana.red";
    String FRUIT_BANANA_RED_KEY_QUEUE = "fruit_banana_red";
    String FRUIT_BANANA_YELLOW_KEY = "fruit.banana.yellow";
    String FRUIT_BANANA_YELLOW_KEY_QUEUE = "fruit_banana_yellow";

    String FOOD_APPLE_RED_KEY = "food.apple.red";
    String FOOD_APPLE_RED_KEY_QUEUE = "food_apple_red";
    String FOOD_APPLE_GREEN_KEY = "food.apple.green";
    String FOOD_APPLE_GREEN_KEY_QUEUE = "food_apple_green";
    String FOOD_BANANA_RED_KEY = "food.banana.red";
    String FOOD_BANANA_RED_KEY_QUEUE = "food_banana_red";
    String FOOD_BANANA_YELLOW_KEY = "food.banana.yellow";
    String FOOD_BANANA_YELLOW_KEY_QUEUE = "food_banana_yellow";

    String FOOD_WATERMELON_GREEN_KEY = "food.watermelon.green";
    String FOOD_WATERMELON_GREEN_KEY_QUEUE = "food_watermelon_green";


    String FRUIT_QUEUE = "fruit_queue";
    String FRUIT_BIND = "fruit.*";

    String FOOD_QUEUE = "food";
    String FOOD_BIND = "food.*";


    String RED = "red";
    String RED_BIND = "*.red";

    String GREEN = "green";
    String GREEN_BIND = "*.green";

    String TOPIC_ALL = "topic_all";
    String ALL_TOPIC_BIND = "#";

    String ALL_FANOUT = "all_fanout";
    String ALL_FANOUT_1 = "all_fanout_1";

    String DEFAULT_QUEUE = "default_queue";
}
