package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbFcCkdMain;
import org.apache.ibatis.annotations.*;
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
            "\tu.USER_NAME\n" +
            "FROM\n" +
            "\tXY_CLB_FC_CKD_MAIN fcm\n" +
            "\tLEFT JOIN XY_USER u ON u.USER_ID = fcm.CKD_OP_USER\n" +
            "WHERE\n" +
            "\tCTR_CODE = #{ctrCcode,jdbcType=VARCHAR}" +
            "\tORDER BY fcm.CKD_INPUT_DATE DESC" +
            "</script>")
    public List<Map<String,Object>> fcList(String ctrCcode) throws SQLException;

    @Select("<script>" +
            "SELECT\n" +
            "\tB.CKD_CODE, \n" +
            "\tB.CTR_CODE, \n" +
            "\tB.CKD_TYPE, \n" +
            "\tTO_CHAR(B.CKD_INPUT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_INPUT_DATE, \n" +
            "\t(SELECT xv.VAL_NAME FROM XY_VAL xv WHERE xv.VAL_ID = B.CKD_FC_TYPE AND xv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C') CKD_FC_TYPE, \n" +
            "\tB.CKD_CK, \n" +
            "\tB.CKD_OP_USER, \n" +
            "\tB.CKD_ZJ, \n" +
            "\tTO_CHAR(B.CKD_AUDIT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_AUDIT_DATE,\n" +
            "\tB.CKD_STATU, \n" +
            "\tB.CKD_MARK\n" +
            "FROM XY_CLB_FC_CKD_MAIN B WHERE B.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND B.CKD_FC_TYPE=#{ckdFcType,jdbcType=VARCHAR}\n" +
            "UNION  ALL\n" +
            "SELECT \n" +
            "\tC.CKD_CODE, \n" +
            "\tC.CTR_CODE, \n" +
            "\tC.CKD_TYPE, \n" +
            "\tTO_CHAR(C.CKD_INPUT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_INPUT_DATE, \n" +
            "\t(SELECT xv.VAL_NAME FROM XY_VAL xv WHERE xv.VAL_ID = C.CKD_FC_TYPE AND xv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C') CKD_FC_TYPE, \n" +
            "\tC.CKD_CK, \n" +
            "\tC.CKD_OP_USER, \n" +
            "\tC.CKD_ZJ, \n" +
            "\tTO_CHAR(C.CKD_AUDIT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_AUDIT_DATE,\n" +
            "\tC.CKD_STATU, \n" +
            "\tC.CKD_MARK\n" +
            "FROM XY_CLB_FC_CKD_MAIN C WHERE C.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND C.CKD_TYPE='1' AND C.CKD_FC_TYPE <![CDATA[<>]]> #{ckdFcType,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> getEngineeringExpenseSettlementDetail(@Param("ctrCode") String ctrCode, @Param("ckdFcType") String ckdFcType) throws SQLException;

    /**
     * 
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/17 15:06
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tC.CKD_CODE, \n" +
            "\tC.CTR_CODE, \n" +
            "\tC.CKD_TYPE, \n" +
            "\tTO_CHAR(C.CKD_INPUT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_INPUT_DATE, \n" +
            "\t(SELECT xv.VAL_NAME FROM XY_VAL xv WHERE xv.VAL_ID = C.CKD_FC_TYPE AND xv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C') CKD_FC_TYPE, \n" +
            "\tC.CKD_CK, \n" +
            "\tC.CKD_OP_USER, \n" +
            "\tC.CKD_ZJ, \n" +
            "\tTO_CHAR(C.CKD_AUDIT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_AUDIT_DATE,\n" +
            "\tC.CKD_STATU, \n" +
            "\tC.CKD_MARK\n" +
            "FROM XY_CLB_FC_CKD_MAIN C WHERE C.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getAllEngineeringExpenseSettlementDetail(@Param("ctrCode") String ctrCode)throws SQLException;

    /**
     * 获取最新fcCkdCode
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/12 17:38
     * @param: [ctrCode]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT B.CKD_CODE+1  FROM ( \n" +
            "\tSELECT A.*, ROWNUM RN \n" +
            "\t\tFROM ( \n" +
            "\t\t\tSELECT \n" +
            "\t\t\t\txcfcm.CKD_CODE \n" +
            "\t\t\tFROM \n" +
            "\t\t\t\tXY_CLB_FC_CKD_MAIN xcfcm\n" +
            "\t\t\tWHERE \n" +
            "\t\t\t\txcfcm.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}\n" +
            "\t\t\tORDER BY xcfcm.CKD_CODE DESC\n" +
            "\t\t) A\n" +
            "\t) B\n" +
            "WHERE RN BETWEEN 1 AND 1" +
            "</script>")
    public String getNewFcCkdCode(@Param("ctrCode") String ctrCode) throws SQLException;

    /**
     * 根据ckdCode删除
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/14 14:03
     * @param: [ckdCode]
     * @return: void
     */
    @Select("<script>" +
            "DELETE FROM XY_CLB_FC_CKD_MAIN WHERE CKD_CODE = #{ckdCode,jdbcType=VARCHAR}" +
            "</script>")
    public void deleteByCkdCode(String ckdCode) throws SQLException;

    /**
     * 根据出库单类型判断是否为首次开单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/15 15:12
     * @param: [ctrCode, ckdFcType]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT COUNT(1) FROM XY_CLB_FC_CKD_MAIN xcfcm WHERE xcfcm.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} AND CKD_FC_TYPE = #{ckdFcType,jdbcType=VARCHAR}" +
            "</script>")
    public Integer isFristKd(@Param("ctrCode") String ctrCode, @Param("ckdFcType") String ckdFcType) throws SQLException;

    /**
     * 获取CkdCode
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/6 9:42
     * @param: [ctrCode]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tB.CKD_CODE + 1 CKD_CODE\n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tA.*,\n" +
            "\t\tROWNUM RN \n" +
            "\tFROM\n" +
            "\t\t( SELECT xcfcm.CKD_CODE FROM XY_CLB_FC_CKD_MAIN xcfcm WHERE xcfcm.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} ORDER BY xcfcm.CKD_CODE DESC ) A \n" +
            "\t) B \n" +
            "WHERE RN BETWEEN 1 AND 1" +
            "</script>")
    public String getCkdCode(String ctrCode) throws SQLException;

    /**
     * 一键开单添加出库单主表
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/16 16:39
     * @param: [ctrCode, rgStage, ckdOpUser]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_CLB_FC_CKD_MAIN ( CKD_CODE, CTR_CODE, CKD_TYPE, CKD_FC_TYPE, CKD_CK, CKD_OP_USER, CKD_ZJ, CKD_STATU )\n" +
            "VALUES\n" +
            "\t(\n" +
            "\t\t#{ ckdCode, jdbcType = VARCHAR }, \n" +
            "\t\t#{ ctrCode, jdbcType = VARCHAR },\n" +
            "\t\t0, \n" +
            "\t\t#{ ckdFcType, jdbcType = VARCHAR },\n" +
            "\t\t'金盛仓库', \n" +
            "\t\t#{ ckdOpUser,\tjdbcType = VARCHAR },\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tSUM( A.HJ ) \n" +
            "\t\tFROM\n" +
            "\t\t\tVW_XY_CLB_FC_CKD_FIRST A \n" +
            "\t\tWHERE\n" +
            "\t\t\tA.CTR_CODE = #{ ctrCode, jdbcType = VARCHAR } \n" +
            "\t\tAND \n" +
            "\t\t\tA.RG_STAGE = #{ ckdFcType, jdbcType = VARCHAR } \n" +
            "\t\t),\n" +
            "\t0 \n" +
            "\t)" +
            "</script>")
    @Options(useGeneratedKeys=true, keyProperty="ckdCode", keyColumn="CKD_CODE")
    public void autoOpenOrderAddCkdMain(XyClbFcCkdMain xyClbFcCkdMain) throws SQLException;

    /**
     * 添加出库单主表二版
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/30 10:22
     * @param: [xyClbFcCkdMain, sqlStr]
     * @return: void
     */
    @SelectProvider(type = autoOpenOrderAddCkdMainTow.class,method = "autoOpenOrderAddCkdMainTow")
    @Options(useGeneratedKeys=true, keyProperty="ckdCode", keyColumn="CKD_CODE")
    public void autoOpenOrderAddCkdMainTow(@Param("ckdCode") String ckdCode ,@Param("ctrCode") String ctrCode ,
                                           @Param("ckdFcType") String ckdFcType ,@Param("ckdOpUser") String ckdOpUser,
                                           String sqlStr) throws SQLException;
    class autoOpenOrderAddCkdMainTow{
        public String autoOpenOrderAddCkdMainTow(@Param("ckdCode") String ckdCode ,@Param("ctrCode") String ctrCode ,
                                                 @Param("ckdFcType") String ckdFcType ,@Param("ckdOpUser") String ckdOpUser ,String sqlStr){
            String tempSqlStr = "" +
                    "INSERT INTO XY_CLB_FC_CKD_MAIN ( CKD_CODE, CTR_CODE, CKD_TYPE, CKD_FC_TYPE, CKD_CK, CKD_OP_USER, CKD_ZJ, CKD_STATU )\n" +
                    "VALUES\n" +
                    "\t(\n" +
                    "\t\t#{ ckdCode, jdbcType = VARCHAR }, \n" +
                    "\t\t#{ ctrCode, jdbcType = VARCHAR },\n" +
                    "\t\t0, \n" +
                    "\t\t#{ ckdFcType, jdbcType = VARCHAR },\n" +
                    "\t\t'金盛仓库', \n" +
                    "\t\t#{ ckdOpUser,\tjdbcType = VARCHAR },\n" +
                    "\t\t(\n" +
                    "SELECT\n" +
                    "\t\t\tSUM( T.HJ ) \n" +
                    "FROM\n" +
                    "\tVW_XY_CLB_FC_CKD_FIRST T \n" +
                    "WHERE\n" +
                    "\t("+sqlStr+" T.P_ID IS NULL ) \n" +
                    "\tAND T.RG_STAGE = #{ckdFcType,jdbcType=VARCHAR} \n" +
                    "\tAND T.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} "  +
                    "\t\t),\n" +
                    "\t0 \n" +
                    "\t)";
            return tempSqlStr;
        }
    }

    /**
     * 获取出库单信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/30 14:31
     * @param: [ckdCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCKD_CODE, \n" +
            "\tCTR_CODE, \n" +
            "\tCKD_TYPE, \n" +
            "\tTO_CHAR(CKD_INPUT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_INPUT_DATE, \n" +
            "\tCKD_FC_TYPE, \n" +
            "\tCKD_CK, \n" +
            "\tCKD_OP_USER, \n" +
            "\tCKD_ZJ, \n" +
            "\tTO_CHAR(CKD_AUDIT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_AUDIT_DATE, \n" +
            "\tCKD_STATU, \n" +
            "\tCKD_MARK ,\n" +
            "\tB.USER_NAME CKD_OP_USER_NAME\n" +
            "FROM\n" +
            "\tXY_CLB_FC_CKD_MAIN A,\n" +
            "\tXY_USER B\n" +
            "WHERE\n" +
            "\tA.CKD_CODE = #{ckdCode,jdbcType=VARCHAR}\n" +
            "AND\n" +
            "\tA.CKD_OP_USER = B.USER_ID" +
            "</script>")
    public Map<String ,Object> getCkdInfo(String ckdCode) throws SQLException;

}
