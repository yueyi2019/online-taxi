package com.online.taxi.sms.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.online.taxi.common.entity.SmsTemplate;

/**
 * @author yueyi2019
 */
@Mapper
public interface SmsTemplateMapper {
    /**
     * 根据id删除短信模板
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入短信模板
     * @param record
     * @return
     */
    int insert(SmsTemplate record);

    /**
     * 插入短信模板
     * @param record
     * @return
     */
    int insertSelective(SmsTemplate record);

    /**
     * 根据id查询短信模板
     * @param id
     * @return
     */
    SmsTemplate selectByPrimaryKey(Integer id);

    /**
     * 更新短信模板
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SmsTemplate record);

    /**
     * 更新短信模板
     * @param record
     * @return
     */
    int updateByPrimaryKey(SmsTemplate record);

    /**
     * 根据模板id，查询短信模板
     * @param templateId
     * @return
     */
    SmsTemplate selectByTemplateId(String templateId);
}