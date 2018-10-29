package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_EXAM_MANAGER")
public class XyExamManager {
    @Column(name = "EXAMCODE")
    private String examCode;

    @Column(name = "EXAMTYPE")
    private String examType;

    @Column(name = "PAPERNO")
    private String paperNo;

    @Column(name = "EXAMDATE")
    private String examDate;

    @Column(name = "TOTALTIME")
    private String totalTime;

    @Column(name = "TREM")
    private String trem;

    @Column(name = "SPARE")
    private String spare;
}
