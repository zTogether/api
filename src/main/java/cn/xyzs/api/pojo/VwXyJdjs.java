package cn.xyzs.api.pojo;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "VW_XY_JDJS")
public class VwXyJdjs {
    //客户档案号
    @Column(name = "CTR_CODE")
    private String ctrCode;

    //工程阶段
    @Column(name = "JD")
    private String jd;

    //人工预算
    @Column(name = "RG_YS")
    private String rgYs;

    //材料预算
    @Column(name = "CL_YS")
    private String clYs;

    //人工业主认可
    @Column(name = "RG_YZRK")
    private String rgYzrk;

    //材料业主认可
    @Column(name = "CL_YZRK")
    private String clYzrk;

    //人工业主不认可
    @Column(name = "RG_YZBRK")
    private String rgYzbrk;

    //材料业主不认可
    @Column(name = "CL_YZBRK")
    private String clYzbrk;

    @Column(name = "FCSJ")
    private String fcsj;

    //实际辅材
    @Column(name = "SJFC")
    private String sjfc;

    //服务费
    @Column(name = "FWF")
    private String fwf;

}
