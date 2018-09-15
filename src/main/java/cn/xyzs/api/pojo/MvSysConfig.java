package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "MV_SYS_CONFIG")
public class MvSysConfig {

    @Column(name = "PARAMETER_KEY")
    private String parameterKey;

    @Column(name = "PARAMETER_VALUE")
    private String parameterValue;

    @Column(name = "PARAMETER_MARK")
    private String parameterMark;
}
