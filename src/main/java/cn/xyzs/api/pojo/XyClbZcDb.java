package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.List;
/***
 *
 * @Description: 主材库
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 14:31
 * @param:
 * @return:
 */
@Table(name = "XY_CLB_ZC_DB")
public class XyClbZcDb {
    //编码
    @Setter
    @Getter
    @Column(name = "ZC_CODE")
    private String zcCode;
    //主材名称
    @Setter
    @Getter
    @Column(name = "ZC_NAME")
    private String zcName;
    //分类
    @Setter
    @Getter
    @Column(name = "ZC_TYPE")
    private String zcType;
    //进货价
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_IN")
    private String zcPriceIn;
    //挂牌价
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_LOOK")
    private String zcPirceLook;
    //销售价
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_OUT")
    private String zcPriceOut;
    //活动价
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_HD")
    private String zcPriceHd;
    //品牌
    @Setter
    @Getter
    @Column(name = "ZC_BRAND")
    private String zcBrand;
    //供应商
    @Setter
    @Getter
    @Column(name = "ZC_SUP")
    private String zcSup;
    //规格
    @Setter
    @Getter
    @Column(name = "ZC_SPEC")
    private String zcSpec;
    //材质
    @Setter
    @Getter
    @Column(name = "ZC_MATERIAL")
    private String zcMaterial;
    //颜色
    @Setter
    @Getter
    @Column(name = "ZC_COLOR")
    private String zcColor;
    //适用风格
    @Setter
    @Getter
    @Column(name = "ZC_STYLE")
    private String zcStyle;
    //区域
    @Setter
    @Getter
    @Column(name = "ZC_AREA")
    private String zcArea;
    //是否新品
    @Setter
    @Getter
    @Column(name = "ZC_IS_NEW")
    private String zcIsNew;
    //是否主推
    @Setter
    @Getter
    @Column(name = "ZC_IS_HOT")
    private String zcIshot;
    //计量单位
    @Setter
    @Getter
    @Column(name = "ZC_UNIT")
    private String zcUnit;
    //描述
    @Setter
    @Getter
    @Column(name = "ZC_DES")
    private String zcDes;
    //订购周期
    @Setter
    @Getter
    @Column(name = "ZC_CYC")
    private String zcCyc;
    //是否启用
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
    //区域
    @Setter
    @Getter
    private List<XyVal> xyZcAreas;


}
