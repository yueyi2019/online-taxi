package com.online.taxi.common.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
	
	/**
	 * 生成32位md5
	 * @param src
	 * @return
	 */
	public static String getMd5(String src) {
		return DigestUtils.md5Hex(src);
	}
	
	public static void main(String[] args) {
		System.out.println(getMd5("我"));
	}
}
