package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_CWB_GRGZ_LIST")
public class XyCwbGrgzList {
    @Column(name = "GRGZ_ID")
    private String grgzId;

    @Column(name = "GRGZ_ROW")
    private String grgzRow;

    @Column(name = "PG_ID")
    private String pgId;

    @Column(name = "PG_GZ")
    private String pgGz;

    @Column(name = "BJD_CODE")
    private String bjdCode;

    @Column(name = "RG_JE")
    private String rgJe;
}
