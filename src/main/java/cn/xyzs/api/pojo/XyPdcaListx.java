package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_PDCA_LISTX")
public class XyPdcaListx {

    //主键id
    @Column(name = "ROW_ID")
    private String rowId;

    //主表id
    @Column(name = "PDCA_ID")
    private String pdcaId;

    //1:本周工作总结 2：下周工作总结
    @Column(name = "CLASSIFY")
	private String classify;

    //内容
    @Column(name = "PCONTENT")
	private String pcontent;

    //序号
    @Column(name = "RES")
	private String res;
}
