package com.hand.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description Json工具类
 * @date 2019/3/10
 */
public class JsonUtil {

    public static String toJson(Object object){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat(DateFormat.LONG)
                .create();
        return gson.toJson(object);
    }
}
