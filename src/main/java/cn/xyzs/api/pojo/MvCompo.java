package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "MV_COMPO")
public class MvCompo {
    @Column(name = "COMPO_ID")
    private String compoId;

    @Column(name = "COMPO_CODE")
    private String compoCode;

    @Column(name = "COMPO_NAME")
    private String compoName;
}
