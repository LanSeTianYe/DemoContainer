package com.sun.xiaotian.demo.test.juint5;

import org.junit.After;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

//定义类实例变量的生命周期
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@DisplayName("HelloWorldTest")
class HelloWorldTest {

    private InnerClass innerClass = new InnerClass();
    private Integer number = null;

    @BeforeAll
    void beforeAll() {
        System.out.println("before all: " + number);
        number = 0;
    }

    @AfterAll
    void afterAll() {
        number = null;
        System.out.println("after all: " + number);
    }

    @BeforeEach
    void before() {
        number++;
        System.out.println("before each: " + number);
    }

    @AfterEach
    void after() {
        System.out.println("after each: " + number);
    }

    @Test
    @DisplayName("😱")
    @Fast
    @Tag("a")
    void testHelloWorldAndEmoji() {
        System.out.println(innerClass.hashCode());
        System.out.println("Hello World!😱");
        assertEquals("a", "a");
    }


    @Test
    @Fast
    @Tag("a")
    void testAssertAll() {
        System.out.println(innerClass.hashCode());
        assertAll("assert all",
                () -> {
                    assertEquals("a", "a");
                },
                () -> {
                    assertEquals("a", "a");
                }
        );
    }

    @Test
    @Fast
    void testThrows() {
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException();
        });
    }

    @Test
    @Disabled
    @Fast
    void testMatcherAssert() {
        assertThat(2 + 1, is(equalTo(3)));
    }

    @Test
    @Fast
    void testAssumption() {
        assumeTrue("CI".equals(System.getenv("ENV")));
    }

    @Test
    @RepeatedTest(value = 3)
    void testParameterized() {
        System.out.println("repeat 2");
    }

    @Test
    @TestFactory
    void testTestFactory() {
        System.out.println("test factory");
    }

    private static class InnerClass {

    }
}