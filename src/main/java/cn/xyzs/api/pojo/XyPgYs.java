package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;


@Data
@Table(name = "XY_PG_YS")
public class XyPgYs {

    //主键id
    @Column(name = "YS_ID")
    private String ysId;

    //客户档案号
    @Column(name = "CTR_CODE")
	private String ctrCode;

    //验收工种
    @Column(name = "YS_GZ")
	private String ysGz;

    //操作时间
    @Column(name = "OP_DATE")
	private String opDate;

    //操作人id
    @Column(name = "OP_USERID")
	private String opUserid;

    //验收状态（0：草稿   1：已验收  2：客户不同意）
    @Column(name = "YS_STATU")
	private String ysStatu;

    //设计师意见
    @Column(name = "ZXY_MARK")
	private String zxyMark;

    //客户已建
    @Column(name = "CUST_MARK")
	private String custMark;

    //验收时间
    @Column(name = "YS_DATE")
	private String ysDate;
}
