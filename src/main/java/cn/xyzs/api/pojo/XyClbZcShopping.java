package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
/***
 *
 * @Description: 购物车
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 14:59
 * @param:
 * @return:
 */
@Table(name = "XY_CLB_ZC_SHOPPING")
public class XyClbZcShopping {
    //流水号
    @Setter
    @Getter
    @Column(name = "ROW_ID")
    private String rowId;
    //客户号
    @Setter
    @Getter
    @Column(name = "CTR_CODE")
    private String ctrCode;
    //销售人员
    @Setter
    @Getter
    @Column(name = "OP_USERID")
    private String opUserid;
    //商品编号
    @Setter
    @Getter
    @Column(name = "ZC_CODE")
    private String zcCode;
    //名称
    @Setter
    @Getter
    @Column(name = "ZC_NAME")
    private String zcName;
    //商品分类
    @Setter
    @Getter
    @Column(name = "ZC_TYPE")
    private String zcType;
    //数量
    @Setter
    @Getter
    @Column(name = "ZC_QTY")
    private String zcQty;
    //进货价
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_IN")
    private String zcPriceIn;
    //销售价
    @Setter
    @Getter
    @Column(name = "ZC_PRICE_OUT")
    private String zcPriceOut;
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
    //计量单位
    @Setter
    @Getter
    @Column(name = "ZC_UNIT")
    private String zcUnit;
    //备注
    @Setter
    @Getter
    @Column(name = "ZC_MARK")
    private String zcMark;
    //订购周期
    @Setter
    @Getter
    @Column(name = "ZC_CYC")
    private String zcCyc;
    //区域
    @Setter
    @Getter
    @Column(name = "ZC_AREA")
    private String zcArea;
}
