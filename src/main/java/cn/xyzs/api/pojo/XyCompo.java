package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "XY_COMPO")
public class XyCompo {
    //菜单ID
    @Setter
    @Getter
    @Column(name = "COMPO_ID")
    private String compoId;
    //菜单链接
    @Setter
    @Getter
    @Column(name = "COMPO_CODE")
    private String compoCode;
    //菜单名
    @Setter
    @Getter
    @Column(name = "COMPO_NAME")
    private String compoName;
}
