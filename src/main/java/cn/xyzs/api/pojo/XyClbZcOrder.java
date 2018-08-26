package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "XY_CLB_ZC_ORDER")
public class XyClbZcOrder {
    @Id
    @Setter
    @Getter
    @Column(name = "ORDER_ID")
    private String orderId;

    @Setter
    @Getter
    @Column(name = "OP_USERID")
    private String opUserid;

    @Setter
    @Getter
    @Column(name = "ORDER_JE")
    private String orderJe;

    @Setter
    @Getter
    @Column(name = "ORDER_MARK")
    private String orderMark;

    @Setter
    @Getter
    @Column(name = "ORDER_STATUS")
    private String orderStatus;

    @Setter
    @Getter
    @Column(name = "ORDER_TYPE")
    private String orderType;

    @Setter
    @Getter
    @Column(name = "ORDER_SUP")
    private String orderSup;

    @Setter
    @Getter
    @Column(name = "ORDER_DIS")
    private String orderDis;

    @Setter
    @Getter
    @Column(name = "ORDER_TYPE")
    private String editType;

    @Setter
    @Getter
    @Column(name = "ORDER_DIS_MARK")
    private String orderDisMark;

    @Setter
    @Getter
    @Column(name = "ORDER_ISRETURN")
    private String orderIsreturn;

    @Setter
    @Getter
    @Column(name = "ORDER_DATE")
    private String orderDate;

    @Setter
    @Getter
    @Column(name = "CTR_CODE")
    private String ctrCode;

}
