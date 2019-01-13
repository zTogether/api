package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyInfoManageConstr;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface XyInfoManageConstrMapper extends Mapper<XyInfoManageConstr> {

    /**
     *
     * @Description: 根据档案号获取执行员记录
     * @author: GeWeiliang
     * @date: 2018\12\27 0027 9:56
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM XY_INFOMANAGE_CONSTR\n" +
            "WHERE CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND BELONG_USER_TYPE='1' \n" +
            "ORDER BY CONSTRUCTION_TYPE,XH" +
            "</script>")
    List<Map<String,Object>> getEdRecord(@Param("ctrCode") String ctrCode)throws SQLException;

    /**
     *
     * @Description: 根据档案号获取工程总监记录
     * @author: GeWeiliang
     * @date: 2018\12\27 0027 9:56
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM XY_INFOMANAGE_CONSTR\n" +
            "WHERE CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND BELONG_USER_TYPE='2' \n" +
            "ORDER BY CONSTRUCTION_TYPE,XH" +
            "</script>")
    List<Map<String,Object>> getRbRecord(@Param("ctrCode") String ctrCode)throws SQLException;

    /**
     *
     * @Description: 查询待处理事项
     * @author: GeWeiliang
     * @date: 2018\12\27 0027 10:48
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT ic.*,u.USER_NAME FROM XY_INFOMANAGE_COMP ic\n" +
            "LEFT JOIN XY_USER u ON u.USER_ID=ic.LIABLE_USER\n" +
            "WHERE ic.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}\n" +
            "ORDER BY ic.XH DESC" +
            "</script>")
    List<Map<String,Object>> getCompContent(@Param("ctrCode") String ctrCode)throws SQLException;

    @Insert("<script>" +
            "INSERT INTO XY_INFOMANAGE_CONSTR(CTR_CODE,CONSTRUCTION_TYPE,BELONG_USER,ED_RECODE,RB_RECODE,BELONG_USER_TYPE,XH)\n" +
            "VALUES(#{ctrCode,jdbcType=VARCHAR},#{consType,jdbcType=VARCHAR},#{belongUser,jdbcType=VARCHAR}," +
            "#{edRecode,jdbcType=VARCHAR},#{rbRecode,jdbcType=VARCHAR},#{userType,jdbcType=VARCHAR},sysdate,#{xh,jdbcType=VARCHAR})" +
            "</script>")
    void addConstr(@Param("ctrCode") String ctrCode,@Param("consType") String consType,@Param("belongUser") String belongUser,
                   @Param("edRecode") String edRecode,@Param("rbRecode") String rbRecode,@Param("userType") String userType,@Param("xh") String xh)throws SQLException;

    @Delete("<script>" +
            "DELETE FROM XY_INFOMANAGE_CONSTR\n" +
            "WHERE CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND CONSTRUCTION_TYPE=#{constrType,jdbcType=VARCHAR} " +
            "AND BELONG_USER_TYPE=#{userType,jdbcType=VARCHAR} AND XH=#{xh,jdbcType=VARCHAR}" +
            "</script>")
    void deleteConstr(@Param("ctrCode") String ctrCode,@Param("constrType") String constrType,@Param("userType") String userType,@Param("xh") String xh)throws SQLException;

    @Update("<script>" +
            "UPDATE XY_INFOMANAGE_CONSTR \n" +
            "SET ED_RECODE=#{edRecord,jdbcType=VARCHAR},RB_RECODE=#{rbRecord,jdbcType=VARCHAR}\n" +
            "WHERE CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND CONSTRUCTION_TYPE=#{constrType,jdbcType=VARCHAR} AND BELONG_USER_TYPE=#{userType,jdbcType=VARCHAR} AND XH=#{xh,jdbcType=VARCHAR}" +
            "</script>")
    void updateConstr(@Param("edRecord") String edRecord,@Param("rbRecord") String rbRecord,@Param("ctrCode") String ctrCode,@Param("constrType") String constrType,@Param("userType") String userType,@Param("xh") String xh)throws SQLException;

    @Select("<script>" +
            "SELECT ir.*,u.USER_NAME FROM XY_INFOMANAGE_REVISIT ir\n" +
            "LEFT JOIN XY_USER u ON u.USER_ID=ir.OP_USER\n" +
            "WHERE ir.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}\n" +
            "ORDER BY ir.XH DESC" +
            "</script>")
    List<Map<String,Object>> getRevisit(@Param("ctrCode") String ctrCode)throws SQLException;

    /**
     *
     * @Description: 查询责任人
     * @author: GeWeiliang
     * @date: 2019\1\3 0003 16:39
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT u.USER_NAME,u.USER_ID\n" +
            "FROM XY_USER u,XY_CUSTOMER_INFO i\n" +
            "WHERE i.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND ( i.CTR_GCJL=u.USER_ID OR i.CTR_AREA_MA=u.USER_ID)" +
            "</script>")
    List<Map<String,Object>> getLiableUser(@Param("ctrCode") String ctrCode)throws SQLException;

    /**
     *
     * @Description: 添加投诉
     * @author: GeWeiliang
     * @date: 2019\1\3 0003 16:25
     * @param: [ctrCode, compContent, compType, limitDate, liableUser, opUser, xh]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_INFOMANAGE_COMP\n" +
            "VALUES(sys_guid(),#{ctrCode,jdbcType=VARCHAR},#{compContent,jdbcType=VARCHAR},#{compType,jdbcType=VARCHAR}," +
            "#{solveDate,jdbcType=VARCHAR},#{liableUser,jdbcType=VARCHAR},'',#{opUser,jdbcType=VARCHAR},SYSDATE,'',#{xh,jdbcType=VARCHAR})" +
            "</script>")
    void addComp(@Param("ctrCode") String ctrCode, @Param("compContent") String compContent, @Param("compType") String compType,
                 @Param("solveDate") Date limitDate,@Param("liableUser") String liableUser,@Param("opUser") String opUser,@Param("xh") int xh)throws SQLException;
    //获取责任人电话
    @Select("<script>" +
            "SELECT USER_TEL FROM XY_USER WHERE USER_ID=#{userId,jdbcType=VARCHAR}" +
            "</script>")
    String userTel(@Param("userId") String userId)throws SQLException;

    @Select("<script>" +
            "SELECT * FROM\n" +
            "(SELECT XH FROM XY_INFOMANAGE_COMP \n" +
            "WHERE CTR_CODE=#{ctrCode,jdbcType=VARCHAR}\n" +
            "ORDER BY XH DESC)\n" +
            "WHERE ROWNUM &lt; 2" +
            "</script>")
    String getCompNum(@Param("ctrCode") String ctrCode)throws SQLException;

    /**
     *
     * @Description: 删除投诉记录
     * @author: GeWeiliang
     * @date: 2019\1\2 0002 11:32
     * @param: [rowId]
     * @return: void
     */
    @Delete("<script>" +
            "DELETE FROM XY_INFOMANAGE_COMP\n" +
            "WHERE ROW_ID=#{rowId,jdbcType=VARCHAR}" +
            "</script>")
    void deleteComp(@Param("rowId") String rowId)throws SQLException;

    @Update("<script>" +
            "UPDATE XY_INFOMANAGE_COMP \n" +
            "SET LIABLE_RES=#{liableRes,jdbcType=VARCHAR},RES_DATE=SYSDATE\n" +
            "WHERE ROW_ID=#{rowId,jdbcType=VARCHAR}" +
            "</script>")
    void resComp(@Param("rowId") String rowId,@Param("liableRes") String liableRes)throws SQLException;

    /**
     *
     * @Description: 查询回访记录条数
     * @author: GeWeiliang
     * @date: 2019\1\2 0002 11:32
     * @param: [ctrCode]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT * FROM\n" +
            "(SELECT XH FROM XY_INFOMANAGE_REVISIT \n" +
            "WHERE CTR_CODE=#{ctrCode,jdbcType=VARCHAR}\n" +
            "ORDER BY XH DESC)\n" +
            "WHERE ROWNUM &lt; 2" +
            "</script>")
    String getRevisitNum(@Param("ctrCode") String ctrCode)throws SQLException;

    /**
     *
     * @Description: 添加回访记录
     * @author: GeWeiliang
     * @date: 2019\1\2 0002 11:32
     * @param: [ctrCode, content, opUser, xh]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_INFOMANAGE_REVISIT\n" +
            "VALUES(sys_guid(),#{ctrCode,jdbcType=VARCHAR},#{content,jdbcType=VARCHAR},#{opUser,jdbcType=VARCHAR},SYSDATE,#{xh,jdbcType=VARCHAR})" +
            "</script>")
    void addRevisit(@Param("ctrCode") String ctrCode,@Param("content") String content,@Param("opUser") String opUser,@Param("xh") int xh)throws SQLException;
}
