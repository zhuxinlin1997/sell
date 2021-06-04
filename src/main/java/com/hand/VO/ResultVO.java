package com.hand.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author person.xinlin.zhu@hand-china.com
 * @version 1.0
 * @name
 * @description http请求返回的最外层对象
 * @date 2019/2/23
 */
@Data
public class ResultVO<T> implements Serializable{

    private static final long serialVersionUID = -1565191362815389371L;
    /** 错误码. */
    private Integer code;

    private String msg;

    /** 返回的具体内容 */
    private T data;
}
