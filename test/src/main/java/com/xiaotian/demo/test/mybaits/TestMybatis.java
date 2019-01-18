package com.xiaotian.demo.test.mybaits;

import com.xiaotian.demo.test.mybaits.mapper.MenuMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.InputStream;


public class TestMybatis {

    public static void main(String[] args) throws Exception {
        SqlSessionFactory sqlSessionFactory = buildSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
        Integer count = menuMapper.selectCount();
        System.out.println(count);
    }

    private static SqlSessionFactory buildSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
