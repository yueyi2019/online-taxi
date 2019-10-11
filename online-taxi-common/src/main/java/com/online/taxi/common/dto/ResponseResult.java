package com.online.taxi.common.dto;

import java.io.Serializable;

import com.online.taxi.common.constant.CommonStatusEnum;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 通用返回值处理类
 *
 */
@Data
@Accessors(chain = true)
@SuppressWarnings("all")
public class ResponseResult<T> implements Serializable {

	private static final long serialVersionUID = -2073390651767969040L;
	
	private int code;
    private String message;
    private T data;
    
	/**
     * 返回成功数据（status：1）
     *
     * @param data 数据内容
     * @param <T>  数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult success(T data) {
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }

    /**
     * 返回错误数据（status：500）
     *
     * @param data 错误内容
     * @param <T>  数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult fail(T data) {
        return new ResponseResult().setCode(CommonStatusEnum.INTERNAL_SERVER_EXCEPTION.getCode()).setMessage(CommonStatusEnum.INTERNAL_SERVER_EXCEPTION.getValue()).setData(data);
    }

    /**
     * 自定义返回错误数据
     *
     * @param code    错误代码
     * @param message 错误消息
     * @return ResponseResult实例
     */
    public static ResponseResult fail(int code, String message) {
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * @param code    错误代码
     * @param message 错误消息
     * @param data    错误内容
     * @return ResponseResult实例
     */
    public static ResponseResult fail(int code, String message, String data) {
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
}