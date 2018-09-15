package cn.xyzs.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
/***
 *
 * @Description: 主材分类表
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 14:44
 * @param:
 * @return:
 */
@Table(name = "XY_CLB_ZC_FL")
public class XyClbZcFl {
    //分类编号
    @Id
    @Setter
    @Getter
    @Column(name = "ZCFL_CODE")
    private String zcflCode;
    @Setter
    @Getter
    @Column(name = "P_CODE")
    private String pCode;
    //分类名称
    @Setter
    @Getter
    @Column(name = "ZCFL_NAME")
    private String zcflName;
    //分类状态
    @Setter
    @Getter
    @Column(name = "ZCFL_STATU")
    private String zcflStatu;
}
