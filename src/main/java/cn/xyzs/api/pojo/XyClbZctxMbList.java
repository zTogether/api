package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_CLB_ZCTX_MB_LIST")
public class XyClbZctxMbList {
    @Column(name = "ROW_ID")
    private String rowId;

    @Column(name = "ML_ID")
    private String mlId;

    @Column(name = "ZC_CODE")
    private String zcCode;
}
