package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_CWB_SK")//收款明细
public class XyCwbSk {
    //档案号
    @Column(name = "CTR_CODE")
    private String ctrCode;
    //流水号
    @Column(name = "CWB_SK_ROWID")
    private String cwbSkRowid;
    //付款时间
    @Column(name = "CWB_SK_DATE")
    private String cwbSkDate;
    //收款方式(1:转卡户,2:转账户,3:刷卡,4:现金)
    @Column(name = "CWB_SK_TYPE")
    private String cwbSkType;
    //收款内容
    @Column(name = "CWB_SK_CONTENT")
    private String cwbSkContent;
    //收款金额
    @Column(name = "CWB_SK_MONEY")
    private String cwbSkMoney;
    //
    @Column(name = "CWB_SK_CMONEY")
    private String cwbSkCmoney;
    //收款类型(1:收款,2:退款)
    @Column(name = "CWB_SK_IO")
    private String cwbSkIo;
    //备注
    @Column(name = "CWB_SK_MARK")
    private String cwbSkMark;
    //打印（1:未打印，2：已打印）
    @Column(name = "CWB_SK_PRINT")
    private String cwbSkPrint;
}
