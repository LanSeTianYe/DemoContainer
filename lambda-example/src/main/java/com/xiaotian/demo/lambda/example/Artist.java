package com.xiaotian.demo.lambda.example;

import lombok.Data;

import java.util.List;

/**
 * 创作音乐的个人或者团队
 *
 * @author sunfeilong [2020/4/6 17:20]
 */

@Data
public class Artist {

    private String name;

    /**
     * 成员
     */
    private List<String> members;

    /**
     * 来自哪里
     */
    private String origin;
}
