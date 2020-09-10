package com.xiaotian.demo.rabbitmq.core.constant;

public interface Const {

    String FANOUT_EXCHANG = "fanout_exchange";
    String ACK_FANOUT_EXCHANG = "ack_fanout_exchange";
    String DIRECT_EXCHANG = "direct_exchange";
    String TOPIC_EXCHANGE = "topic_exchange";
    String RPC_SERVER_EXCHANGE = "rpc_server_exchange";

    String RABBITMQ_LOG_EXCHANGE = "amq.rabbitmq.log";

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
    String FRUIT_BIND = "fruit.*.*";

    String FRUIT_RED_QUEUE = "fruit_red_queue";
    String FRUIT_RED_BIND = "fruit.*.red";

    String FRUIT_APPLE_QUEUE = "fruit_apple_queue";
    String FRUIT_APPLE_BIND = "fruit.apple.*";

    String RED_QUEUE = "red_queue";
    String RED_BIND = "*.*.red";

    String TOPIC_ALL_QUEUE = "topic_all_queue";
    String TOPIC_ALL_BIND = "#";

    String ALL_FANOUT = "all_fanout";
    String ALL_FANOUT_1 = "all_fanout_1";

    String DEFAULT_QUEUE = "default_queue";

    String ACK_QUEUE = "ack_queue";

    String RAIBBIT_LOG_ALL_QUEUE = "rabbit_log_all_queue";
    String RAIBBIT_LOG_ALL_BIND = "#";

    String RAIBBIT_LOG_ERROR_QUEUE = "rabbit_error_log_debug_queue";
    String RAIBBIT_LOG_ERROR_BIND = "error";

    String RPC_SERVER_QUEUE = "rpc_server_queue";

}
