package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_CLB_FC_CKD_MAIN")//辅材清单
public class XyClbFcCkdMain {
    //编号
    @Column(name = "CKD_CODE")
    private String ckdCode;

    //档案号
    @Column(name = "CTR_CODE")
    private String ctrCode;

    //出货类别(0:出库,1:退库)
    @Column(name = "CKD_TYPE")
    private String ckdType;

    //开单时间
    @Column(name = "CKD_INPUT_DATE")
    private String ckdInputDate;

    //材料大类(对应XY_VAL表)
    @Column(name = "CKD_FC_TYPE")
    private String ckdFcType;

    //出库单仓库
    @Column(name = "CKD_CK")
    private String ckdCk;

    //开单人
    @Column(name = "CKD_OP_USER")
    private String ckdOpUser;

    //开单总计
    @Column(name = "CKD_ZJ")
    private String ckdZj;

    //确认时间
    @Column(name = "CKD_AUDIT_DATE")
    private String ckdAuditDate;

    //单据状态(0:草稿,1:待审,2:待打印,3:已打印)
    @Column(name = "CKD_STATU")
    private String ckdStatu;

    //备注
    @Column(name = "CKD_MARK")
    private String ckdMark;
}
