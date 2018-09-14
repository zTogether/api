package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 *
 * @Description: 订单主表
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 14:46
 * @param:
 * @return:
 */
@Table(name = "XY_CLB_ZC_ORDER")
public class XyClbZcOrder {
    //单据号
    @Id
    @Setter
    @Getter
    @Column(name = "ORDER_ID")
    private String orderId;
    //销售人
    @Setter
    @Getter
    @Column(name = "OP_USERID")
    private String opUserid;
    //总额
    @Setter
    @Getter
    @Column(name = "ORDER_JE")
    private String orderJe;
    //备注
    @Setter
    @Getter
    @Column(name = "ORDER_MARK")
    private String orderMark;
    //状态
    @Setter
    @Getter
    @Column(name = "ORDER_STATUS")
    private String orderStatus;
    //类型
    @Setter
    @Getter
    @Column(name = "ORDER_TYPE")
    private String orderType;
    //供应商
    @Setter
    @Getter
    @Column(name = "ORDER_SUP")
    private String orderSup;
    //折扣
    @Setter
    @Getter
    @Column(name = "ORDER_DIS")
    private String orderDis;
    //编辑类型
    @Setter
    @Getter
    @Column(name = "ORDER_TYPE")
    private String editType;
    //优惠备注
    @Setter
    @Getter
    @Column(name = "ORDER_DIS_MARK")
    private String orderDisMark;
    //是否退货单
    @Setter
    @Getter
    @Column(name = "ORDER_ISRETURN")
    private String orderIsreturn;
    //订单时间
    @Setter
    @Getter
    @Column(name = "ORDER_DATE")
    private String orderDate;
    //客户编号
    @Setter
    @Getter
    @Column(name = "CTR_CODE")
    private String ctrCode;

}
