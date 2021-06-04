package com.hand.utils;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2019/2/26
 */
public class KeyUtilTest {

    @Test
    public void genUniqueKey() {
        System.out.println(KeyUtil.genUniqueKey());
    }
}