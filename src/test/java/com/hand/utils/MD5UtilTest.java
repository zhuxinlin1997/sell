package com.hand.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.transform.Source;

import static org.junit.Assert.*;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/4/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MD5UtilTest {

    @Test
    public void getMD5Code() {
        try {
            String md5Code = MD5Util.getMD5Code("111111");
            System.out.println(md5Code);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}