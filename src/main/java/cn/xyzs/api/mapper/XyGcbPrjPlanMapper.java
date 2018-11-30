package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyGcbPrjPlan;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface XyGcbPrjPlanMapper extends Mapper<XyGcbPrjPlan> {
    /**
     *
     * @Description: 根据档案号查询工地日程
     * @author: GeWeiliang
     * @date: 2018\11\10 0010 9:06
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT p.*,u.USER_NAME FROM XY_GCB_PRJ_PLAN p\n" +
            "LEFT JOIN XY_USER u ON u.USER_ID=p.EDIT_USER \n" +
            "WHERE p.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} ORDER BY p.DAYS,p.XH" +
            "</script>")
    List<Map<String,Object>> getGcbPrjPlan(String ctrCode) throws SQLException;

    /**
     *
     * @Description: 确认日程完成
     * @author: GeWeiliang
     * @date: 2018\11\10 0010 9:29
     * @param: [editDate]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_GCB_PRJ_PLAN SET EDIT_STATU=1,EDIT_DATE=#{editDate},EDIT_USER=#{editUser} WHERE ROW_ID=#{rowId}" +
            "</script>")
    void toEnsure(@Param("editDate") Date editDate, @Param("rowId") String rowId, @Param("editUser") String userId) throws SQLException;

    /**
     *
     * @Description: 是否代购
     * @author: GeWeiliang
     * @date: 2018\11\11 0011 17:12
     * @param: [editDate, rowId, content]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_GCB_PRJ_PLAN SET EDIT_STATU=1,EDIT_MARK=#{content,jdbcType=VARCHAR}," +
            "EDIT_DATE=#{editDate,jdbcType=VARCHAR},EDIT_USER=#{editUser,jdbcType=VARCHAR}\n" +
            " WHERE ROW_ID=#{rowId,jdbcType=VARCHAR}" +
            "</script>")
    void isDaiGou(@Param("editDate") Date editDate, @Param("rowId") String prjId,
                  @Param("content") String content, @Param("editUser") String userId) throws SQLException;

    /**
     *
     * @Description: 日程添加备注
     * @author: GeWeiliang
     * @date: 2018\11\10 0010 9:49
     * @param: [rowId, mark]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_GCB_PRJ_PLAN SET EDIT_MARK=#{prjMark} WHERE ROW_ID=#{prjId}" +
            "</script>")
    void addPrjMark(@Param("prjId") String prjId, @Param("prjMark") String prjMark) throws SQLException;

    @Select("<script>" +
            "SELECT ROW_ID,DAYS,PLAN_TYPE,EDIT_STATU FROM XY_GCB_PRJ_PLAN WHERE ROW_ID = #{rowId}" +
            "</script>")
    Map<String,Object> getOnePlan(String rowId) throws SQLException;


    /**
     *
     * @Description: 获取量尺单
     * @author: GeWeiliang
     * @date: 2018\11\12 0012 15:16
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT p.ROW_ID,l.ZCPB_ROWID,l.ZCPB_MX,l.ZCPB_QTY,l.ZCPB_PRICE,l.ZCPB_PP \n" +
            "FROM XY_CLB_ZCPB_LIST l,XY_GCB_PRJ_PLAN p,XY_GCB_PRJ_LCD_LIST t \n" +
            "WHERE p.PLAN_LCDID=t.PLAN_LCDID AND t.ZCPB_ID=l.ZCPB_ROWID AND p.ROW_ID=#{rowId} " +
            "</script>")
    List<Map<String,Object>> getLcd(String rowId) throws SQLException;


    /**
     *
     * @Description: 添加量尺单
     * @author: GeWeiliang
     * @date: 2018\11\12 0012 17:32
     * @param: [prjId, mbId, quantity, length, width, height]
     * @return: void
     */
    @InsertProvider(type = addLcd.class,method = "addLcd")
    void addLcd(@Param("prjId") String prjId, @Param("zcpbId") String zcpbId, @Param("quantity") String quantity,
                @Param("ctrCode") String ctrCode, @Param("mark") String lcdMark) throws SQLException;
    class addLcd{
        public String addLcd(@Param("prjId") String prjId,@Param("zcpbId") String zcpbId,@Param("quantity") String quantity,
                             @Param("ctrCode") String ctrCode,@Param("mark") String lcdMark){
            return new SQL(){{
                INSERT_INTO("XY_GCB_PRJ_LCD");
                VALUES("ZCPB_ID","#{zcpbId,jdbcType=VARCHAR}");
                VALUES("PRJ_ID","#{prjId,jdbcType=VARCHAR}");
                VALUES("CTR_CODE","#{ctrCode,jdbcType=VARCHAR}");
                if(quantity!=null&&quantity!=""){
                    VALUES("QUANTITY","#{quantity}");
                }
                if(lcdMark!=null&&lcdMark!=""){
                    VALUES("MARK","#{mark}");
                }
            }}.toString();
        }
    }

    /**
     *
     * @Description: 根据客户号查看量尺单
     * @author: GeWeiliang
     * @date: 2018\11\14 0014 16:28
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT d.*,l.ZCPB_MX,l.ZCPB_QTY,l.ZCPB_PRICE,l.ZCPB_PP FROM XY_CLB_ZCPB_LIST l,XY_GCB_PRJ_LCD d \n" +
            "WHERE  d.ZCPB_ID=l.ZCPB_ROWID AND d.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    List<Map<String,Object>> showLcdByCtrCode(String ctrCode) throws SQLException;

    /**
     *
     * @Description: 根据员工号查看自己工地的日程
     * @author: GeWeiliang
     * @date: 2018\11\16 0016 9:47
     * @param: [userId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT p.*,i.CTR_ADDR FROM XY_GCB_PRJ_PLAN p \n" +
            "LEFT JOIN XY_CUSTOMER_INFO i ON p.CTR_CODE=i.CTR_CODE\n" +
            "WHERE i.CTR_GCJL=#{userId} OR i.CTR_CLDD=#{userId}  ORDER BY p.DAYS" +
            "</script>")
    List<Map<String,Object>> showMyPlan(String userId) throws SQLException;

    /**
     *
     * @Description: 获取关联工程的rowid
     * @author: GeWeiliang
     * @date: 2018\11\17 0017 14:40
     * @param: [prjId]
     * @return: java.util.List<java.lang.String>
     */
    @Select("<script>" +
            "SELECT CON_ROWID FROM XY_GCB_PRJ_PLAN WHERE ROW_ID=#{prjId,jdbcType=VARCHAR}" +
            "</script>")
    String getConPrj(@Param("prjId") String prjId) throws SQLException;

    @Select("<script>" +
            "SELECT * FROM XY_GCB_PRJ_PLAN WHERE ROW_ID=#{rowId,jdbcType=VARCHAR}" +
            "</script>")
    Map<String,Object> getOnePrj(@Param("rowId") String rowId) throws SQLException;

    /**
     * 生成工程计划
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/23 16:40
     * @param: [ctrCode, pgBeginDate]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_GCB_PRJ_PLAN A (\n" +
            "\tSELECT\n" +
            "\t\t#{ctrCode,jdbcType=VARCHAR} CTR_CODE,\n" +
            "\t\tSYS_GUID () ROW_ID,\n" +
            "\t\tADD_MONTHS( to_date( #{pgBeginDate,jdbcType=VARCHAR}, 'YYYY-MM-DD' ), 0 ) + DAYS DAYS,\n" +
            "\t\tB.XH,\n" +
            "\t\tB.PLAN_CONTENT,\n" +
            "\t\tB.ROLE_NAME,\n" +
            "\t\tB.PLAN_TYPE,\n" +
            "\t\tB.PLAN_LCDID,\n" +
            "\t\tB.CON_ROWID,\n" +
            "\t\tNULL AS EDIT_DATE,\n" +
            "\t\t'-' EDIT_MARK,\n" +
            "\t\t0 EDIT_STATU,\n" +
            "\t\tNULL AS EDIT_USER,\n" +
            "\t\tB.ROW_ID MB_ROWID \n" +
            "\tFROM\n" +
            "\t\tXY_GCB_PRJ_PLAN_MB B \n" +
            "\tWHERE\n" +
            "\t\tB.DAYS &lt;&gt; 0 \n" +
            ")" +
            "</script>")
    public void createEngineeringPlan(@Param("ctrCode") String ctrCode, @Param("pgBeginDate") String pgBeginDate) throws SQLException;
}
