package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "XY_ROLE_FUC")
public class XyRoleFuc {
    @Setter
    @Getter
    @Column(name = "ROLE_ID")
    private String roleId;
    @Setter
    @Getter
    @Column(name = "COMPO_ID")
    private String compoId;
    @Setter
    @Getter
    @Column(name = "OP_ID")
    private String opId;
}
