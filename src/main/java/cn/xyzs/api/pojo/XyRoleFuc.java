package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
/***
 *
 * @Description: 角色功能
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 15:56
 * @param:
 * @return:
 */
@Table(name = "XY_ROLE_FUC")
public class XyRoleFuc {
    //角色编号
    @Setter
    @Getter
    @Column(name = "ROLE_ID")
    private String roleId;
    //菜单ID
    @Setter
    @Getter
    @Column(name = "COMPO_ID")
    private String compoId;
    //操作ID
    @Setter
    @Getter
    @Column(name = "OP_ID")
    private String opId;
}
