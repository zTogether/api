package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
/***
 *
 * @Description: 主材套系模本
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 15:02
 * @param:
 * @return:
 */
@Data
@Table(name = "XY_CLB_ZCTX_MB")
public class XyClbZctxMb {
    //VR编号
    @Column(name = "VR_ID")
    private String vrId;
    //分类编号
    @Column(name = "FL_BH")
    private String flBh;
    //分类名称
    @Column(name = "FL_MC")
    private String flMc;
    //目录编号
    @Column(name = "ML_ID")
    private String mlId;
    //目录分类
    @Column(name = "ML_CODE")
    private String mlCode;
    //目录名称
    @Column(name = "ML_MC")
    private String mlMc;
    //目录是否必须
    @Column(name = "ML_ISMUST")
    private String mlIsmust;
    //商品分类
    @Column(name = "ML_ZCFL")
    private String mlZcfl;
    //商品号
    @Column(name = "ZC_CODE")
    private String zcCode;
}
