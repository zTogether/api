package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_BJD_FC_LIST")
public class XyBjdFcList {

    //报价单Code
    @Column(name = "BJD_CODE")
    private String bjdCode;

    //材料唯一值
    @Column(name = "BJD_FC_ROWID")
	private String bjdFcRowid;

    //辅材分类
    @Column(name = "BJD_FC_STAGE")
	private String bjdFcStage;

    //报价单辅材序号
    @Column(name = "BJD_FC_NO")
	private String bjdFcNo;

    //
    @Column(name = "FC_PRICE_CODE")
	private String fcPriceCode;

    //辅材项目名称
    @Column(name = "FC_NAME")
	private String fcName;

    //辅材品牌
    @Column(name = "BRAND_NAME")
	private String brandName;

    //
    @Column(name = "FC_SPEC")
	private String fcSpec;

    //计量单位
    @Column(name = "FC_UNIT")
	private String fcCnit;

    //数量
    @Column(name = "FC_QTY")
	private String fcQty;

    //辅材单价
    @Column(name = "FC_PRICE")
    private String fcPrice;

    //小计
    @Column(name = "FC_XJ")
    private String fcXj;

    //备注
    @Column(name = "FC_MARK")
    private String fcMark;

    //
    @Column(name = "FC_YN")
	private String fcYn;
}
