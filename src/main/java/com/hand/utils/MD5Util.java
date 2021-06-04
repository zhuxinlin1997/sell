package com.hand.utils;


import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description MD5加密
 * @date 2019/4/2
 */
public class MD5Util {

    public static String getMD5Code(String sourceString)throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64.Encoder base64Encoder = Base64.getEncoder();
        // 加密字符串
        return base64Encoder.encodeToString(md5.digest(sourceString.getBytes("utf-8")));
    }
}
