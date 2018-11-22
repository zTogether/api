package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_PDCA")
public class XyPdca {

    //主键id
    @Column(name = "PDCA_ID")
    private String pdcaId;

    //用户id
    @Column(name = "USER_ID")
    private String userId;

    //最后编辑时间
    @Column(name = "POSITION")
    private String position;

    //开始时间
    @Column(name = "PDCA_DATE")
    private String pdcaDate;

    //资源
    @Column(name = "PRESOURCE")
    private String presource;

    //意见
    @Column(name = "OPINION")
    private String opinion;

    //状态
    @Column(name = "STATU")
    private String statu;

    //结束时间
    @Column(name = "RES")
    private String res;

    //建议
    @Column(name = "ISSUE")
    private String issue;
}
