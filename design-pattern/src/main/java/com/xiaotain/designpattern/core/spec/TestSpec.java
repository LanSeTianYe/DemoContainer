package com.xiaotain.designpattern.core.spec;

import com.xiaotain.designpattern.core.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author sunfeilong [2020/6/17 11:21]
 */
public class TestSpec {

    public static void main(String[] args) {
        ArrayList<User> data = new ArrayList<>();
        data.add(new User("张三", 10));
        data.add(new User("李四", 15));
        data.add(new User("王五", 20));


        Predicate<User> p1 = (u) -> u.getAge() >= 15;
        Predicate<User> p2 = (u) -> u.getName().contains("四");

        Predicate<User> p3 = p1.and(p2);

        List<User> filter = filter(data, p3);

        System.out.println(filter);
    }


    private static List<User> filter(List<User> data, Predicate<User> predicate) {
        return data.stream().filter(predicate).collect(Collectors.toList());
    }
}
