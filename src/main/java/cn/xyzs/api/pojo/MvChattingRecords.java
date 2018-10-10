package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "MV_CHATTING_RECORDS")
public class MvChattingRecords {
    //客户档案号
    @Column(name = "CTR_CODE")
    private String ctrCode;

    //用户id
    @Column(name = "USER_ID")
    private String userId;

    //发送时间
    @Column(name = "SEND_DATE")
    private String sendDate;

    //发送内容
    @Column(name = "CHATING_CONTENT")
    private String chatingContent;

    //内容类型
    @Column(name = "CONTENT_TYPE")
    private String contentType;

}
