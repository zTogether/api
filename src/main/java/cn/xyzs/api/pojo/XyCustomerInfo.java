package cn.xyzs.api.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 *
 * @Description: 客户信息表
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 15:12
 * @param:
 * @return:
 */
@Data
@Table(name = "XY_CUSTOMER_INFO")
public class XyCustomerInfo {
    //客户号
    @Id
    @Setter
    @Getter
    @Column(name="CTR_CODE")
    private String ctrCode;
    //建档日期
    @Setter
    @Getter
    @Column(name="CTR_CRT_DATE")
    private String ctrCrtDate;
    //客户姓名
    @Setter
    @Getter
    @Column(name="CTR_NAME")
    private String ctrName;
    //客户电话
    @Setter
    @Getter
    @Column(name="CTR_TEL")
    private String ctrTel;
    //客户身份证号
    @Setter
    @Getter
    @Column(name="CTR_CARDID")
    private String ctrCardid;
    //工程地址
    @Setter
    @Getter
    @Column(name="CTR_ADDR")
    private String ctrAddr;
    //建筑面积
    @Setter
    @Getter
    @Column(name="CTR_AREA")
    private Integer ctrArea;
    //户型结构
    @Setter
    @Getter
    @Column(name="CTR_STRUCTURE")
    private String ctrStructure;
    //楼层
    @Setter
    @Getter
    @Column(name="CTR_FLOOR")
    private Integer ctrFloor;
    //是否有电梯
    @Setter
    @Getter
    @Column(name="CTR_LIFT")
    private String ctrLift;
    //工程类型(0：新装 1：再装)
    @Setter
    @Getter
    @Column(name="CTR_PRJ_TYPE")
    private String ctrPrjType;
    //报价范围
    @Setter
    @Getter
    @Column(name="CTR_QT_RANGE")
    private String ctrQtRang;
    //报价类型(0：标准 1：售前 2：保惠)
    @Setter
    @Getter
    @Column(name="CTR_QT_TYPE")
    private String ctrQtType;
    //报价版本
    @Setter
    @Getter
    @Column(name="RG_VER_CODE")
    private String rgVerCode;
    //接待人员
    @Setter
    @Getter
    @Column(name=" ")
    private String ctrWaiter;
    //服务机构
    @Setter
    @Getter
    @Column(name="CTR_ORG")
    private String ctrOrg;
    //设计师
    @Setter
    @Getter
    @Column(name="CTR_SJS")
    //执行总监
    private String ctrSjs;
    @Setter
    @Getter
    @Column(name="CTR_GCJL")
    private String ctrGcjl;
    //材料导购
    @Setter
    @Getter
    @Column(name="CTR_CLDD")
    private String ctrCldd;
    //
    @Setter
    @Getter
    @Column(name="CTR_ROWID")
    private String ctrRowid;
    //区域老总
    @Setter
    @Getter
    @Column(name="CTR_AREA_MA")
    private String ctrAreaMa;
    //工程机构
    @Setter
    @Getter
    @Column(name="CTR_PRO_ORG")
    private String ctrProOrg;
    //开工日期
    @Setter
    @Getter
    @Column(name="CTR_KG_DATE")
    private String ctrKgDate;
    //档案状态
    @Setter
    @Getter
    @Column(name="CTR_STATU")
    private String ctrStatu;
    //施工图纸
    @Setter
    @Getter
    @Column(name="CTR_DRAW")
    private String ctrDraw;
    //图纸状态
    @Setter
    @Getter
    @Column(name="DRAW_STATU")
    private String drawStatu;
    //工程坐标X
    @Setter
    @Getter
    @Column(name="CTR_X")
    private String ctrX;
    //工程坐标Y
    @Setter
    @Getter
    @Column(name="CTR_Y")
    private String ctrY;
    //地图版本
    @Setter
    @Getter
    @Column(name="CTR_MAP_VERSION")
    private String ctrMapVersion;

    //合约成效人
    @Column(name = "CTR_OWENER")
    private String ctrOwener;
}
