package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyPg;
import org.apache.ibatis.annotations.*;
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
            "\txp.PG_ID = #{pgId}" +
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
            "\tPG_GR = #{grId}\n" +
            "WHERE\n" +
            "\tPG_ID = #{pgId}\t" +
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
            "\tTO_CHAR(pg.PG_BEGIN_DATE,'yyyy-MM-dd HH24:mi:ss') PG_BEGIN_DATE,\n" +
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
            "\tpg.CTR_CODE = #{ctrCode}" +
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
            "\t\t\t\txp.PG_GR = #{grId}\n" +
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
            "\t\txp.PG_GR = #{grId}\n" +
            ") A\n" +
            "WHERE A.CTR_TEL = #{condition}\n" +
            "OR A.CTR_NAME = #{condition}\n" +
            "OR A.CTR_CODE = #{condition}" +
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
            "SELECT xp.PG_GR FROM XY_PG xp WHERE xp.CTR_CODE = #{ctrCode}" +
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
            "\txp.CTR_CODE = #{ctrCode}" +
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
            "                 WHERE A.PG_ID = #{pgId}\n" +
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

    @Select("<script>" +
            "SELECT pg.PG_ID,pg.CTR_CODE,TO_CHAR(pg.PG_OP_DATE,'yyyy-MM-dd HH24:mi:ss') PG_OP_DATE,\n" +
            "\t\tpg.PG_STAGE,PG_GR,TO_CHAR(pg.PG_BEGIN_DATE,'yyyy-MM-dd') PG_BEGIN_DATE,\n" +
            "\t\tpg.PG_DAYS,pg.PG_OP_USER,pg.PG_MONEY_YN,pg.PG_PRINT_YN,pg.PG_ADD_MONEY,\n" +
            "\t\t(SELECT xv.VAL_NAME FROM XY_VAL xv WHERE xv.VAL_ID=pg.PG_STAGE AND xv.VALSET_ID='B3B32F221FF14256988E7C0A218EBF5C') PG_STAGE_NAME,\n" +
            "\t\t(SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID=pg.PG_OP_USER) OP_USER_NAME\n" +
            "FROM XY_PG pg\n" +
            "WHERE pg.PG_GR=#{pgGr,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> getMyPrjList(@Param("pgGr") String pgGr) throws SQLException;
}