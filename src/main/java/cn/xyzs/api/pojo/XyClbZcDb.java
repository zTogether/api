package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.List;

@Table(name = "XY_CLB_ZC_DB")
public class XyClbZcDb {
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
    private Double zcPriceIn;
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_LOOK")
    private Double zcPirceLook;
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_OUT")
    private Double zcPriceOut;
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_HD")
    private Double zcPriceHd;
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
    @Column(name = "ZC_STYLE")
    private String zcStyle;
    @Setter
    @Getter
    @Column(name = "ZC_AREA")
    private String zcArea;
    @Setter
    @Getter
    @Column(name = "ZC_IS_NEW")
    private String zcIsNew;
    @Setter
    @Getter
    @Column(name = "ZC_IS_HOT")
    private String zcIshot;
    @Setter
    @Getter
    @Column(name = "ZC_UNIT")
    private String zcUnit;
    @Setter
    @Getter
    @Column(name = "ZC_DES")
    private String zcDes;
    @Setter
    @Getter
    @Column(name = "ZC_CYC")
    private Integer zcCyc;
    @Setter
    @Getter
    @Column(name = "ZC_IS_USED")
    private String zcIsUsed;
    @Setter
    @Getter
    @Column(name = "ZC_PRO_AREA")
    private String zcProArea;
    @Setter
    @Getter
    @Column(name = "ZC_VERSION")
    private String zcVersion;

    @Setter
    @Getter
    private List<XyVal> xyZcAreas;


}
