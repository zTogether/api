package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
/***
 *
 * @Description: 工人报名记录表
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 15:36
 * @param:
 * @return:
 */
@Data
public class XyPgWaiter {
    //派工单号
    @Column(name = "PG_ID")
    private String pgId;
    //工人
    @Column(name = "GR_ID")
    private String grId;
    //状态
    @Column(name = "ZT")
    private String zt;
    //操作日期
    @Column(name = "OP_DATE")
    private String opDate;
    //预定退场日期
    @Column(name = "END_DATE")
    private String endDate;
    //客户档案
    @Column(name = "CTR_CODE")
    private String ctrCode;
    //验收日期
    @Column(name = "YS_DATE")
    private String ysDate;

}
