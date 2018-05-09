package com.sun.xiaotian.demo.zookeeper;

import org.apache.jute.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TestJute implements Record {

    private String name;

    private int age;

    public TestJute() {

    }

    public TestJute(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void serialize(OutputArchive archive, String tag) throws IOException {
        archive.startRecord(this, tag);
        archive.writeString(name,"name");
        archive.writeInt(age, "age");
        archive.endRecord(this, tag);
    }

    @Override
    public void deserialize(InputArchive archive, String tag) throws IOException {
        archive.startRecord(tag);
        name = archive.readString("name");
        age = archive.readInt("age");
        archive.endRecord(tag);
    }

    public static void main(String[] args) throws IOException {
        TestJute testJute = new TestJute("test", 25);
        String tag = "tag";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BinaryOutputArchive outputArchive = BinaryOutputArchive.getArchive(byteArrayOutputStream);
        testJute.serialize(outputArchive, tag);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        BinaryInputArchive inputArchive = BinaryInputArchive.getArchive(byteArrayInputStream);

        TestJute deserializeTestJute = new TestJute();
        deserializeTestJute.deserialize(inputArchive, tag);
        System.out.println(deserializeTestJute);
    }

    @Override
    public String toString() {
        return "TestJute{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
