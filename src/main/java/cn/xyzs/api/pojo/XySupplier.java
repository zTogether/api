package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "XY_SUPPLIER")
public class XySupplier {
    @Setter
    @Getter
    @Column(name = "SUP_CODE")
    private String supCode;
    @Setter
    @Getter
    @Column(name = "SUP_NAME")
    private String supName;
    @Setter
    @Getter
    @Column(name = "SUP_STYLE")
    private String supStyle;
    @Setter
    @Getter
    @Column(name = "SUP_MNGER")
    private String supMnger;
    @Setter
    @Getter
    @Column(name = "SUP_TEL")
    private String supTel;
    @Setter
    @Getter
    @Column(name = "SUP_AREA")
    private String supArea;
    @Setter
    @Getter
    @Column(name = "SUP_BANKID_P")
    private String supBankidP;
    @Setter
    @Getter
    @Column(name = "SUP_BANKID_C")
    private String supBankidC;
    @Setter
    @Getter
    @Column(name = "SUP_ISUSED")
    private String supIsused;
    @Setter
    @Getter
    @Column(name = "SUP_REBATE")
    private String supRebate;
    @Setter
    @Getter
    @Column(name = "SUP_MARK")
    private String supMark;
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
    @Setter
    @Getter
    @Column(name = "SUP_N1")
    private String supN1;
}
