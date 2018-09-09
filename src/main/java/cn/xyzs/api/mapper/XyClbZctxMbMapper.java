package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZctxMbVr;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
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
    @Select("<script>SELECT * FROM XY_CLB_ZCTX_MB_VR WHERE VR_STYLE=#{vrStyle}</script>")
    public List<Map<String,Object>> showZctxVr(@Param("vrStyle") String vrStyle)throws SQLException;

    @Select("<script>SELECT * FROM XY_CLB_ZCTX_MB_VR WHERE VR_ID=#{vrId}</script>")
    public Map<String,Object> vrDetail(@Param("vrId") String vrId) throws SQLException;

    /***
     *
     * @Description: 套系材料列表
     * @author: GeWeiliang
     * @date: 2018\9\3 0003 16:44
     * @param: [vrId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = clList.class,method = "clList")
    public List<Map<String,Object>> txClList(@Param("vrId") String vrId,@Param("mlId") String mlId) throws SQLException;
    class clList{
        public String clList(@Param("vrId") String vrId,@Param("mlId") String mlId){
            return new SQL(){{
                SELECT("zm.*,NVL(zd.ZC_NAME, '-') ZC_NAME,zd.ZC_PRICE_OUT," +
                        "NVL(zd.ZC_BRAND, '-') ZC_BRAND,NVL(sup.SUP_NAME, '-') SUP_NAME, NVL(zd.ZC_SPEC, '-') ZC_SPEC," +
                        "NVL(zd.ZC_MATERIAL, '-') ZC_MATERIAL,NVL(zd.ZC_COLOR, '-') ZC_COLOR,NVL(zf.ZCFL_NAME,'-') ZCFL," +
                        "NVL(zd.ZC_UNIT,'-') ZC_UNIT,NVL(zd.ZC_DES,'-') ZC_DES,zd.ZC_CYC");
                FROM("XY_CLB_ZCTX_MB zm");
                LEFT_OUTER_JOIN("XY_CLB_ZC_DB zd ON zm.ZC_CODE=zd.ZC_CODE");
                LEFT_OUTER_JOIN("XY_SUPPLIER sup ON zd.ZC_SUP=sup.SUP_CODE");
                LEFT_OUTER_JOIN("XY_CLB_ZC_FL zf ON zd.ZC_TYPE=zf.ZCFL_CODE");
                WHERE("zm.VR_ID=#{vrId,jdbcType=VARCHAR}");
                if(mlId!=null && mlId!=""){
                    WHERE("zm.ML_ID=#{mlId,jdbcType=VARCHAR}");
                }
                ORDER_BY("zm.FL_BH");
            }}.toString();
        }
    }
}
