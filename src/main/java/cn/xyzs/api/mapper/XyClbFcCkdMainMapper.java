package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbFcCkdMain;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbFcCkdMainMapper extends Mapper<XyClbFcCkdMain>{

    /**
     *
     * @Description: 辅材清单
     * @author: GeWeiliang
     * @date: 2018\9\19 0019 9:45
     * @param: [ctrCcode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tfcm.CKD_CODE, \n" +
            "\tfcm.CTR_CODE, \n" +
            "\tfcm.CKD_TYPE, \n" +
            "\tTO_CHAR(fcm.CKD_INPUT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_INPUT_DATE, \n" +
            "\tfcm.CKD_FC_TYPE, \n" +
            "\tfcm.CKD_CK, \n" +
            "\tfcm.CKD_OP_USER, \n" +
            "\tfcm.CKD_ZJ, \n" +
            "\tTO_CHAR(fcm.CKD_AUDIT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_AUDIT_DATE, \n" +
            "\tfcm.CKD_STATU, \n" +
            "\tfcm.CKD_MARK,\n" +
            "\tu.USER_NAME,\n" +
            "\txv.VAL_NAME \n" +
            "FROM\n" +
            "\tXY_CLB_FC_CKD_MAIN fcm\n" +
            "\tLEFT JOIN XY_USER u ON u.USER_ID = fcm.CKD_OP_USER\n" +
            "\tLEFT JOIN XY_VAL xv ON fcm.CKD_FC_TYPE = xv.VAL_ID \n" +
            "\tAND xv.VALSET_ID = '39AB9E59F1EF4CF6ACD71CF9D89F8129' \n" +
            "WHERE\n" +
            "\tCTR_CODE = #{ctrCcode,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> fcList(String ctrCcode) throws SQLException;

    @Select("<script>" +
            "SELECT\n" +
            "\tB.CKD_CODE, \n" +
            "\tB.CTR_CODE, \n" +
            "\tB.CKD_TYPE, \n" +
            "\tTO_CHAR(B.CKD_INPUT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_INPUT_DATE, \n" +
            "\tB.CKD_FC_TYPE, \n" +
            "\tB.CKD_CK, \n" +
            "\tB.CKD_OP_USER, \n" +
            "\tB.CKD_ZJ, \n" +
            "\tTO_CHAR(B.CKD_AUDIT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_AUDIT_DATE,\n" +
            "\tB.CKD_STATU, \n" +
            "\tB.CKD_MARK,\n" +
            "\t(SELECT xpy.ys_statu FROM XY_PG_YS xpy " +
            "WHERE xpy.ctr_code = #{ctrCode,jdbcType=VARCHAR} AND xpy.ys_gz = #{ysGz,jdbcType=VARCHAR}) ys_status\n" +
            "FROM XY_CLB_FC_CKD_MAIN B WHERE B.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND B.CKD_FC_TYPE=#{ckdFcType,jdbcType=VARCHAR}\n" +
            "UNION  ALL\n" +
            "SELECT \n" +
            "\tC.CKD_CODE, \n" +
            "\tC.CTR_CODE, \n" +
            "\tC.CKD_TYPE, \n" +
            "\tTO_CHAR(C.CKD_INPUT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_INPUT_DATE, \n" +
            "\tC.CKD_FC_TYPE, \n" +
            "\tC.CKD_CK, \n" +
            "\tC.CKD_OP_USER, \n" +
            "\tC.CKD_ZJ, \n" +
            "\tTO_CHAR(C.CKD_AUDIT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_AUDIT_DATE,\n" +
            "\tC.CKD_STATU, \n" +
            "\tC.CKD_MARK,\n" +
            "\t(SELECT xpyb.ys_statu FROM XY_PG_YS xpyb " +
            "WHERE xpyb.ctr_code = #{ctrCode,jdbcType=VARCHAR} AND xpyb.ys_gz = #{ysGz,jdbcType=VARCHAR}) ys_status \n" +
            "FROM XY_CLB_FC_CKD_MAIN C WHERE C.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND C.CKD_TYPE='1' AND C.CKD_FC_TYPE <> #{ckdFcType,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> getEngineeringExpenseSettlementDetail(@Param("ctrCode") String ctrCode,
                                                                          @Param("ysGz") String ysGz,@Param("ckdFcType") String ckdFcType) throws SQLException;

}
