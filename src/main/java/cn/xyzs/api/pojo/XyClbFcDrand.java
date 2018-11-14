package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_CLB_FC_BRAND")
public class XyClbFcDrand {

    //品牌编号
    @Column(name = "BRAND_ID")
    private String brand_id;

    //辅材编号(对应：XY_CLB_FC_DB)
    @Column(name = "FC_CODE")
	private String fcCode;

    //品牌名称
    @Column(name = "BRAND_NAME")
	private String brandName;

    //供应商编码
    @Column(name = "SUP_CODE")
	private String supCode;

    //是否启用（1：可用   0：不可用）
    @Column(name = "BRAND_ISUSED")
	private String brandIsused;

    //
    @Column(name = "S_NAME")
	private String sName;

    //
    @Column(name = "S_VAL")
	private String sVal;
}
