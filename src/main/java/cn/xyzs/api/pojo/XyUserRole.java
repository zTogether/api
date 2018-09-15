package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
/***
 *
 * @Description: 用户角色
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 14:16
 * @param:
 * @return:
 */
@Table(name = "XY_USER_ROLE")
public class XyUserRole {
    //用户角色ID
    @Getter
    @Setter
    @Column(name = "UR_ID")
    private String urId;
    // 员工ID
    @Getter
    @Setter
    @Column(name = "USER_ID")
    private String userd;
    //角色ID
    @Getter
    @Setter
    @Column(name = "ROLE_ID")
    private String roled;

}
