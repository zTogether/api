package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_PDCA_LIST")
public class XyPdcaList {


    @Column(name = "DATE_ID")
    private String dateId;

    //主表id
    @Column(name = "PDCA_ID")
    private String pdcaId;

    //周几
    @Column(name = "WEEK")
	private String week;

    //日期
    @Column(name = "DATES")
	private String dates;

    @Column(name = "CLASSIFY")
	private String classify;

    //工作内容记录
    @Column(name = "PCONTENT")
	private String pcontent;

    //日总结
    @Column(name = "PSUMMARY")
	private String psummary;

    //建议
    @Column(name = "RES")
	private String res;
}
