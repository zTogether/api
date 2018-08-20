package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "XY_CLB_ZC_FL")
public class XyClbZcFl {
    @Id
    @Setter
    @Getter
    @Column(name = "ZCFL_CODE")
    private String zcflCode;
    @Setter
    @Getter
    @Column(name = "P_CODE")
    private String pCode;
    @Setter
    @Getter
    @Column(name = "ZCFL_NAME")
    private String zcflName;
    @Setter
    @Getter
    @Column(name = "ZCFL_STATU")
    private String zcflStatu;
}
