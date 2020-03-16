package com.online.taxi.three.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.taxi.three.entity.Three;
import com.online.taxi.three.mapper.ThreeMapper;

/**
 * @author yueyi2019
 */
@Service
public class RmThreeService {
	@Autowired
	ThreeMapper mapper;
	
	public String rm3() {
		
		Three o = new Three();
		o.setId(3);
		o.setName("rm3");
		mapper.insertSelective(o);
		
		return "";
	}
}
