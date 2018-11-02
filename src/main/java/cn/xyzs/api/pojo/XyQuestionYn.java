package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_QUESTION_YN")
public class XyQuestionYn {
    @Column(name = "QUESTION")
    private String questionNo;

    @Column(name = "QUESTIONCONTENT")
    private String questionContent;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "LEVEL")
    private String level;

    @Column(name = "SPARE")
    private String spare;
}
