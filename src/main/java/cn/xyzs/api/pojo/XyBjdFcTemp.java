package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_BJD_FC_TEMP")
public class XyBjdFcTemp {

    //档案号
    @Column(name = "CTR_CODE")
    private String ctrCode;

    //
    @Column(name = "RG_VER_CODE")
    private String rgVerCode;

    //工程分类
    @Column(name = "RG_STAGE")
	private String rgStage;

    @Column(name = "FC_TYPE")
	private String fcType;

    @Column(name = "S_NAME")
	private String sName;

    @Column(name = "S_VAL")
	private String sVal;

    @Column(name = "S_PR")
	private String sPr;

    @Column(name = "IS_VISIBAL")
	private String isVisibal;

    @Column(name = "CON_RG")
	private String conRg;

    //辅材名称
    @Column(name = "FC_NAME")
	private String fcName;

    //品牌
    @Column(name = "FC_PP")
	private String fcPp;

    //单价
    @Column(name = "FC_PRICE")
	private String fcPrice;

    //单位
    @Column(name = "FC_UNIT")
	private String fcUnit;

    //辅材备注
    @Column(name = "FC_MARK")
	private String fcMark;
}
