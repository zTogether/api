package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_STAR_EVA_LIST")
public class XyStarEvaList {
    //评论帐号
    @Column(name = "EVA_NO")
    private String evaNo;

    //星级
    @Column(name = "LEVEL")
    private String level;

    //评论内容
    @Column(name = "EVALUATION")
    private String evaluation;

    //评价名称
    @Column(name = "EVA_NAME")
    private String evaName;

    //评论类型(0:工程验收)
    @Column(name = "EVA_TYPE")
    private String evaType;

    @Column(name = "EVA_CODE")
    private String evaCode;

    //评论日期
    @Column(name = "EVA_DATE")
    private String evaDate;
}
