package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_CLB_ZCTX_MB")
public class XyClbZctxMb {
    @Column(name = "VR_ID")
    private String vrId;
    @Column(name = "FL_BH")
    private String flBh;
    @Column(name = "FL_MC")
    private String flMc;
    @Column(name = "ML_ID")
    private String mlId;
    @Column(name = "ML_CODE")
    private String mlCode;
    @Column(name = "ML_MC")
    private String mlMc;
    @Column(name = "ML_ISMUST")
    private String mlIsmust;
    @Column(name = "ML_ZCFL")
    private String mlZcfl;
    @Column(name = "ZC_CODE")
    private String zcCode;
}
