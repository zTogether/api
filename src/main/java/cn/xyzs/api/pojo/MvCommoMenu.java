package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

public class MvCommoMenu {

    //员工号
    @Column(name = "USER_ID")
    @Getter
    @Setter
    private String userId;
    //角色ID
    @Column(name = "ROLE_ID")
    @Getter
    @Setter
    private String roleId;
    //
    @Column(name = "COMPO_ID")
    @Getter
    @Setter
    private String compoId;

}
