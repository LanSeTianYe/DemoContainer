package com.xiaotian.demo.test;

public class TestSwitch {

    enum Status {ACTIVE, DISABLE}

    public static void main(String[] args) {
        TestSwitch testSwitch = new TestSwitch();

        testSwitch.useString("A");
        testSwitch.useString("B");
        testSwitch.useString("C");

        testSwitch.useInt(1);
        testSwitch.useInt(12);

        testSwitch.useEnum(Status.ACTIVE);
        testSwitch.useEnum(Status.DISABLE);

        testSwitch.noDefault(1);

        testSwitch.whileAndSwitch();
    }

    public void useString(String sex) {
        switch (sex) {
            case "A":
                System.out.println("A");
                break;
            case "B":
                System.out.println("B");
                break;
            default:
                System.out.println("D");
                break;
        }
    }

    public void useInt(int number) {
        switch (number) {
            case 1:
            case 2:
                System.out.println(number);
            default:
                System.out.println(-1);
        }

    }

    public void useEnum(Status status) {
        switch (status) {
            case ACTIVE:
                System.out.println("ACTIVE");
                break;
            case DISABLE:
                System.out.println("DISABLE");
                break;
            default:
                System.out.println("DEFAULT");
        }
    }

    public void noDefault(int number) {
        switch (number) {
            case 1:
                System.out.println(1);
                break;
            default:
                System.out.println(-1);
        }
    }

    public void whileAndSwitch() {
        int code = 1;
        while (code < 2) {
            switch (code) {
                case 1:
                    code++;
                    continue;
            }
            if (code == 2) {
                System.out.println(2);
            }
        }
    }
}

