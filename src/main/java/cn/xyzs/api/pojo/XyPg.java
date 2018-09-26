package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
/***
 *
 * @Description: 派工单主表
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 15:31
 * @param:
 * @return:
 */
@Data
public class XyPg {
    //单据号
    @Column(name = "PG_ID")
    private String pgId;

    //客户号
    @Column(name = "CTR_CODE")
    private String ctrCode;

    //派工日期
    @Column(name = "PG_OP_DATE")
    private String pgOpDate;

    //派工工种
    @Column(name = "PG_STAGE")
    private String pgStage;

    //工长
    @Column(name = "PG_GR")
    private String pgGr;

    //进场日期
    @Column(name = "PG_BEGIN_DATE")
    private String pgBeginDate;

    //工期
    @Column(name = "PG_DAYS")
    private String pgDays;

    //操作人
    @Column(name = "PG_OP_USER")
    private String pgOpUser;

    //工资状态(0：未申请   1：已申请   2：已发放)
    @Column(name = "PG_MONEY_YN")
    private String pgMoneyYn;

    //打印状态（0：未打印   1：已打印    如果grId == null的话：等待工人报名中）
    @Column(name = "PG_PRINT_YN")
    private String pgPrintYn;
    
    //增加金额
    @Column(name = "PG_ADD_MONEY")
    private String pgAddMoney;


}
