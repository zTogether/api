package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_CWB_GRGZ_MAIN")
public class XyCwbGrgzMain {
    @Column(name = "GRGZ_ID")
    private String grgzId;

    @Column(name = "GRGZ_DATE")
    private String grgzDate;

    @Column(name = "GRGZ_STATU")
    private String grgzStatu;

    @Column(name = "GR_ID")
    private String grId;

    @Column(name = "CTR_CODE")
    private String ctrCode;

    @Column(name = "GRGZ_YF")
    private String grgzYf;

    @Column(name = "GRGZ_SF")
    private String grgzSf;

    @Column(name = "GRGZ_MARK")
    private String grgzMark;
}
