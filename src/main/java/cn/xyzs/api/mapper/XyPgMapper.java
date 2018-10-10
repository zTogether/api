package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyPg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

}
