package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class VwXyPgWaiter {

    @Column(name="PG_OP_DATE")
    private String pgOpDate;

    @Column(name="PG_ID")
    private String pgId;

    @Column(name="GR_ID")
    private String grId;

    @Column(name="GR_NAME")
    private String grName;

    @Column(name="DISTANCE")
    private String distance;

    @Column(name="PG_BEGIN_DATE")
    private String pgBeginDate;

    @Column(name="GZ")
    private String gz;

    @Column(name="ADD_MONEY")
    private String addMoney;

    @Column(name="CTR_CODE")
    private String ctrCode;

    @Column(name="CTR_NAME")
    private String ctrName;

    @Column(name="CTR_ADDR")
    private String ctrAddr;

    @Column(name="ORG_PRJ_NAME")
    private String orgPrj_name;

    @Column(name="STATE")
    private String state;

}
