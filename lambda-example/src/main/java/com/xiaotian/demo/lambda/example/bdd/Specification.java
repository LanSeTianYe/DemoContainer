package com.xiaotian.demo.lambda.example.bdd;

@FunctionalInterface
public interface Specification {

    void specifyBehaviour(Expect expect);

}
