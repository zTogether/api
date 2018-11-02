package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_EXAM_PAPER")
public class XyExamPaper {
    @Column(name = "PAPERNO")
    private String paperNo;

    @Column(name = "QUESTIONNO")
    private String questionNo;

    @Column(name = "QUESTIONSOCRE")
    private String  questionSocre;

    @Column(name = "QUESTIONTYPE")
    private String questionType;

    @Column(name = "SPARE")
    private String spare;
}
