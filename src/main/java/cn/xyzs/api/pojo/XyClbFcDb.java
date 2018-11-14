package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_CLB_FC_DB")
public class XyClbFcDb {

    //辅材主键Id
    @Column(name = "FC_CODE")
    private String fcCode;

    //辅材名称
    @Column(name = "FC_NAME")
	private String fcName;

    //规格型号
    @Column(name = "FC_SPEC")
	private String fcSpec;

    //单位
    @Column(name = "FC_UNIT")
	private String fcUnit;

    //辅材材料分类（关联XY_VAL表）
    @Column(name = "FC_TYPE")
	private String fcType;

    //保质期
    @Column(name = "FC_LIFE")
	private String fcLife;

    //采购形式（1：外采   2：仓库）
    @Column(name = "FC_PUR_STY")
	private String fcPurSty;

    //进货预警
    @Column(name = "FC_PUR_WAR")
	private String fcPurWar;

    //最低库存预警
    @Column(name = "FC_LOW_WAR")
	private String fcLowWar;

    //是否可用（0：不可用     1：）
    @Column(name = "FC_ISUSED")
	private String fcIsused;

    //照片
    @Column(name = "FC_IMG")
	private String fcImg;

    //备注
    @Column(name = "FC_MARK")
	private String fcMark;

    //备用1
    @Column(name = "FC_C1")
	private String fcC1;

    //备用2
    @Column(name = "FC_C2")
	private String fcC2;

    //备用3
    @Column(name = "FC_C3")
	private String fcC3;

    //备用4
    @Column(name = "FC_N4")
	private String fcN4;
}
