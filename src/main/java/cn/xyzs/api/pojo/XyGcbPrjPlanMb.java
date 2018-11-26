package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_GCB_PRJ_PLAN_MB")
public class XyGcbPrjPlanMb {

    //
    @Column(name = "ROW_ID")
    private String rowId;

    //
    @Column(name = "DAYS")
	private String days;

    //
    @Column(name = "XH")
	private String xh;

    //
    @Column(name = "PLAN_CONTENT")
	private String planContent;

    //
    @Column(name = "ROLE_NAME")
	private String roleName;

    //
    @Column(name = "PLAN_TYPE")
	private String planType;

    //
    @Column(name = "PLAN_LCDID")
	private String planLcdid;

    //
    @Column(name = "CON_ROWID")
	private String conRowid;
}
