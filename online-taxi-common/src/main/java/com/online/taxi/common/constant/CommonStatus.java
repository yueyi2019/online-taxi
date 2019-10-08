package com.online.taxi.common.constant;

import lombok.Getter;

public enum CommonStatus {
	
	/**短信验证码服务	10001-10099*/
	VERIFY_CODE_ERROR(10001,"短信验证码验证失败"),
	
	
	/**
     * 	操作成功
     */
    SUCCESS(1, "success"),
    
    /**
     * 	操作异常
     */
    INTERNAL_SERVER_EXCEPTION(0, "exception"),
	
    /**
     * 	操作失败
     */
    FAIL(0, "fail");
	
	@Getter
	private final int code;
	
	@Getter
    private final String value;
    
    private CommonStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

}
