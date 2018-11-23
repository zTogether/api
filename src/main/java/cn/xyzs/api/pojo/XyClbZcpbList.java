package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_CLB_ZCPB_LIST")
public class XyClbZcpbList {
    @Column(name = "ZCPB_ID")
    private String zcpbId;

    @Column(name = "CTR_CODE")
    private String ctrCode;

    @Column(name = "ZCPB_ML")
    private String zcpbMl;

    @Column(name = "ZCPB_XH")
    private String zcpbXh;

    @Column(name = "ZCPB_TYPE")
    private String zcpbType;

    @Column(name = "ZCPB_PP")
    private String zcpbPp;

    @Column(name = "ZCPB_DC")
    private String zcpbDc;

    @Column(name = "ZCPB_SPEC")
    private String zcpbSpec;

    @Column(name = "ZCPB_UNIT")
    private String zcpbUnit;

    @Column(name = "ZCPB_PRICE")
    private String zcpbPrice;

    @Column(name = "ZCPB_QTY")
    private String zcpbQty;

    @Column(name = "ZCPB_XJ")
    private String zcpbXj;

    @Column(name = "ZCPB_MARK")
    private String zcpbMark;

    @Column(name = "ZCPB_ROWID")
    private String zcpbRowid;

    @Column(name = "ZCPB_VERSION")
    private String zcpbVersion;

    @Column(name = "ZCPB_METE")
    private String zcpbMete;

    @Column(name = "ZCPB_MX")
    private String zcpbMx;

    @Column(name = "ZCPB_STAGE")
    private String zcpbStage;

    @Column(name = "ZCPB_ZC_CODE")
    private String zcpbZcCode;

    @Column(name = "ZCPB_ZC_TYPE")
    private String zcpbZcType;
}
