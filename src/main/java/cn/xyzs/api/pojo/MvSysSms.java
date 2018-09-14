package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "MV_SYS_SMS")
public class MvSysSms {

    //主键
    @Column(name = "SMS_ID")
    private String smsId;

    //手机号
    @Column(name = "TEL")
    private String tel;

    //验证码
    @Column(name = "VERIFICATION_CODE")
    private String verificationCode;

    //添加时间
    @Column(name = "ADD_TIME")
    private String addTime;

    //发送内容
    @Column(name = "SMS_CONTENT")
    private String smsContent;

    //发送人
    @Column(name = "SMS_SENDER")
    private String smsSender;

    //发送状态
    @Column(name = "SEND_STATUS")
    private String sendStatus;
}
