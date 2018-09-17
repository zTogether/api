package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
/***
 *
 * @Description: 角色表
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 14:18
 * @param:
 * @return:
 */
@Table(name = "XY_ROLE")
public class XyRole {
    //角色ID
    @Getter
    @Setter
    @Column(name = "ROLE_ID")
    private String roleId;
    //角色名称
    @Getter
    @Setter
    @Column(name = "ROLE_NAME")
    private String roleName;
    //角色类型
    @Getter
    @Setter
    @Column(name = "ROLE_TYPE")
    private String roleType;
}
