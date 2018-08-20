package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "XY_COMPO")
public class XyCompo {
    @Setter
    @Getter
    @Column(name = "COMPO_ID")
    private String compoId;
    @Setter
    @Getter
    @Column(name = "COMPO_CODE")
    private String compoCode;
    @Setter
    @Getter
    @Column(name = "COMPO_NAME")
    private String compoName;
}
