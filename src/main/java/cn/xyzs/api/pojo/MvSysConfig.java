package cn.xyzs.api.pojo;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "MV_SYS_CONFIG")
public class MvSysConfig {

    @Column(name = "PARAMETER_KEY")
    private String parameterKey;

    @Column(name = "PARAMETER_VALUE")
    private String parameterValue;
}
