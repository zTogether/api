package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_CLB_FC_CKD_LIST")
public class XyClbFcCkdList {

    //编号
    @Column(name = "CKD_CODE")
    private String ckdCode;

    //唯一主键
    @Column(name = "CKD_ROW")
	private String ckdRow;

    //材料编号
    @Column(name = "FC_PRICE_ID")
	private String fcPriceId;

    //品牌
    @Column(name = "FC_PP")
	private String fcPp;

    //名称
    @Column(name = "FC_NAME")
	private String fcName;

    //单位
    @Column(name = "FC_UNIT")
	private String fcUnit;

    //数量
    @Column(name = "FC_QTY")
	private String fcQty;

    //价格
    @Column(name = "FC_PRICE")
	private String fcPrice;

    //金额
    @Column(name = "FC_JE")
	private String fcJe;

    //运费
    @Column(name = "FC_YF")
	private String fcYf;

    //
    @Column(name = "FC_PER")
    private String fcPer;

    //小计
    @Column(name = "FC_XJ")
	private String fcXj;

    //备注
    @Column(name = "FC_MARK")
	private String fcMark;

    //是否外采（1：外采   2：仓库）
    @Column(name = "FC_ISWC")
	private String fcIswc;

    @Column(name = "FC_ISEDIT")
	private String fcIsedit;

    @Column(name = "FC_PRINT_GROUP")
	private String fcPrintGroup;
}
