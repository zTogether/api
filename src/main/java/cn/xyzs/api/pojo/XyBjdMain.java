package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_BJD_MAIN")
public class XyBjdMain {
    //单据号
    @Column(name = "BJD_CODE")
    private String bjdCode;

    //创建时间
    @Column(name = "CREATE_DATE")
    private String createDate;

    //客户号
    @Column(name = "CTR_CODE")
    private String ctrCode;

    //发起对象（0：执行总监发起   1：设计师发起）
    @Column(name = "BJD_AUTHER")
    private String bjdAuther;

    //单据状态（1：草稿   2：待审   3：已审）
    @Column(name = "BJD_STAGE")
    private String bjdStage;

    //人工统计
    @Column(name = "BJD_RG_ZJ")
    private String bjdRgZj;

    //辅材统计
    @Column(name = "BJD_FC_ZJ")
    private String bjdFcZj;

    //服务费统计
    @Column(name = "BJD_FWF_ZJ")
    private String bjdFwfZj;

    @Column(name = "BJD_DRAWING")
    private String bjdDrawing;

    //柜子面积
    @Column(name = "BJD_CA_AREA")
    private String bjdCabArea;

    //报价类型
    //单据号尾号为   01：原始     其他为：增减项
}
