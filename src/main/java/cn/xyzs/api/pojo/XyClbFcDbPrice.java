package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name="XY_CLB_FC_DB_PRICE")
public class XyClbFcDbPrice {

    //价格码
    @Column(name = "FC_PRICE_CODE")
    private String fcPriceCode;

    //品牌Id
    @Column(name = "BRAND_ID")
	private String brandId;

    //进货价
    @Column(name = "FC_PRICE_IN")
	private String fcPriceIn;

    //出货价
    @Column(name = "FC_PRICE_OUT")
	private String fcPriceOut;

    //变更时间
    @Column(name = "FC_PRICE_DATE")
	private String fcPriceDate;

    //是否启用(1:可用   2：不可用)
    @Column(name = "FC_PRICE_ISUSED")
	private String fcPriceIsused;

    //说明
    @Column(name = "FC_PRICE_MARK")
	private String fcPriceMark;

    //备用1
    @Column(name = "FC_PRICE_C1")
	private String fcPriceC1;

    //备用2
    @Column(name = "FC_PRICE_C2")
	private String fcPriceC2;

    //备用3
    @Column(name = "FC_PRICE_N1")
	private String fcPriceN1;

    //备用4
    @Column(name = "FC_PRICE_N2")
	private String fcPriceN2;
}
