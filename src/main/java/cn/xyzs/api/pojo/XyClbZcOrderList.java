package cn.xyzs.api.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
/**
 *
 * @Description: 标准商品订单明细表
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 14:50
 * @param:
 * @return:
 */
@Data
@Table(name = "XY_CLB_ZC_ORDER_LIST")
public class XyClbZcOrderList {
    //订单编号
    @Column(name = "ORDER_ID")
    private String orderId;
    //流水号
    @Column(name = "ROW_ID")
    private String rowId;
    //商品编号
    @Column(name = "ZC_CODE")
    private String zcCode;
    //商品名称
    @Column(name = "ZC_NAME")
    private String zcName;
    //商品分类
    @Column(name = "ZC_TYPE")
    private String zcType;
    //进货价
    @Column(name = "ZC_PRICE_IN")
    private String zcPriceIn;
    //销售价
    @Column(name = "ZC_PRICE_OUT")
    private String zcPriceOut;
    //数量
    @Column(name = "ZC_QTY")
    private String zcQty;
    //品牌
    @Column(name = "ZC_BRAND")
    private String zcBrand;
    //供应商
    @Column(name = "ZC_SUP")
    private String zcSup;
    //规格
    @Column(name = "ZC_SPEC")
    private String zcSpec;
    //材质
    @Column(name = "ZC_MATERIAL")
    private String zcMaterial;
    //颜色
    @Column(name = "ZC_COLOR")
    private String zcColor;
    //计量单位
    @Column(name = "ZC_UNIT")
    private String zcUnit;
    //备注
    @Column(name = "ZC_MARK")
    private String zcMark;
    //订购周期
    @Column(name = "ZC_CYC")
    private String zcCyc;
    //区域
    @Column(name = "ZC_AREA")
    private String zcArea;
    //版本
    @Column(name = "ZC_VERSION")
    private String zcVersion;
    //状态
    @Column(name = "ZC_SHOP_STATUS")
    private String zcShopStatus;
}
