package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
/***
 *
 * @Description: 供应商信息表
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 15:39
 * @param:
 * @return:
 */
@Table(name = "XY_SUPPLIER")
public class XySupplier {
    //编号
    @Setter
    @Getter
    @Column(name = "SUP_CODE")
    private String supCode;
    //名称
    @Setter
    @Getter
    @Column(name = "SUP_NAME")
    private String supName;
    //供应商类别
    @Setter
    @Getter
    @Column(name = "SUP_STYLE")
    private String supStyle;
    //联系人
    @Setter
    @Getter
    @Column(name = "SUP_MNGER")
    private String supMnger;
    //电话
    @Setter
    @Getter
    @Column(name = "SUP_TEL")
    private String supTel;
    //区域
    @Setter
    @Getter
    @Column(name = "SUP_AREA")
    private String supArea;
    //个人账户
    @Setter
    @Getter
    @Column(name = "SUP_BANKID_P")
    private String supBankidP;
    //对公账户
    @Setter
    @Getter
    @Column(name = "SUP_BANKID_C")
    private String supBankidC;
    //是否启用
    @Setter
    @Getter
    @Column(name = "SUP_ISUSED")
    private String supIsused;
    //佣金比例
    @Setter
    @Getter
    @Column(name = "SUP_REBATE")
    private String supRebate;
    //备注
    @Setter
    @Getter
    @Column(name = "SUP_MARK")
    private String supMark;
    //备用1
    @Setter
    @Getter
    @Column(name = "SUP_C1")
    private String supC1;
    @Setter
    @Getter
    @Column(name = "SUP_C2")
    private String supC2;
    @Setter
    @Getter
    @Column(name = "SUP_C3")
    private String supC3;
    //备用4
    @Setter
    @Getter
    @Column(name = "SUP_N1")
    private String supN1;
}
