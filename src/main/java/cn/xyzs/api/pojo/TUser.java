package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 用户类
 */
@Table(name="XY_USER")
public class TUser {
	//用户唯一标识ID
	
	@Id
    @Getter
    @Setter
    private String userId;
    //用户登录名
    @Column(name="USER_CODE")
    @Getter
    @Setter
    private String userCode;
    //用户真实名
    @Getter
    @Setter
    private String userName;
    //用户电话
    @Getter
    @Setter
    @Column(name = "USER_TEL")
    private String userTel;
    //用户密码
    @Getter
    @Setter
    private String password;
    //生日
    @Getter
    @Setter
    private String userBthd;
    //性别
    @Getter
    @Setter
    private String userSex;
    //是否启用
    @Getter
    @Setter
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