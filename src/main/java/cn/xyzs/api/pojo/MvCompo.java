package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "MV_COMPO")
public class MvCompo {
    //菜单ID
    @Column(name = "COMPO_ID")
    private String compoId;

    //菜单链接
    @Column(name = "COMPO_CODE")
    private String compoCode;

    //菜单名
    @Column(name = "COMPO_NAME")
    private String compoName;
}
