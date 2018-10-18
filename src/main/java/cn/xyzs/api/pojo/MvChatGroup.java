package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 *
 * @Description: 群组表
 * @author: GeWeiliang
 * @date: 2018\10\18 0018 14:23
 * @param:
 * @return:
 */
@Data
@Table(name = "MV_CHAT_GROUP")
public class MvChatGroup {
    //群组id
    @Column(name = "GROUP_ID")
    private String groupId;

    //档案号
    @Column(name = "CTR_CODE")
    private String ctrCode;

    //创建时间
    @Column(name = "CREATE_TIME")
    private String createTime;

    //是否在用(0:在用,1：停用)
    @Column(name = "IS_OCCUPIED")
    private String isOccupied;

}
