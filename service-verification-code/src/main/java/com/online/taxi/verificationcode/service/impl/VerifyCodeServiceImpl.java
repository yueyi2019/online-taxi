package com.online.taxi.verificationcode.service.impl;

import com.online.taxi.common.constant.CommonStatusEnum;
import com.online.taxi.common.constant.IdentityConstant;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.verificationcode.VerifyCodeResponse;
import com.online.taxi.verificationcode.constant.VerifyCodeConstant;
import com.online.taxi.verificationcode.service.VerifyCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
/**
 * @author yueyi2019
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ResponseResult generate(int identity , String phoneNumber){

        //校验 发送时限
        checkSendCodeTimeLimit(phoneNumber);

        //生成6位code
        String code = String.valueOf((int)((Math.random()*9+1)*100000));

        //生成redis key
        String keyPre = generateKeyPreByIdentity(identity);
        String key = keyPre + phoneNumber;
        //存redis，2分钟过期
        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);
        codeRedis.set(code);
        codeRedis.expire(120, TimeUnit.SECONDS);

        //返回
        VerifyCodeResponse result = new VerifyCodeResponse();
        result.setCode(code);
        return ResponseResult.success(result);
    }

    @Override
    public ResponseResult verify(int identity,String phoneNumber,String code){
        //三档验证


        //生成redis key
        String keyPre = generateKeyPreByIdentity(identity);
        String key = keyPre + phoneNumber;
        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);
        String redisCode = codeRedis.get();

        if(StringUtils.isNotBlank(code)
                && StringUtils.isNotBlank(redisCode)
                && code.trim().equals(redisCode.trim())) {
            return ResponseResult.success(null);
        }else {
            return ResponseResult.fail(CommonStatusEnum.VERIFY_CODE_ERROR.getCode(), CommonStatusEnum.VERIFY_CODE_ERROR.getValue());
        }

    }

    /**
     * 根据身份类型生成对应的缓存key
     * @param identity
     * @return
     */
    private String generateKeyPreByIdentity(int identity){
        String keyPre = "";
        if (identity == IdentityConstant.PASSENGER){
            keyPre = VerifyCodeConstant.PASSENGER_LOGIN_KEY_PRE;
        }else if (identity == IdentityConstant.DRIVER){
            keyPre = VerifyCodeConstant.DRIVER_LOGIN_KEY_PRE;
        }
        return keyPre;
    }

    /**
     * 判断此手机号发送时限限制
     * @param phoneNumber
     * @return
     */
    private ResponseResult checkSendCodeTimeLimit(String phoneNumber){
        //判断是否有 限制1分钟，10分钟，24小时。

        return ResponseResult.success("");
    }

    /**
     * 三档验证校验
     * @param phoneNumber
     * @param code
     * @return
     */
    private ResponseResult checkCodeThreeLimit(String phoneNumber,String code){
        //看流程图

        return ResponseResult.success("");
    }
}
