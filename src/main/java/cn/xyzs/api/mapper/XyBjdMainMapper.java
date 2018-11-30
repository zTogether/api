package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyBjdMain;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyBjdMainMapper extends Mapper<XyBjdMain>{

    /**
     *
     * @Description: 报价清单
     * @author: GeWeiliang
     * @date: 2018\9\20 0020 9:32
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txbm.* ,\n" +
            "\tTO_CHAR(xbm.CREATE_DATE,'yyyy-MM-dd HH24:mi:ss') CREATEDATE\n" +
            "FROM\n" +
            "\tXY_BJD_MAIN xbm\t\n" +
            "WHERE\n" +
            "\tCTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> bjdList(String ctrCode) throws SQLException;

    /**
     * 判断此工种是否可派工
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/10 15:33
     * @param: [ctrCode, pgStage]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "select count(1) from (" +
            "SELECT * FROM \n" +
            "  XY_BJD_MAIN D,\n" +
            "  XY_BJD_RG_LIST E\n" +
            "  WHERE\n" +
            "  D.BJD_CODE = E.BJD_CODE\n" +
            "  AND D.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}\n" +
            "  AND EXISTS(SELECT 1 FROM XY_GCB_RG_VER_LIST X WHERE X.RG_ID=E.RG_ID AND X.RG_SG_STAGE = #{pgStage,jdbcType=VARCHAR})\n" +
            "  AND D.BJD_STAGE='3' \n" +
            "  AND NOT EXISTS(SELECT 1 FROM XY_GCB_RG_DB C WHERE C.RG_ISGZ !=1 AND SUBSTR(E.RG_ID,1,10)=C.RG_CODE)\n" +
            "  AND NOT EXISTS(SELECT 1 FROM XY_PG A,XY_PG_LIST B WHERE A.PG_ID=B.PG_ID AND A.PG_STAGE=#{pgStage,jdbcType=VARCHAR} AND A.CTR_CODE= #{ctrCode,jdbcType=VARCHAR} AND B.BJD_CODE=D.BJD_CODE)" +
            ")" +
            "</script>")
    public Integer getIsPg(@Param("ctrCode") String ctrCode, @Param("pgStage") String pgStage) throws SQLException;

    /**
     * 根据ctrCode获取当前的报价单金额与出库单金额
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/14 11:58
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tA.CTR_CODE,\n" +
            "\tsum( bjd_je ) BJD_JE,\n" +
            "\tSUM( CLD_JE ) CLD_JE \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tT.CTR_CODE,\n" +
            "\t\tT.BJD_FC_ZJ BJD_JE,\n" +
            "\t\t0 CLD_JE \n" +
            "\tFROM\n" +
            "\t\tXY_BJD_MAIN T \n" +
            "\tWHERE\n" +
            "\t\tT.BJD_STAGE = '3' UNION ALL\n" +
            "\tSELECT\n" +
            "\t\tT.CTR_CODE,\n" +
            "\t\tT.FCSJ_JE,\n" +
            "\t\t0 \n" +
            "\tFROM\n" +
            "\t\tXY_BJD_FCSJ_MAIN T \n" +
            "\tWHERE\n" +
            "\t\tT.FCSJ_STATU = '1' UNION ALL\n" +
            "\tSELECT\n" +
            "\t\tT.CTR_CODE,\n" +
            "\t\t0,\n" +
            "\t\t( CASE T.CKD_TYPE WHEN '0' THEN T.CKD_ZJ ELSE - T.CKD_ZJ END ) \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_CLB_FC_CKD_MAIN T \n" +
            "\t\tWHERE\n" +
            "\t\t\t(( T.CKD_TYPE = '1' AND T.CKD_STATU = '3' ) OR T.CKD_TYPE = '0' ) UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\tT.CTR_CODE,\n" +
            "\t\t\t0,\n" +
            "\t\t\t0 \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_CUSTOMER_INFO T \n" +
            "\t\t) A \n" +
            "\tWHERE\n" +
            "\t\tA.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} \n" +
            "GROUP BY\n" +
            "\tA.CTR_CODE" +
            "</script>")
    public Map<String ,Object> getNowBjdjeAndCldjeByCtrCode(String ctrCode) throws SQLException;
}
