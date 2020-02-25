package com.online.taxi.verificationcode.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class GitConfig {

	@Value("${env}")
	private String env;
}
