package com.sun.xiaotian.demo.springboot.demo.person;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Answers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.*;

public class MockTest {

    @Test
    public void testMock() {
        PersonService mock = mock(PersonService.class);
        List<Person> personList = new ArrayList<>();

        //返回数值
        personList.add(new Person());
        personList.add(new Person());
        personList.add(new Person());
        when(mock.getAll()).thenReturn(personList);
        //抛出异常
        when(mock.getName()).thenThrow(new RuntimeException());

        for (int i = 0; i < 10; i++) {
            mock.getAll();
        }
        //验证调用多少次
        verify(mock, atLeast(10)).getAll();
    }

    @Test
    public void testIterator() {
        Iterator mockIterator = mock(Iterator.class);
        //第一次返回true，后面都返回false
        when(mockIterator.hasNext()).thenReturn(true).thenReturn(false);
        Assert.assertTrue(mockIterator.hasNext());
        Assert.assertFalse(mockIterator.hasNext());
        Assert.assertFalse(mockIterator.hasNext());
    }

    @Test(expected = IOException.class)
    public void thenThrow() throws IOException {
        OutputStream outputStream = mock(OutputStream.class);
        //没有返回值的方法调用
        doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }

    @Test
    public void testNull() {
        //调用对象里的对象不会抛出空指针异常
        Human mock = mock(Human.class, Answers.RETURNS_DEEP_STUBS);
        when(mock.getAddress().getDetailAddress()).thenReturn("China");
        System.out.println(mock.getAddress().getDetailAddress());
        verify(mock.getAddress()).getDetailAddress();

        //默认值，常量默认值，非常量返回提示字符串
        Human smartNull = mock(Human.class, Answers.RETURNS_SMART_NULLS);
        System.out.println(smartNull.getName());
        System.out.println(smartNull.getAge());
    }

    public static class Human {
        private String name;
        private int age;
        private Address address;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public Address getAddress() { return address; }
        public void setAddress(Address address) { this.address = address; }

        public static class Address {
            private String detailAddress;
            public String getDetailAddress() { return detailAddress; }
            public void setDetailAddress(String detailAddress) { this.detailAddress = detailAddress; }
        }
    }
}