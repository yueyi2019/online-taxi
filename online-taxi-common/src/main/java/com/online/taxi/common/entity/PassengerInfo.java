package com.online.taxi.common.entity;

import java.util.Date;
/**
 * @author yueyi2019
 */
public class PassengerInfo {
    private Integer id;

    private String phoneNumber;

    private Date birthday;

    private String passengerName;

    private Byte gender;

    private String headImg;

    private Byte passengerType;

    private Short registerType;

    private Byte lastLoginMethod;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName == null ? null : passengerName.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public Byte getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(Byte passengerType) {
        this.passengerType = passengerType;
    }

    public Short getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Short registerType) {
        this.registerType = registerType;
    }

    public Byte getLastLoginMethod() {
        return lastLoginMethod;
    }

    public void setLastLoginMethod(Byte lastLoginMethod) {
        this.lastLoginMethod = lastLoginMethod;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}