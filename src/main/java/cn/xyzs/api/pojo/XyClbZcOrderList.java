package cn.xyzs.api.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_CLB_ZC_ORDER_LIST")
public class XyClbZcOrderList {

    @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "ROW_ID")
    private String rowId;

    @Column(name = "ZC_CODE")
    private String zcCode;

    @Column(name = "ZC_NAME")
    private String zcName;

    @Column(name = "ZC_TYPE")
    private String zcType;

    @Column(name = "ZC_PRICE_IN")
    private String zcPriceIn;

    @Column(name = "ZC_PRICE_OUT")
    private String zcPriceOut;

    @Column(name = "ZC_QTY")
    private String zcQty;

    @Column(name = "ZC_BRAND")
    private String zcBrand;

    @Column(name = "ZC_SUP")
    private String zcSup;

    @Column(name = "ZC_SPEC")
    private String zcSpec;

    @Column(name = "ZC_MATERIAL")
    private String zcMaterial;

    @Column(name = "ZC_COLOR")
    private String zcColor;

    @Column(name = "ZC_UNIT")
    private String zcUnit;

    @Column(name = "ZC_MARK")
    private String zcMark;

    @Column(name = "ZC_CYC")
    private String zcCyc;

    @Column(name = "ZC_AREA")
    private String zcArea;

    @Column(name = "ZC_VERSION")
    private String zcVersion;

    @Column(name = "ZC_SHOP_STATUS")
    private String zcShopStatus;
}
