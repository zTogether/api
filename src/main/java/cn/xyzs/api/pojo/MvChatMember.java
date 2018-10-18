package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
/**
 *
 * @Description: 成员表
 * @author: GeWeiliang
 * @date: 2018\10\18 0018 14:27
 * @param:
 * @return:
 */
@Data
@Table(name = "MV_CHAT_MEMBER")
public class MvChatMember {
    //群组id
    @Column(name = "GROUP_ID")
    private String groupId;

    //用户id
    @Column(name = "USER_ID")
    private String userId;

    //用户角色名称
    @Column(name = "USER_ROLE_NAME")
    private String userRoleName;

    //添加时间
    @Column(name = "ADD_TIME")
    private String addTime;
}
