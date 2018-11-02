package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_EXAM_ANSWER")
public class XyExamAnswer {
    @Column(name = "EXAMCODE")
    private String examCode;

    @Column(name = "EMPNO")
    private String empNo;

    @Column(name = "QUESTIONNO")
    private String questionNo;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "SPARE")
    private String spare;
}
