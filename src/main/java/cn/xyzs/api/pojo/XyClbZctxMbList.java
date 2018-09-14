package cn.xyzs.api.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
/**
 *
 * @Description: 主材套系模板选项
 * @author: GeWeiliang
 * @date: 2018\9\14 0014 15:07
 * @param:
 * @return:
 */
@Data
@Table(name = "XY_CLB_ZCTX_MB_LIST")
public class XyClbZctxMbList {
    //流水号
    @Column(name = "ROW_ID")
    private String rowId;
    //选项ID
    @Column(name = "ML_ID")
    private String mlId;
    //商品编号
    @Column(name = "ZC_CODE")
    private String zcCode;
}
