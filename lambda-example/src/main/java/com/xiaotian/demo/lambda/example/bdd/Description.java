package com.xiaotian.demo.lambda.example.bdd;

public class Description {

    private final String description;

    public Description(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void should(String exceptDescription, Specification specification) {
        try {
            System.out.println(description);
            Expect expect = new Expect(exceptDescription);
            specification.specifyBehaviour(expect);
            System.out.println(exceptDescription);
            System.out.println("pass");
        } catch (AssertionError error) {
            System.out.println("failure");
            System.out.println(error.getMessage());
        } catch (Throwable e) {
            System.out.println("failure");
            System.out.println("非预期的异常: " + e.getMessage());
        }
    }
}
