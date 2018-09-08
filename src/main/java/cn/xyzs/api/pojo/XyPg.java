package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class XyPg {

    @Column(name = "PG_ID")
    private String pgId;

    @Column(name = "CTR_CODE")
    private String ctrCode;

    @Column(name = "PG_OP_DATE")
    private String pgOpDate;

    @Column(name = "PG_STAGE")
    private String pgStage;

    @Column(name = "PG_GR")
    private String pgGr;

    @Column(name = "PG_BEGIN_DATE")
    private String pgBeginDate;

    @Column(name = "PG_DAYS")
    private String pgDays;

    @Column(name = "PG_OP_USER")
    private String pgOpUser;

    @Column(name = "PG_MONEY_YN")
    private String pgMoneyYn;

    @Column(name = "PG_PRINT_YN")
    private String pgPrintYn;

    @Column(name = "PG_ADD_MONEY")
    private String pgAddMoney;


}
