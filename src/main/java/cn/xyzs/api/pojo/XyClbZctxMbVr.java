package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_CLB_ZCTX_MB_VR")
public class XyClbZctxMbVr {
    @Column(name = "VR_ID")
    private String vrId;
    @Column(name = "VR_NAME")
    private String vrName;
    @Column(name = "VR_TYPE")
    private String vrType;
    @Column(name = "VR_STYLE")
    private String vrStyle;
    @Column(name = "VR_URL")
    private String vrUrl;
    @Column(name = "VR_PIC")
    private String vrPic;
    @Column(name = "VR_SPEC")
    private String vrSpec;
}
