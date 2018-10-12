package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyPgLsit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyPgLsitMapper extends Mapper<XyPgLsit>{

    /**
     * 获取派单明细
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/23 9:25
     * @param: [pgId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txpl.PG_ID,\n" +
            "\txpl.PG_ROW,\n" +
            "\txpl.RG_ID,\n" +
            "\txpl.RG_NAME, \n" +
            "\txpl.RG_UNIT, \n" +
            "\txpl.RG_QTY, \n" +
            "\txpl.RG_HJ, \n" +
            "\txpl.RG_DESC, \n" +
            "\txpl.BJD_CODE \n" +
            "FROM\n" +
            "\tXY_PG_LIST xpl \n" +
            "WHERE\n" +
            "\txpl.PG_ID = #{pgId,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getPgLsit(String pgId) throws SQLException;

    /**
     * 添加派工List
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/11 9:05
     * @param: []
     * @return: void
     */
    @Select("" +
            "INSERT INTO XY_PG_LIST\n" +
            "SELECT\n" +
            "\t#{pgId,jdbcType=VARCHAR},\n" +
            "\tSYS_GUid(),\n" +
            "\tE .RG_ID,\n" +
            "\tE .RG_NAME,\n" +
            "\tE .RG_UNIT,\n" +
            "\tsum(E .RG_QTY),\n" +
            "\tsum(E .RG_XJ),\n" +
            "\tE .RG_DES,\n" +
            "\tD .BJD_CODE\n" +
            "FROM\n" +
            "\tXY_BJD_MAIN D,\n" +
            "\tXY_BJD_RG_LIST E \n" +
            "WHERE\n" +
            "\tD.BJD_CODE = E.BJD_CODE \n" +
            "\tAND D.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} \n" +
            "\tAND EXISTS ( SELECT 1 FROM XY_GCB_RG_VER_LIST X WHERE X.RG_ID = E.RG_ID AND X.RG_SG_STAGE = #{pgStage,jdbcType=VARCHAR} ) \n" +
            "\tAND D.BJD_STAGE = '3' \n" +
            "\tAND NOT EXISTS (\n" +
            "\tSELECT\n" +
            "\t\t1 \n" +
            "\tFROM\n" +
            "\t\tXY_GCB_RG_DB C \n" +
            "\tWHERE\n" +
            "\t\tC.RG_ISGZ != 1 \n" +
            "\t\tAND SUBSTR( E.RG_ID, 1, 10 ) = C.RG_CODE \n" +
            "\t) \n" +
            "\tAND NOT EXISTS (\n" +
            "\tSELECT\n" +
            "\t\t1 \n" +
            "\tFROM\n" +
            "\t\tXY_PG A,\n" +
            "\t\tXY_PG_LIST B \n" +
            "\tWHERE\n" +
            "\t\tA.PG_ID = B.PG_ID \n" +
            "\t\tAND A.PG_STAGE = #{pgStage,jdbcType=VARCHAR} \n" +
            "\t\tAND A.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} \n" +
            "\t\tAND B.BJD_CODE = D.BJD_CODE \n" +
            "\t) \n" +
            "\tGROUP BY E.RG_ID,\n" +
            "\tE.RG_NAME,\n" +
            "\tE.RG_UNIT,\n" +
            "\tE.RG_DES,\n" +
            "\tD.BJD_CODE" +
            "")
    public void addPgList(@Param("pgId") String pgId ,@Param("ctrCode") String ctrCode ,@Param("pgStage") String pgStage ) throws SQLException;
}
