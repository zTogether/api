package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
/***
 *
 * @Description: 报名视图
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 15:47
 * @param:
 * @return:
 */
@Data
public class VwXyPgWaiter {
    //
    @Column(name="PG_OP_DATE")
    private String pgOpDate;
    //派工号
    @Column(name="PG_ID")
    private String pgId;
    //工人号
    @Column(name="GR_ID")
    private String grId;
    //工人名称
    @Column(name="GR_NAME")
    private String grName;
    //距离
    @Column(name="DISTANCE")
    private String distance;
    //进场日期
    @Column(name="PG_BEGIN_DATE")
    private String pgBeginDate;
    //工资
    @Column(name="GZ")
    private String gz;
    //额外工资
    @Column(name="ADD_MONEY")
    private String addMoney;
    //客户号
    @Column(name="CTR_CODE")
    private String ctrCode;
    //客户名字
    @Column(name="CTR_NAME")
    private String ctrName;
    //工程地址
    @Column(name="CTR_ADDR")
    private String ctrAddr;
    //
    @Column(name="ORG_PRJ_NAME")
    private String orgPrj_name;

    @Column(name="STATE")
    private String state;

}
