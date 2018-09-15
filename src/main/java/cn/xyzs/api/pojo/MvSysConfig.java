package cn.xyzs.api.pojo;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "MV_SYS_CONFIG")
public class MvSysConfig {

    //参数key
    @Column(name = "PARAMETER_KEY")
    private String parameterKey;

    //参数值
    @Column(name = "PARAMETER_VALUE")
    private String parameterValue;
}
