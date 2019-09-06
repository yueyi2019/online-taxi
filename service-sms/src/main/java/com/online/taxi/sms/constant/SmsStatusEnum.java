package com.online.taxi.sms.constant;

import lombok.Getter;

public enum SmsStatusEnum {
	
	/**
     * 操作成功
     */
    SEND_SUCCESS(1, "sms send success"),
    
    /**
     * 操作异常
     */
    INTERNAL_SERVER_EXCEPTION(0, "exception"),
	
    /**
     * 操作失败
     */
    SEND_FAIL(0, "sms send fail");
	
	@Getter
	private final int code;
	
	@Getter
    private final String value;
    
    private SmsStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

}