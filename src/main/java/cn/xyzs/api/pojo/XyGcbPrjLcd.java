package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_GCB_PRJ_LCD")
public class XyGcbPrjLcd {
    @Column(name = "ROW_ID")
    private String rowId;

    @Column(name = "ZCPB_ID")
    private String zcpbId;

    @Column(name = "PRJ_ID")
    private String prjId;

    @Column(name = "QUANTITY")
    private String quantity;

    @Column(name = "IS_ORDER")
    private String isOrder;

    @Column(name = "MARK")
    private String mark;
}
