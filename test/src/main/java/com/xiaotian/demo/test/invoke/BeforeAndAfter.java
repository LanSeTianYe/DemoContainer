package com.xiaotian.demo.test.invoke;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BeforeAndAfter {
    private static List<Invoke> invokeList = new ArrayList();
    private static int currIndex = 0;

    static {
        Random random = new SecureRandom();
        int nextInt = random.nextInt(10);
        for (int i = 0; i < 10; i++) {
            if (nextInt > 5) {
                invokeList.add(new Before());
            } else {
                invokeList.add(new After());
            }
            nextInt = random.nextInt();
        }
    }

    public void process() {
        if (currIndex == invokeList.size()) {
            invoke();
        } else {
            invokeList.get(currIndex++).invoke(this);
        }
    }

    public void invoke() {
        System.out.println(this.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        BeforeAndAfter beforeAndAfter = new BeforeAndAfter();
        beforeAndAfter.process();
    }
}

interface Invoke {
    public void invoke(BeforeAndAfter beforeAndAfter);
}

class Before implements Invoke {
    public void invoke(BeforeAndAfter beforeAndAfter) {
        System.out.println(this.getClass().getSimpleName());
        beforeAndAfter.process();
    }
}

class After implements Invoke {
    public void invoke(BeforeAndAfter beforeAndAfter) {
        beforeAndAfter.process();
        System.out.println(this.getClass().getSimpleName());
    }
}
