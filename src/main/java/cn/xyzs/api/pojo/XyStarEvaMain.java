package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_STAR_EVA_MAIN")
public class XyStarEvaMain {
    @Column(name = "EVA_CODE")
    private String evaCode;

    @Column(name = "EVA_NO")
    private String evaNo;

    //评论种类0:工程验收
    @Column(name = "EVA_TYPE")
    private String evaType;

    //评论时间
    @Column(name = "EVA_DATE")
    private String evaDate;
}
