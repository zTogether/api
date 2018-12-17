package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyPdca;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;;import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyPdcaMapper extends Mapper<XyPdca> {

    /**
     * 根据userId获取所有的报表
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/10 9:52
     * @param: [userId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txp.PDCA_ID,\n" +
            "\txp.USER_ID, \n" +
            "\tNVL(xp.POSITION, ' ') POSITION, \n" +
            "\tTO_CHAR(xp.PDCA_DATE,'yyyy-MM-dd') PDCA_DATE, \n" +
            "\txp.PRESOURCE, \n" +
            "\txp.OPINION, \n" +
            "\txp.STATU, \n" +
            "\tNVL(xp.RES, ' ') RES,\n" +
            "\txp.ISSUE,\n" +
            "\txu.USER_NAME\n" +
            "FROM\n" +
            "\tXY_PDCA xp,\n" +
            "\tXY_USER xu\n" +
            "WHERE\n" +
            "\txp.USER_ID = xu.USER_ID\n" +
            "AND\n" +
            "\txp.USER_ID = #{userId,jdbcType=VARCHAR}" +
            "AND \n" +
            "\txp.PDCA_DATE\n" +
            "BETWEEN TO_DATE(#{beginDate,jdbcType=VARCHAR}, 'yyyy-MM-dd HH24:mi:ss')\n" +
            "AND TO_DATE(#{endDate,jdbcType=VARCHAR}, 'yyyy-MM-dd HH24:mi:ss') " +
            " ORDER BY xp.PDCA_DATE DESC" +
            "</script>")
    public List<Map<String ,Object>> getPdcaByUserId(@Param("userId") String userId, @Param("beginDate") String beginDate, @Param("endDate") String endDate) throws SQLException;

    /**
     * 获取下级pdca
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/10 17:12
     * @param: [userId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txp.PDCA_ID,\n" +
            "\txp.USER_ID, \n" +
            "\tNVL(xp.POSITION, ' ') POSITION, \n" +
            "\tTO_CHAR(xp.PDCA_DATE,'yyyy-MM-dd') PDCA_DATE, \n" +
            "\txp.PRESOURCE, \n" +
            "\txp.OPINION, \n" +
            "\txp.STATU, \n" +
            "\tNVL(xp.RES, ' ') RES,\n" +
            "\txp.ISSUE,\n" +
            "\txu.USER_NAME\n" +
            "FROM\n" +
            "\tXY_PDCA xp,\n" +
            "\tXY_USER xu\n" +
            "WHERE\n" +
            "\txp.USER_ID = xu.USER_ID\n" +
            "AND\n" +
            "\txp.USER_ID IN (\n" +
            "\t\tSELECT\n" +
            "\t\t\txur.FOLLOWER_ID \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_USER_RELATION_PDCA xur \n" +
            "\t\tWHERE \n" +
            "\t\t\txur.LEADER_ID <![CDATA[!=]]> #{userId,jdbcType=VARCHAR}\n" +
            "\t\tSTART WITH xur.LEADER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\tCONNECT BY xur.LEADER_ID = PRIOR xur.FOLLOWER_ID\t\t\t\n" +
            "\t)" +
            "</script>")
    public List<Map<String ,Object>> getSubordinatePdca(String userId) throws SQLException;

    /**
     *
     * @Description: 查询每周的工作问题，需要资源，总经理意见
     * @author: GeWeiliang
     * @date: 2018\11\24 0024 11:05
     * @param: [userId, pdcaId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM XY_PDCA\n" +
            "WHERE USER_ID=#{userId,jdbcType=VARCHAR} AND PDCA_ID=#{pdcaId,jdbcType=VARCHAR}" +
            "</script>")
    List<Map<String,Object>> getWeekContent(@Param("userId") String userId, @Param("pdcaId") String pdcaId) throws SQLException;

    /**
     *
     * @Description: 获取周总结及计划
     * @author: GeWeiliang
     * @date: 2018\11\26 0026 17:13
     * @param: [pdcaId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM XY_PDCA_LISTX WHERE PDCA_ID=#{pdcaId,jdbcType=VARCHAR} AND CLASSIFY='1' ORDER BY RES" +
            "</script>")
    List<Map<String,Object>> getWeekSummary(@Param("pdcaId") String pdcaId) throws SQLException;
    @Select("<script>" +
            "SELECT * FROM XY_PDCA_LISTX WHERE PDCA_ID=#{pdcaId,jdbcType=VARCHAR} AND CLASSIFY='2' ORDER BY RES" +
            "</script>")
    List<Map<String,Object>> getWeekPlan(@Param("pdcaId") String pdcaId) throws SQLException;
    /**
     *
     * @Description: 获取每天的内容
     * @author: GeWeiliang
     * @date: 2018\11\25 0025 10:14
     * @param: [pdcaId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM XY_PDCA_LIST \n" +
            "WHERE PDCA_ID=#{pdcaId,jdbcType=VARCHAR} ORDER BY DATES,RES" +
            "</script>")
    List<Map<String,Object>> getDayContent(@Param("pdcaId") String pdcaId) throws SQLException;

    /**
     *
     * @Description: 增加内容行
     * @author: GeWeiliang
     * @date: 2018\11\26 0026 18:47
     * @param: [pdcaId, week, date, res]
     * @return: void
     */
    @Insert("<script>" +
            "\n" +
            "INSERT INTO XY_PDCA_LIST VALUES(sys_guid(),#{pdcaId,jdbcType=VARCHAR},#{week,jdbcType=VARCHAR},TO_DATE(#{date},'yy-MM-dd'),'','',#{PSummary},#{res,jdbcType=VARCHAR})" +
            "</script>")
    void addPdcaList(@Param("pdcaId") String pdcaId, @Param("week") String week, @Param("date") String date,@Param("PSummary") String PSummary, @Param("res") String res) throws SQLException;

    /**
     *
     * @Description: 删除内容行
     * @author: GeWeiliang
     * @date: 2018\11\26 0026 18:47
     * @param: [pdcaId, week, res]
     * @return: void
     */
    @Delete("<script>" +
            "DELETE FROM XY_PDCA_LIST WHERE PDCA_ID=#{pdcaId,jdbcType=VARCHAR}  AND WEEK=#{week,jdbcType=VARCHAR} AND RES=#{res,jdbcType=VARCHAR}" +
            "</script>")
    void deletePdcaList(@Param("pdcaId") String pdcaId, @Param("week") String week, @Param("res") String res) throws SQLException;

    @Update("<script>" +
            "UPDATE XY_PDCA_LIST SET PCONTENT=#{content,jdbcType=VARCHAR} WHERE PDCA_ID=#{pdcaId,jdbcType=VARCHAR} AND RES =#{res,jdbcType=VARCHAR} AND WEEK=#{week,jdbcType=VARCHAR} " +
            "</script>")
    void updatePdcaPcontent(@Param("pdcaId") String pdcaId, @Param("week") String week, @Param("res") String res,
                            @Param("content") String content) throws SQLException;

    @Update("<script>" +
            "UPDATE XY_PDCA_LIST SET PSUMMARY=#{content,jdbcType=VARCHAR} WHERE PDCA_ID=#{pdcaId,jdbcType=VARCHAR} AND WEEK=#{week,jdbcType=VARCHAR}" +
            "</script>")
    void updatePdcaSummary(@Param("pdcaId") String pdcaId, @Param("week") String week, @Param("content") String content) throws SQLException;

    //更新周总结
    @Update("<script>" +
            "UPDATE XY_PDCA_LISTX SET PCONTENT =#{content,jdbcType=VARCHAR} WHERE PDCA_ID=#{pdcaId,jdbcType=VARCHAR} AND CLASSIFY='1' AND RES=#{res,jdbcType=VARCHAR}" +
            "</script>")
    void updateWeekSummary(@Param("pdcaId") String pdcaId,@Param("content") String content,@Param("res") String res) throws SQLException;
    @Insert("<script>" +
            "INSERT INTO XY_PDCA_LISTX VALUES(sys_guid(),#{pdcaId,jdbcType=VARCHAR},'1','',#{res,jdbcType=VARCHAR}) " +
            "</script>")
    void addSumCol(@Param("pdcaId") String pdcaId,@Param("res") String res) throws SQLException;

    //更新下周计划
    @Update("<script>" +
            "UPDATE XY_PDCA_LISTX SET PCONTENT =#{content,jdbcType=VARCHAR} WHERE PDCA_ID=#{pdcaId,jdbcType=VARCHAR} AND CLASSIFY='2' AND RES=#{res,jdbcType=VARCHAR}" +
            "</script>")
    void updateWeekPlan(@Param("pdcaId") String pdcaId, @Param("content") String content,@Param("res") String res) throws SQLException;
    @Insert("<script>" +
            "INSERT INTO XY_PDCA_LISTX VALUES(sys_guid(),#{pdcaId,jdbcType=VARCHAR},'2','',#{res,jdbcType=VARCHAR})" +
            "</script>")
    void addPlanCol(@Param("pdcaId") String pdcaId,@Param("res") String res) throws SQLException;

    //删除周总结
    @Delete("<script>" +
            "DELETE FROM XY_PDCA_LISTX WHERE PDCA_ID=#{pdcaId,jdbcType=VARCHAR} AND RES=#{res,jdbcType=VARCHAR} AND CLASSIFY='1'" +
            "</script>")
    void deleteWeekSum(@Param("pdcaId") String pdcaId,@Param("res") String res) throws SQLException;
    //删除下周计划
    @Delete("<script>" +
            "DELETE FROM XY_PDCA_LISTX WHERE PDCA_ID=#{pdcaId,jdbcType=VARCHAR} AND RES=#{res,jdbcType=VARCHAR} AND CLASSIFY='2'" +
            "</script>")
    void deleteWeekPlan(@Param("pdcaId") String pdcaId,@Param("res") String res) throws SQLException;

    @Update("<script>" +
            "UPDATE XY_PDCA SET POSITION=#{position,jdbcType=VARCHAR} ,PRESOURCE=#{PResources,jdbcType=VARCHAR} ,OPINION=#{opinion,jdbcType=VARCHAR} ,ISSUE=#{issue,jdbcType=VARCHAR}\n" +
            "WHERE PDCA_ID=#{pdcaId,jdbcType=VARCHAR}" +
            "</script>")
    void updatePdca(@Param("pdcaId") String pdcaId, @Param("position") String position, @Param("PResources") String PResources,
                    @Param("opinion") String opinion, @Param("issue") String issue) throws SQLException;

    @Update("<script>" +
            "UPDATE XY_PDCA SET POSITION=#{date} WHERE PDCA_ID=#{pdcaId}" +
            "</script>")
    void updateLastDate(@Param("date") String date,@Param("pdcaId") String pdcaId) throws SQLException;
}
