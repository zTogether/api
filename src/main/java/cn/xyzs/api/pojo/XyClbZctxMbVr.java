package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
/***
 *
 * @Description: 主材套系模本VR
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 15:10
 * @param:
 * @return:
 */
@Data
@Table(name = "XY_CLB_ZCTX_MB_VR")
public class XyClbZctxMbVr {
    //套系号
    @Column(name = "VR_ID")
    private String vrId;
    //套系名称
    @Column(name = "VR_NAME")
    private String vrName;
    //套系分类
    @Column(name = "VR_TYPE")
    private String vrType;
    //套系样式
    @Column(name = "VR_STYLE")
    private String vrStyle;
    //套系VR地址
    @Column(name = "VR_URL")
    private String vrUrl;
    //套系图片
    @Column(name = "VR_PIC")
    private String vrPic;
    //套系说明
    @Column(name = "VR_SPEC")
    private String vrSpec;
}
