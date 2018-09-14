package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 *
 * @Description: 用户类
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 14:05
 * @param:
 * @return:
 */
@Table(name = "XY_USER")
public class XyUser {
    //员工id
    @Id
    @Setter
    @Getter
    @Column(name = "USER_ID")
    private String userId;
    //员工号
    @Setter
    @Getter
    @Column(name = "USER_CODE")
    private String userCode;
    //员工姓名
    @Setter
    @Getter
    @Column(name = "USER_NAME")
    private String userName;
    //员工电话
    @Setter
    @Getter
    @Column(name = "USER_TEL")
    private String userTel;
    //密码
    @Setter
    @Getter
    @Column(name = "PASSWORD")
    private String password;
    //出生日期
    @Setter
    @Getter
    @Column(name = "USER_BTHD")
    private String userBthd;
    //性别
    @Setter
    @Getter
    @Column(name = "USER_SEX")
    private String userSex;
    //是否可用
    @Setter
    @Getter
    @Column(name = "IS_USED")
    private String isUsed;
    //身份证号
    @Setter
    @Getter
    @Column(name = "ID_CARD")
    private String idCard;
    //中国银行卡号
    @Setter
    @Getter
    @Column(name = "BANK_ID_BC")
    private String bankIdBc;
    //员工备注1
    @Setter
    @Getter
    @Column(name = "USER_MARK1")
    private String userMark1;
    //备注2
    @Setter
    @Getter
    @Column(name = "USER_MARK2")
    private String userMark2;
    //备注3
    @Setter
    @Getter
    @Column(name = "USER_MARK3")
    private String userMark3;
    //
    @Setter
    @Getter
    @Column(name = "MUST_CHANGE")
    private String mustChange;
    //中国工商银行卡号
    @Setter
    @Getter
    @Column(name = "BANK_ID_ICBC")
    private String bankIdIcbc;
    //招商银行卡号
    @Setter
    @Getter
    @Column(name = "BANK_ID_CMBC")
    private String bankIdCmbc;
}
