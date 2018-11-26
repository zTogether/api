package cn.xyzs.api.pojo;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "XY_CLB_ZC_ORDER_FH")
public class XyClbZcOrderFh {
    //订单id
    @Column(name = "ORDER_ID")
    private String orderId;

    //供应商id
    @Column(name = "ORDER_SUP")
	private String orderSup;

    //发货备注
    @Column(name = "FH_MARK")
	private String fhMark;

    //发货日期
    @Column(name = "FH_DATE")
	private String fhDate;

    //执行员验收状态（0：未验收，    1：已验收）
    @Column(name = "ZXY_STATU")
	private String zxyStatu;

    //执行员id
    @Column(name = "YS_USER")
	private String ysUser;

    //执行员验收日期
    @Column(name = "YS_DATE")
	private String ysDate;

    //客户验收状态（0：未验收   1：已验收）
    @Column(name = "KH_STATU")
	private String khStatu;

    //客户验收时间
    @Column(name = "KH_DATE")
	private String khDate;
}
