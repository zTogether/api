package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "XY_CLB_ZC_SHOPPING")
public class XyClbZcShopping {
    @Setter
    @Getter
    @Column(name = "ROW_ID")
    private String rowId;
    @Setter
    @Getter
    @Column(name = "CTR_CODE")
    private String ctrCode;
    @Setter
    @Getter
    @Column(name = "OP_USERID")
    private String opUserid;
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
    @Column(name = "ZC_QTY")
    private String zcQty;
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_IN")
    private String zcPriceIn;
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_OUT")
    private String zcPriceOut;
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
    private String zcCyc;
    @Setter
    @Getter
    @Column(name = "ZC_AREA")
    private String zcArea;
}
