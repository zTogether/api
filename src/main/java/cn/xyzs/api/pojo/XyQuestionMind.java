package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_QUESTION_MIND")
public class XyQuestionMind {
    @Column(name = "QUESTIONNO")
    private String questionNo;

    @Column(name = "QUESTIONCONTENT")
    private String questionContent;

    @Column(name = "SPARE")
    private String spare;
}
