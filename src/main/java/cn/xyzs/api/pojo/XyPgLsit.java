package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_PG_LIST")
public class XyPgLsit {

    //派工id
    @Column(name = "PG_ID")
    private String pgId;

    //
    @Column(name = "PG_ROW")
	private String pgRow;

    //项目Id
    @Column(name = "RG_ID")
	private String rgId;

    //项目名称
    @Column(name = "RG_NAME")
	private String rgName;

    //计量单位
    @Column(name = "RG_UNIT")
	private String rgUnit;

    //人工数量
    @Column(name = "RG_QTY")
	private String rgQty;

    //
    @Column(name = "RG_HJ")
	private String rgHj;

    //工艺描述
    @Column(name = "RG_DESC")
	private String rgDesc;

    //
    @Column(name = "BJD_CODE")
	private String bjdCode;
}
