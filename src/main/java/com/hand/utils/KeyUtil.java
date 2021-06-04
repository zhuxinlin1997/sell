package com.hand.utils;

import java.util.Random;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description 生成随机字符
 * @date 2019/2/26
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间 + 随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        return ""+System.currentTimeMillis() + (new Random().nextInt(900000)+100000);
    }
}
