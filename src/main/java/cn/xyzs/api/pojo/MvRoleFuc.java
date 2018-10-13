package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "MV_ROLE_FUC")
public class MvRoleFuc {
    //角色编号
    @Column(name = "ROLE_ID")
    private String roleId;

    //菜单ID
    @Column(name = "COMPO_ID")
    private String compoId;

    //操作ID
    @Column(name = "OP_ID")
    private String opId;
}
