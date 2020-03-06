package com.online.taxi.zuul.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 密文黑名单
 * 此例子中用set，实际应该用redis，多个网关共享此信息。用redis中的set
 * @author yueyi2019
 *
 */
public class CiphertextBlackList {
	
	public static Set<String> blackList = new HashSet<>();
	
	public boolean add(String cipher) {
		return blackList.add(cipher);
	}
	
	public boolean check(String cipher) {
		if(!blackList.contains(cipher)) {
			return true;
		}
		return false;
	}
}
