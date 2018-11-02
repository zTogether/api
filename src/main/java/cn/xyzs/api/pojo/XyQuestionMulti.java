package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_QUESTION_MULTI")
public class XyQuestionMulti {
    @Column(name = "QUESTIONNO")
    private String questionNo;

    @Column(name = "QUESTION_A")
    private String questionA;

    @Column(name = "QUESTION_B")
    private String questionB;

    @Column(name = "QUESTION_C")
    private String questionC;

    @Column(name = "QUESTION_D")
    private String questionD;

    @Column(name = "QUESTION_E")
    private String questionE;

    @Column(name = "QUESTION_F")
    private String questionF;

    @Column(name = "QUESTION_G")
    private String questionG;

    @Column(name = "Y_ANSWER")
    private String YAnswer;

    @Column(name = "QLEVEL")
    private String QLevel;

    @Column(name = "SPARE")
    private String spare;

    @Column(name = "QUESTIONCONTENT")
    private String questionContent;

}
