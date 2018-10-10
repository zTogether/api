package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 *
 * @Description:
 * @author: GeWeiliang
 * @date: 2018\10\6 0006 16:18
 * @param:
 * @return:
 */
@Data
@Table(name = "XY_GCB_RG_VER")
public class XyGcbRgVer {
    @Column(name = "RG_VER_CODE")
    private String rgVerCode;

    @Column(name = "RG_VER_NAME")
    private String rgVerName;

    @Column(name = "RG_VER_MARK")
    private String rgVerMark;

    @Column(name = "RG_VER_ISUSED")
    private String rgVerIsused;

    @Column(name = "RG_VER_BJ_RGML")
    private String rgVerBjRgml;

    @Column(name = "RG_VER_BJ_FCML")
    private String rgVerBjFcml;

    @Column(name = "RG_VER_BJ_SGML")
    private String rgVerBjSgml;

    @Column(name = "RG_VER_C1")
    private String rgVerC1;

    @Column(name = "RG_VER_C2")
    private String rgVerC2;

    @Column(name = "RG_VER_C3")
    private String rgVerC3;

}
