package com.online.taxi.sms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.taxi.common.dto.sms.SmsSendRequest;
import com.online.taxi.common.dto.sms.SmsTemplateDto;
import com.online.taxi.common.entity.Sms;
import com.online.taxi.common.entity.SmsTemplate;
import com.online.taxi.sms.constant.SmsStatusEnum;
import com.online.taxi.sms.dao.SmsDao;
import com.online.taxi.sms.dao.SmsTemplateDao;
import com.online.taxi.sms.service.AliService;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Service
@Slf4j
public class AliServiceImpl implements AliService {
	
	// 缓存用于替换内容的模板
    private Map<String, String> templateMaps = new HashMap<String, String>();
    
    @Autowired
    private SmsTemplateDao smsTemplateDto;
    
    @Autowired
    private SmsDao smsDao;

    @Override
    public int sendSms(SmsSendRequest request) {
        log.info(request.toString());

        for (String phoneNumber : request.getReceivers()) {
            List<SmsTemplateDto> templates = request.getData();
            
            Sms sms = new Sms();
            sms.setPhoneNumber(phoneNumber);

            for (SmsTemplateDto template : templates) {
                // 从DB加载模板内容至缓存
                if (!templateMaps.containsKey(template.getId())) {
                	//此处注释掉的内容为，将db模板加载到内存
                	SmsTemplate t = smsTemplateDto.getByTemplateId(template.getId());
                	System.out.println(t.getContent());
                    templateMaps.put(template.getId(),
                    		smsTemplateDto.getByTemplateId(template.getId()).getContent());
                }
                
                // 替换占位符
                String content = templateMaps.get(template.getId());
                for (Map.Entry<String, Object> entry : template.getTemplateMap().entrySet()) {
                    content = StringUtils.replace(content, "${" + entry.getKey() + "}", "" + entry.getValue());
                }

                // 发生错误时，不影响其他手机号和模板的发送
                try {
                    int result = send(phoneNumber, template.getId(), template.getTemplateMap());
                    
                    // 组装SMS对象
                    sms.setSendTime(new Date());
                    sms.setOperator("");
                    sms.setSendFlag(1);
                    sms.setSendNumber(0);
                    sms.setSmsContent(content);

                    if (result != SmsStatusEnum.SEND_SUCCESS.getCode()) {
                        throw new Exception("短信发送失败");
                    }
                } catch (Exception e) {
                    sms.setSendFlag(0);
                    sms.setSendNumber(1);
                    log.error("发送短信（" + template.getId() + "）失败：" + phoneNumber, e);
                } finally {
                    sms.setCreateTime(new Date());
                    smsDao.insert(sms);
                }
            }
        }
        return 0;
    }

    private int send(String phoneNumber, String templateId, Map<String, ?> map) throws Exception {
        JSONObject param = new JSONObject();
        param.putAll(map);

        return sendMsg(phoneNumber, templateId, param.toString());
    }
    
    private int sendMsg(String phoneNumber, String templateCode, String param) {
        /**
    	try {
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phoneNumber);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(sign);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(param);
            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            log.info("短信结果："+JSONObject.fromObject(sendSmsResponse));
            // 解析发送结果
            if (sendSmsResponse != null && sendSmsResponse.getCode().trim().equals("OK")) {
                System.out.println(sendSmsResponse.getBizId());
                log.info("阿里短信通道 成功 - 手机号码 - " + phoneNumber + " 内容 " + param);
                return SmsEnum.OK.getCode();
            } else {
                log.info("阿里短信通道 失败 - 手机号码 - " + phoneNumber + " 内容 " + param);
                return SmsEnum.FAIL.getCode();
            }
        }catch (Exception e){
            return SmsEnum.EXCEPTION.getCode();
        }
    	*/
    	return SmsStatusEnum.SEND_SUCCESS.getCode();

    }
}
