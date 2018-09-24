package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_BJD_RG_LSIT")//报价单详情
public class XyBjdRgList {
    //单据号
    @Column(name = "BJD_CODE")
    private String bjdCode;
    //流水号
    @Column(name = "BJD_RG_ROWID")
    private String bjdRgRowid;
    //报价类型
    @Column(name = "BJD_STAGE")
    private String bjdRgStage;
    //行号
    @Column(name = "BJD_RG_NO")
    private String bjdRgNo;
    //
    @Column(name = "RG_ID")
    private String rgId;
    //名称
    @Column(name = "RG_NAME")
    private String rgName;
    //单位
    @Column(name = "RG_UNIT")
    private String rgUnit;
    //数量
    @Column(name = "RG_QTY")
    private String rgQty;
    //单价
    @Column(name = "RG_PRICE")
    private String rgPrice;
    //小计
    @Column(name = "RG_XJ")
    private String rgXj;
    //工艺
    @Column(name = "RG_DES")
    private String rgDes;
    //备注
    @Column(name = "RG_MARK")
    private String rgMark;
    //
    @Column(name = "RG_YN")
    private String rgYn;
    //是否认同(1:业主认同 其余皆为不认同)
    @Column(name = "RG_YZRK")
    private String rgYzrk;
}
