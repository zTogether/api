package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "XY_GCB_PRJ_PLAN")
public class XyGcbPrjPlan {
    @Column(name = "CTR_CODE")
    private String ctrCode;

    @Column(name = "ROW_ID")
    private String rowId;

    @Column(name = "DAYS")
    private Date days;

    @Column(name = "XH")
    private String xh;

    @Column(name = "PLAN_CONTENT")
    private String planContent;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "PLAN_TYPE")
    private String planType;

    @Column(name = "PLAN_LCCDID")
    private String planLcdid;

    @Column(name = "CON_ROWID")
    private String conRowid;

    @Column(name = "EDIT_DATE")
    private String editDate;

    @Column(name = "EDIT_MARK")
    private String editMark;

    @Column(name = "EDIT_STATU")
    private String editStatu;

    @Column(name = "EDIT_USER")
    private String editUser;
}
