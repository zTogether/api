package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZctxMbVr;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZctxMbMapper extends Mapper<XyClbZctxMbVr> {
    /***
     *
     * @Description: 根据vrStyle查询套系VR
     * @author: GeWeiliang
     * @date: 2018\9\2 0002 17:35
     * @param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("SELECT * FROM XY_CLB_ZCTX_MB_VR WHERE VR_STYLE=#{vrStyle}")
    public List<Map<String,Object>> showZctxVr(@Param("vrStyle") String vrStyle)throws SQLException;

    @Select("SELECT * FROM XY_CLB_ZCTX_MB_VR WHERE VR_ID=#{vrId}")
    public Map<String,Object> vrDetail(@Param("vrId") String vrId) throws SQLException;

    /***
     *
     * @Description: 套系材料列表
     * @author: GeWeiliang
     * @date: 2018\9\3 0003 16:44
     * @param: [vrId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("SELECT zm.*,zf.ZCFL_NAME,zd.ZC_NAME,zd.ZC_PRICE_OUT,zd.ZC_BRAND,zd.ZC_SPEC,sup.SUP_NAME,zd.ZC_MATERIAL," +
            "zd.ZC_COLOR,zd.ZC_UNIT,zd.ZC_DES,zd.ZC_CYC \n" +
            "FROM XY_CLB_ZCTX_MB zm,XY_CLB_ZC_DB zd,XY_SUPPLIER sup,XY_CLB_ZC_FL zf\n" +
            "WHERE zm.VR_ID=#{vrId} AND zm.ZC_CODE=zd.ZC_CODE AND zd.ZC_SUP=sup.SUP_CODE " +
            "AND zm.ML_ZCFL=zf.ZCFL_CODE\n" +
            "ORDER BY zm.FL_BH")
    public List<Map<String,Object>> txClList(@Param("vrId") String vrId) throws SQLException;
}
