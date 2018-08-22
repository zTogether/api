package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "XY_USER")
public class XyUser {
    @Id
    @Setter
    @Getter
    @Column(name = "USER_ID")
    private String userId;
    @Setter
    @Getter
    @Column(name = "USER_CODE")
    private String userCode;
    @Setter
    @Getter
    @Column(name = "USER_NAME")
    private String userName;
    @Setter
    @Getter
    @Column(name = "USER_TEL")
    private String userTel;
    @Setter
    @Getter
    @Column(name = "PASSWORD")
    private String password;
    @Setter
    @Getter
    @Column(name = "USER_BTHD")
    private String userBthd;
    @Setter
    @Getter
    @Column(name = "USER_SEX")
    private String userSex;
    @Setter
    @Getter
    @Column(name = "IS_USED")
    private String isUsed;
    @Setter
    @Getter
    @Column(name = "ID_CARD")
    private String idCard;
    @Setter
    @Getter
    @Column(name = "BANK_ID_BC")
    private String bankIdBc;
    @Setter
    @Getter
    @Column(name = "USER_MARK1")
    private String userMark1;
    @Setter
    @Getter
    @Column(name = "USER_MARK2")
    private String userMark2;
    @Setter
    @Getter
    @Column(name = "USER_MARK3")
    private String userMark3;
    @Setter
    @Getter
    @Column(name = "MUST_CHANGE")
    private String mustChange;
    @Setter
    @Getter
    @Column(name = "BANK_ID_ICBC")
    private String bankIdIcbc;
    @Setter
    @Getter
    @Column(name = "BANK_ID_CMBC")
    private String bankIdCmbc;
}
