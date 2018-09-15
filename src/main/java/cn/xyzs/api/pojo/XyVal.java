package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "XY_VAL")
public class XyVal {
    //
    @Setter
    @Getter
    @Column(name = "VALSET_ID")
    private String valsetId;

    @Setter
    @Getter
    @Column(name = "VAL_ID")
    private String valId;

    @Setter
    @Getter
    @Column(name = "VAL_NAME")
    private String valName;
}
