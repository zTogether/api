package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyPg;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyPgMapper extends Mapper<XyPg>{

    /**
     * 根据pgId获取此标的详情
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:24
     * @param: [pgId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\tXY_PG xp\n" +
            "WHERE\n" +
            "\txp.PG_ID = #{pgId,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String ,Object> getXyPgInfoByPgId(String pgId) throws SQLException;

    /**
     * 修改派工主表的工人id
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:25
     * @param: [pgId, grId]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_PG\n" +
            "SET\n" +
            "\tPG_GR = #{grId,jdbcType=VARCHAR}\n" +
            "WHERE\n" +
            "\tPG_ID = #{pgId,jdbcType=VARCHAR}\t" +
            "</script>")
    public void updatePgGr(@Param("pgId") String pgId , @Param("grId") String grId) throws SQLException;

    /**
     *
     * @Description: 工程清单
     * @author: GeWeiliang
     * @date: 2018\9\19 0019 9:14
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tpg.PG_ID, \n" +
            "\tpg.CTR_CODE,\n" +
            "\tTO_CHAR(pg.PG_OP_DATE,'yyyy-MM-dd HH24:mi:ss') PG_OP_DATE,\n" +
            "\tpg.PG_STAGE,\n" +
            "\tpg.PG_GR,\n" +
            "\tTO_CHAR(pg.PG_BEGIN_DATE,'yyyy-MM-dd') PG_BEGIN_DATE,\n" +
            "\tpg.PG_DAYS,\n" +
            "\tpg.PG_OP_USER,\n" +
            "\tpg.PG_MONEY_YN,\n" +
            "\tpg.PG_PRINT_YN,\n" +
            "\tpg.PG_ADD_MONEY,\n" +
            "\tu.USER_NAME pg_op_name,\n" +
            "\tu.USER_TEL,\n" +
            "\tgr.GR_NAME,\n" +
            "\tgr.GR_TEL,\n" +
            "\txv.VAL_NAME\n" +
            "FROM\n" +
            "\tXY_PG pg\n" +
            "\tLEFT JOIN XY_USER u ON u.USER_ID = pg.PG_OP_USER\n" +
            "\tLEFT JOIN XY_GCB_GRXX gr ON gr.GR_ID = pg.PG_GR\n" +
            "\tLEFT JOIN XY_VAL xv ON pg.PG_STAGE = xv.VAL_ID \n" +
            "\tAND xv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C' \n" +
            "WHERE\n" +
            "\tpg.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> getPrjList(String ctrCode) throws SQLException;

    /**
     * 工人聊天群，获取工人工地(分页)
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/30 11:15
     * @param: [grId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT *  FROM ( \n" +
            "\tSELECT A.*, ROWNUM RN \n" +
            "\t\tFROM ( \n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\txci.CTR_CODE,\n" +
            "\t\t\t\txci.CTR_NAME,\n" +
            "\t\t\t\txci.CTR_TEL,\n" +
            "\t\t\t\txci.CTR_ADDR\n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tXY_PG xp \n" +
            "\t\t\tLEFT JOIN XY_CUSTOMER_INFO xci ON xp.CTR_CODE = xci.CTR_CODE\n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\txp.PG_GR = #{grId,jdbcType=VARCHAR}\n" +
            "\t\t) A\n" +
            "\t)\n" +
            "WHERE RN BETWEEN #{startNum} AND #{endNum}" +
            "</script>")
    public List<Map<String ,Object>> getGrConstructionSite(@Param("grId") String grId ,@Param("startNum") String startNum ,@Param("endNum") String endNum) throws SQLException;

    /**
     * 工人聊天群，根据条件获取工人工地
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/30 13:46
     * @param: [grId, ctrTel, ctrName, ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM(\n" +
            "\tSELECT\n" +
            "\t\txci.CTR_CODE,\n" +
            "\t\txci.CTR_NAME,\n" +
            "\t\txci.CTR_TEL,\n" +
            "\t\txci.CTR_ADDR\n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tLEFT JOIN XY_CUSTOMER_INFO xci ON xp.CTR_CODE = xci.CTR_CODE\n" +
            "\tWHERE\n" +
            "\t\txp.PG_GR = #{grId,jdbcType=VARCHAR}\n" +
            ") A\n" +
            "WHERE A.CTR_TEL = #{condition,jdbcType=VARCHAR}\n" +
            "OR A.CTR_NAME = #{condition,jdbcType=VARCHAR}\n" +
            "OR A.CTR_CODE = #{condition,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getGrConstructionSiteByCondition(@Param("grId") String grId ,@Param("condition") String condition) throws SQLException;

    /**
     * 根据ctrCode获取所有为其服务的工人
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/3 9:24
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT xp.PG_GR FROM XY_PG xp WHERE xp.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,String>> getGrIdS(String ctrCode) throws SQLException;

    /**
     * 根据ctrCode获取所有为其服务的工人姓名与Id
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 8:54
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txp.PG_GR,\n" +
            "\tcgg.GR_NAME\n" +
            "FROM\n" +
            "\tXY_PG xp \n" +
            "LEFT JOIN XY_GCB_GRXX cgg ON xp.PG_GR = cgg.GR_ID\n" +
            "WHERE\n" +
            "\txp.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getGrInfoListByCtrCode(String ctrCode) throws SQLException;

    /**
     * 判断是否存在重复派工
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/10 16:18
     * @param: [ctrCode, pgStage]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT COUNT(1) FROM XY_PG xp WHERE xp.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} AND xp.PG_STAGE = #{pgStage,jdbcType=VARCHAR} AND xp.PG_GR IS NULL" +
            "</script>")
    public Integer isRepetitionPg(@Param("ctrCode") String ctrCode ,@Param("pgStage") String pgStage ) throws SQLException;

    /**
     * 添加派工主表信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/10 17:03
     * @param: [ctrCode, pgStage, pgBeginDate, pgOpUser]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_PG ( PG_ID, CTR_CODE, PG_STAGE, PG_BEGIN_DATE, PG_OP_USER )\n" +
            "VALUES\n" +
            "\t( sys_guid (), #{ctrCode,jdbcType=VARCHAR}, #{pgStage,jdbcType=VARCHAR}, TO_DATE( #{pgBeginDate,jdbcType=VARCHAR}, 'yyyy-MM-dd HH24:mi:ss' ), #{pgOpUser,jdbcType=VARCHAR} )" +
            "</script>")
    @Options(useGeneratedKeys=true, keyProperty="pgId", keyColumn="PG_ID")
    public void addPg(XyPg xyPg) throws SQLException;

    /**
     * 根据pgId修改pgGr
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/11 10:16
     * @param: []
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_PG SET PG_GR=#{pgGr,jdbcType=VARCHAR} WHERE PG_ID=#{pgId,jdbcType=VARCHAR}" +
            "</script>")
    public void updatePgGrByPgId(@Param("pgGr") String pgGr ,@Param("pgId") String pgId) throws SQLException;

    @Select("<script>" +
            "SELECT gr_id, del_sq\n" +
            "  FROM (SELECT (SELECT distinct B.PG_GR\n" +
            "                          FROM XY_PG B\n" +
            "                         WHERE B.CTR_CODE = A.CTR_CODE\n" +
            "                         AND A.PG_ID != B.PG_ID\n" +
            "                           AND (CASE A.PG_STAGE\n" +
            "                                 WHEN '60' THEN\n" +
            "                                  '22'\n" +
            "                                 WHEN '32' THEN\n" +
            "                                  '31'\n" +
            "                                 ELSE\n" +
            "                                  A.PG_STAGE\n" +
            "                               END) = (CASE B.PG_STAGE\n" +
            "                                 WHEN '60' THEN\n" +
            "                                  '22'\n" +
            "                                 WHEN '32' THEN\n" +
            "                                  '31'\n" +
            "                                 ELSE\n" +
            "                                  B.PG_STAGE\n" +
            "                               END)) gr_id,\n" +
            "                       999999 score,\n" +
            "                       '0' del_sq\n" +
            "                  FROM XY_PG A\n" +
            "                 WHERE A.PG_ID = #{pgId,jdbcType=VARCHAR}\n" +
            "                   AND EXISTS (SELECT B.PG_GR\n" +
            "                          FROM XY_PG B\n" +
            "                         WHERE B.CTR_CODE = A.CTR_CODE\n" +
            "                           AND A.PG_ID != B.PG_ID\n" +
            "                           AND (CASE A.PG_STAGE\n" +
            "                                 WHEN '60' THEN\n" +
            "                                  '22'\n" +
            "                                 WHEN '32' THEN\n" +
            "                                  '31'\n" +
            "                                 ELSE\n" +
            "                                  A.PG_STAGE\n" +
            "                               END) = (CASE B.PG_STAGE\n" +
            "                                 WHEN '60' THEN\n" +
            "                                  '22'\n" +
            "                                 WHEN '32' THEN\n" +
            "                                  '31'\n" +
            "                                 ELSE\n" +
            "                                  B.PG_STAGE\n" +
            "                               END)))" +
            "</script>")
    public List<Map<String,Object>> getMaxGr(@Param("pgId") String pgId);

    @Select("" +
            "UPDATE XY_PG \n" +
            "SET PG_DAYS = (\n" +
            "\tSELECT\n" +
            "\t\t(\n" +
            "\t\tCASE\n" +
            "\t\t\t\t\n" +
            "\t\t\t\tWHEN A.PG_STAGE = '10' \n" +
            "\t\t\t\tAND CTR_PRJ_TYPE = 0 THEN\n" +
            "\t\t\t\t\t3 \n" +
            "\t\t\t\t\tWHEN A.PG_STAGE = '21' \n" +
            "\t\t\t\t\tAND CTR_PRJ_TYPE = 0 THEN\n" +
            "\t\t\t\t\t\t3 \n" +
            "\t\t\t\t\t\tWHEN A.PG_STAGE = '22' \n" +
            "\t\t\t\t\t\tAND CTR_PRJ_TYPE = 0 THEN\n" +
            "\t\t\t\t\t\t\t5 \n" +
            "\t\t\t\t\t\t\tWHEN A.PG_STAGE = '60' \n" +
            "\t\t\t\t\t\t\tAND CTR_PRJ_TYPE = 0 THEN\n" +
            "\t\t\t\t\t\t\t\t0 ELSE (\n" +
            "\t\t\t\t\t\t\t\tCASE\n" +
            "\t\t\t\t\t\t\t\t\t\t\n" +
            "\t\t\t\t\t\t\t\t\t\tWHEN CTR_PRJ_TYPE = 0 THEN\n" +
            "\t\t\t\t\t\t\t\t\t\tFLOOR((\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tsum( B.RG_HJ ) / 600 \n" +
            "\t\t\t\t\t\t\t\t\t\t\t)) \n" +
            "\t\t\t\t\t\t\t\t\t\tWHEN CTR_PRJ_TYPE = 1 \n" +
            "\t\t\t\t\t\t\t\t\t\tAND FLOOR((\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tsum( B.RG_HJ ) / 600 \n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t)) <= 3 THEN\n" +
            "\t\t\t\t\t\t\t\t\t\t\t3 ELSE FLOOR((\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\tsum( B.RG_HJ ) / 600 \n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t)) \n" +
            "\t\t\t\t\t\t\t\t\t\tEND \n" +
            "\t\t\t\t\t\t\t\t\t\t) \n" +
            "\t\t\t\t\t\t\t\t\tEND \n" +
            "\t\t\t\t\t\t\t\t\t) DAYS \n" +
            "\t\t\t\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\t\t\t\tXY_PG A\n" +
            "\t\t\t\t\t\t\t\t\tLEFT JOIN XY_PG_LIST B ON A.PG_ID = B.PG_ID\n" +
            "\t\t\t\t\t\t\t\t\tLEFT JOIN XY_CUSTOMER_INFO C ON A.CTR_CODE = C.CTR_CODE \n" +
            "\t\t\t\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\t\t\t\tA.PG_ID = #{pgId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\t\t\t\tGROUP BY\n" +
            "\t\t\t\t\t\t\t\t\tA.PG_ID,\n" +
            "\t\t\t\t\t\t\t\t\tA.PG_STAGE,\n" +
            "\t\t\t\t\t\t\t\t\tC.CTR_AREA,\n" +
            "\t\t\t\t\t\t\t\t\tCTR_PRJ_TYPE \n" +
            "\t\t\t\t\t\t\t\t) \n" +
            "\t\t\t\t\t\tWHERE\n" +
            "\tPG_ID = #{pgId,jdbcType=VARCHAR}" +
            "")
    public void updateDays(@Param("pgId") String pgId) throws SQLException;

    /**
     * 获取工资状态为未申请的派工单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/13 10:00
     * @param: [pgGr]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tpg.PG_ID,\n" +
            "\tpg.CTR_CODE,\n" +
            "\tTO_CHAR( pg.PG_OP_DATE, 'yyyy-MM-dd' ) PG_OP_DATE,\n" +
            "\tpg.PG_STAGE,\n" +
            "\tPG_GR,\n" +
            "\tTO_CHAR( pg.PG_BEGIN_DATE, 'yyyy-MM-dd' ) PG_BEGIN_DATE,\n" +
            "\tpg.PG_DAYS,\n" +
            "\tpg.PG_OP_USER,\n" +
            "\tpg.PG_MONEY_YN,\n" +
            "\tpg.PG_PRINT_YN,\n" +
            "\tpg.PG_ADD_MONEY,\n" +
            "\t( SELECT xv.VAL_NAME FROM XY_VAL xv WHERE xv.VAL_ID = pg.PG_STAGE AND xv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C' ) PG_STAGE_NAME,\n" +
            "\t( SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID = pg.PG_OP_USER ) OP_USER_NAME ,\n" +
            "\t( SELECT xci.CTR_ADDR FROM XY_CUSTOMER_INFO xci WHERE xci.CTR_CODE = pg.CTR_CODE ) CTR_ADDR\n" +
            "FROM\n" +
            "\tXY_PG pg \n" +
            "WHERE\n" +
            "\tpg.PG_GR = #{pgGr,jdbcType=VARCHAR} \n" +
            "AND pg.PG_MONEY_YN = '0' \n" +
            "ORDER BY\n" +
            "\tpg.PG_BEGIN_DATE DESC" +
            "</script>")
    public List<Map<String,Object>> getNotApplyEngineeringList(@Param("pgGr") String pgGr) throws SQLException;

    /**
     * 获取工资状态为已申请申请的派工单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/13 10:27
     * @param: [pgGr]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tpg.PG_ID,\n" +
            "\tpg.CTR_CODE,\n" +
            "\tTO_CHAR( pg.PG_OP_DATE, 'yyyy-MM-dd' ) PG_OP_DATE,\n" +
            "\tpg.PG_STAGE,\n" +
            "\tPG_GR,\n" +
            "\tTO_CHAR( pg.PG_BEGIN_DATE, 'yyyy-MM-dd' ) PG_BEGIN_DATE,\n" +
            "\tpg.PG_DAYS,\n" +
            "\tpg.PG_OP_USER,\n" +
            "\tpg.PG_MONEY_YN,\n" +
            "\tpg.PG_PRINT_YN,\n" +
            "\tpg.PG_ADD_MONEY,\n" +
            "\t( SELECT xv.VAL_NAME FROM XY_VAL xv WHERE xv.VAL_ID = pg.PG_STAGE AND xv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C' ) PG_STAGE_NAME,\n" +
            "\t( SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID = pg.PG_OP_USER ) OP_USER_NAME ,\n" +
            "\t( SELECT xci.CTR_ADDR FROM XY_CUSTOMER_INFO xci WHERE xci.CTR_CODE = pg.CTR_CODE ) CTR_ADDR\n" +
            "FROM\n" +
            "\tXY_PG pg \n" +
            "WHERE\n" +
            "\tpg.PG_MONEY_YN = '1' \n" +
            "AND\n" +
            "\tpg.PG_GR = #{pgGr,jdbcType=VARCHAR}\n" +
            "AND pg.PG_ID NOT IN (\n" +
            "\tSELECT\n" +
            "\t\tgl.PG_ID \n" +
            "\tFROM\n" +
            "\t\tXY_CWB_GRGZ_LIST gl \n" +
            "\tWHERE\n" +
            "\t\tgl.GRGZ_ID IN ( SELECT gm.GRGZ_ID FROM XY_CWB_GRGZ_MAIN gm WHERE gm.GRGZ_STATU = '2' AND gm.GR_ID = #{pgGr,jdbcType=VARCHAR} )\n" +
            ")" +
            "ORDER BY\n" +
            "\tpg.PG_BEGIN_DATE DESC" +
            "</script>")
    public List<Map<String,Object>> getApplyEngineeringList(@Param("pgGr") String pgGr) throws SQLException;

    /**
     * 获取已发放工资的派工单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/13 16:55
     * @param: [pgGr]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tgm.GRGZ_ID,\n" +
            "\tTO_CHAR( gm.GRGZ_DATE, 'yyyy-MM-dd' )\tGRGZ_DATE,\n" +
            "\tgm.GRGZ_STATU, \n" +
            "\tgm.GR_ID, \n" +
            "\tgm.CTR_CODE, \n" +
            "\tgm.GRGZ_YF, \n" +
            "\tgm.GRGZ_SF, \n" +
            "\tgm.GRGZ_MARK \n" +
            "FROM \n" +
            "\tXY_CWB_GRGZ_MAIN gm \n" +
            "WHERE \n" +
            "\tgm.GRGZ_STATU = '2' \n" +
            "AND \n" +
            "\tgm.GR_ID = #{pgGr,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getGrgzMainLsit(@Param("pgGr") String pgGr) throws SQLException;

    /**
     * 获取已发放工资的派工单Lsit
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/13 16:59
     * @param: [grgzId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txcgl.RG_JE,\n" +
            "\tpg.PG_ID,\n" +
            "\tpg.CTR_CODE,\n" +
            "\tTO_CHAR( pg.PG_OP_DATE, 'yyyy-MM-dd' ) PG_OP_DATE,\n" +
            "\tpg.PG_STAGE,\n" +
            "\tpg.PG_GR,\n" +
            "\tTO_CHAR( pg.PG_BEGIN_DATE, 'yyyy-MM-dd' ) PG_BEGIN_DATE,\n" +
            "\tpg.PG_DAYS,\n" +
            "\tpg.PG_OP_USER,\n" +
            "\tpg.PG_MONEY_YN,\n" +
            "\tpg.PG_PRINT_YN,\n" +
            "\tpg.PG_ADD_MONEY,\n" +
            "\t( SELECT xv.VAL_NAME FROM XY_VAL xv WHERE xv.VAL_ID = pg.PG_STAGE AND xv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C' ) PG_STAGE_NAME,\n" +
            "\t( SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID = pg.PG_OP_USER ) OP_USER_NAME ,\n" +
            "\t( SELECT xci.CTR_ADDR FROM XY_CUSTOMER_INFO xci WHERE xci.CTR_CODE = pg.CTR_CODE ) CTR_ADDR\n" +
            "FROM\n" +
            "\tXY_CWB_GRGZ_LIST xcgl ,\n" +
            "\tXY_PG pg\n" +
            "WHERE\n" +
            "\txcgl.GRGZ_ID = #{grgzId,jdbcType=VARCHAR}\n" +
            "AND \n" +
            "\tpg.PG_ID = xcgl.PG_ID" +
            "</script>")
    public List<Map<String ,Object>> getEndApplyEngineeringList(@Param("grgzId") String grgzId) throws SQLException;

    @Select("<script>" +
            "SELECT\n" +
            "\tA.CTR_CODE,\n" +
            "\tB.USER_NAME ZXY_NAME,\n" +
            "\tB.USER_TEL ZXY_TEL,\n" +
            "\tC.GR_NAME GR_NAME,\n" +
            "\tC.GR_TEL GR_TEL,\n" +
            "\t(\n" +
            "\tCASE\n" +
            "\t\t\tA.PG_STAGE \n" +
            "\t\t\tWHEN '32' THEN\n" +
            "\t\t\t'镶贴' \n" +
            "\t\t\tWHEN '31' THEN\n" +
            "\t\t\t'砌筑' \n" +
            "\t\t\tWHEN '22' THEN\n" +
            "\t\t\t'电工' \n" +
            "\t\t\tWHEN '50' THEN\n" +
            "\t\t\t'油漆' \n" +
            "\t\t\tWHEN '40' THEN\n" +
            "\t\t\t'木工' \n" +
            "\t\t\tWHEN '10' THEN\n" +
            "\t\t\t'基础' \n" +
            "\t\t\tWHEN '60' THEN\n" +
            "\t\t\t'安装' \n" +
            "\t\t\tWHEN '20' THEN\n" +
            "\t\t\t'水电' \n" +
            "\t\t\tWHEN '30' THEN\n" +
            "\t\t\t'瓦工' \n" +
            "\t\t\tWHEN '21' THEN\n" +
            "\t\t\t'水工' \n" +
            "\t\tEND \n" +
            "\t\t) GZNAME \n" +
            "\tFROM\n" +
            "\t\tXY_PG A,\n" +
            "\t\tXY_USER B,\n" +
            "\t\tXY_GCB_GRXX C,\n" +
            "\t\tXY_CUSTOMER_INFO D \n" +
            "\tWHERE A.PG_GR = C.GR_ID \n" +
            "\tAND A.PG_ID = #{pgId,jdbcType=VARCHAR}\n" +
            "\tAND A.CTR_CODE = D.CTR_CODE \n" +
            "\tAND D.CTR_GCJL = B.USER_ID" +
            "</script>")
    public Map<String,Object> getPgMsg(String pgId) throws SQLException;

    /**
     * 判断是否需要发送信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/27 11:16
     * @param: [pgId]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCOUNT( 1 ) C_NUM \n" +
            "FROM\n" +
            "\tXY_PG A \n" +
            "WHERE\n" +
            "\tEXISTS (\n" +
            "\tSELECT\n" +
            "\t\t1 \n" +
            "\tFROM\n" +
            "\t\tXY_PG T \n" +
            "\tWHERE\n" +
            "\t\tT.PG_ID = #{pgId,jdbcType=VARCHAR} \n" +
            "\t\tAND T.CTR_CODE = A.CTR_CODE \n" +
            "\tAND T.PG_STAGE = A.PG_STAGE \n" +
            "\t)" +
            "</script>")
    public Integer isSendMsg(String pgId) throws SQLException;
}