package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class XyPgWaiter {

    @Column(name = "PG_ID")
    private String pgId;

    @Column(name = "GR_ID")
    private String grId;

    @Column(name = "ZT")
    private String zt;

    @Column(name = "OP_DATE")
    private String opDate;

    @Column(name = "END_DATE")
    private String endDate;

    @Column(name = "CTR_CODE")
    private String ctrCode;

    @Column(name = "YS_DATE")
    private String ysDate;

}
