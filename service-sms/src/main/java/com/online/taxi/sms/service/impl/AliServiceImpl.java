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
/**
 * @author yueyi2019
 */
@Service
@Slf4j
public class AliServiceImpl implements AliService {
	
	/**
	*   缓存用于替换内容的模板
	 */
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
         * 按照短信供应商的api编写即可
    	*/
    	return SmsStatusEnum.SEND_SUCCESS.getCode();

    }
}
