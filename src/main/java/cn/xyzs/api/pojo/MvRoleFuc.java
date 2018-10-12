package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "MV_ROLE_FUC")
public class MvRoleFuc {
    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "COMPO_ID")
    private String compoId;

    @Column(name = "OP_ID")
    private String opId;
}
