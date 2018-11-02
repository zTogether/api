package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_STAR_EVA_LIST")
public class XyStarEvaList {
    @Column(name = "EVA_NO")
    private String evaNo;

    @Column(name = "LEVEL")
    private String level;

    @Column(name = "EVALUATION")
    private String evaluation;

    @Column(name = "EVA_NAME")
    private String evaName;
}
