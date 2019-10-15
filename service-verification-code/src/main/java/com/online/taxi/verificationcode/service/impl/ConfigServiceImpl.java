package com.online.taxi.verificationcode.service.impl;

import com.online.taxi.verificationcode.service.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author yueyi2019
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    private String env1;

    private String env2;

    @Override
    public String getEnv1() {
        env1 = "env1";
        env2 = "2";
        return env1;
    }

    @Override
    public String getEnv2() {

        return "env2:"+env2;
    }
}
