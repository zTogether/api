package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "XY_CLB_ZC_ORDER_LIST")
public class XyClbZcOrderList {
    @Setter
    @Getter
    @Column(name = "ORDER_ID")
    private String orderId;
    @Setter
    @Getter
    @Column(name = "ROW_ID")
    private String rowId;
    @Setter
    @Getter
    @Column(name = "ZC_CODE")
    private String zcCode;
    @Setter
    @Getter
    @Column(name = "ZC_NAME")
    private String zcName;
    @Setter
    @Getter
    @Column(name = "ZC_TYPE")
    private String zcType;
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_IN")
    private Integer zcPriceIn;
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_OUT")
    private Integer zcPriceOut;
    @Setter
    @Getter
    @Column(name = "ZC_QTY")
    private Integer zcQty;
    @Setter
    @Getter
    @Column(name = "ZC_BRAND")
    private String zcBrand;
    @Setter
    @Getter
    @Column(name = "ZC_SUP")
    private String zcSup;
    @Setter
    @Getter
    @Column(name = "ZC_SPEC")
    private String zcSpec;
    @Setter
    @Getter
    @Column(name = "ZC_MATERIAL")
    private String zcMaterial;
    @Setter
    @Getter
    @Column(name = "ZC_COLOR")
    private String zcColor;
    @Setter
    @Getter
    @Column(name = "ZC_UNIT")
    private String zcUnit;
    @Setter
    @Getter
    @Column(name = "ZC_MARK")
    private String zcMark;
    @Setter
    @Getter
    @Column(name = "ZC_CYC")
    private Integer zcCyc;
    @Setter
    @Getter
    @Column(name = "ZC_AREA")
    private String zcArea;
    @Setter
    @Getter
    @Column(name = "ZC_VERSION")
    private String zcVersion;
    @Setter
    @Getter
    @Column(name = "ZC_SHOP_STATUS")
    private String zcShopStatus;
}
