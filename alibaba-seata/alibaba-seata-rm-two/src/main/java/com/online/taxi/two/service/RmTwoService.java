package com.online.taxi.two.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.taxi.two.entity.Two;
import com.online.taxi.two.mapper.TwoMapper;

/**
 * @author yueyi2019
 */
@Service
public class RmTwoService {
	
	@Autowired
	TwoMapper mapper;
	
	public String rm2() {
		Two o = new Two();
		o.setId(2);
		o.setName("rm2");
		mapper.insertSelective(o);
		
		return "";
	}
}
