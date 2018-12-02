package cn.xyzs.api.mapper;


import cn.xyzs.common.pojo.*;
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
    @SelectProvider(type = getPrjPlan.class,method = "getPrjPlan")
    List<Map<String,Object>> getGcbPrjPlan(@Param("ctrCode") String ctrCode,@Param("roleName") String roleName) throws SQLException;
    class getPrjPlan{
        public String getPrjPlan(@Param("ctrCode") String ctrCode,@Param("roleName") String roleName){
            return new SQL(){{
                SELECT("p.*,u.USER_NAME");
                FROM("XY_GCB_PRJ_PLAN p");
                LEFT_OUTER_JOIN("XY_USER u ON u.USER_ID=p.EDIT_USER");
                WHERE(" p.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}");
                if(roleName!=null&&roleName!=""){
                    WHERE("p.ROLE_NAME=#{roleName}");
                }
                ORDER_BY("p.DAYS,p.XH");
            }}.toString();
        }
    }

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
    void toEnsure(@Param("editDate") Date editDate, @Param("rowId") String rowId,@Param("editUser") String userId) throws SQLException;

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
    void isDaiGou(@Param("editDate") Date editDate,@Param("rowId") String prjId,
                         @Param("content") String content,@Param("editUser") String userId) throws SQLException;

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
    void addPrjMark(@Param("prjId") String prjId,@Param("prjMark") String prjMark) throws SQLException;

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
            "SELECT\n" +
            "  p.ROW_ID,\n" +
            "  l.ZCPB_ROWID,\n" +
            "  l.ZCPB_MX,\n" +
            "  l.ZCPB_QTY,\n" +
            "  l.ZCPB_PRICE,\n" +
            "  l.ZCPB_PP \n" +
            "FROM\n" +
            "  XY_CLB_ZCPB_LIST l,\n" +
            "  XY_GCB_PRJ_PLAN p,\n" +
            "  XY_GCB_PRJ_LCD_LIST t \n" +
            "WHERE\n" +
            "  p.PLAN_LCDID = t.PLAN_LCDID \n" +
            "  AND t.ZCPB_ID = l.ZCPB_ID \n" +
            "  AND p.ROW_ID = #{rowId,jdbcType=VARCHAR}\n" +
            "  AND l.ZCPB_ZC_CODE IS NOT NULL\n" +
            "  and p.ctr_code=l.ctr_code" +
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
    void addLcd(@Param("prjId") String prjId,@Param("zcpbId") String zcpbId,@Param("quantity") String quantity,
                      @Param("ctrCode") String ctrCode,@Param("mark") String lcdMark) throws SQLException;
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
     * @Description: 获取生成的量尺单
     * @author: GeWeiliang
     * @date: 2018\11\29 0029 9:13
     * @param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT A.ROW_ID,B.CTR_CODE,C.ZC_SUP,D.EDIT_USER,SUM(A.QUANTITY*B.ZCPB_PRICE)JE,A.MARK,(CASE WHEN B.ZCPB_DC LIKE '1%' THEN 0 ELSE 1 END)ZCPB_DC\n" +
            " FROM XY_GCB_PRJ_LCD A \n" +
            " LEFT JOIN XY_CLB_ZCPB_LIST B ON A.ZCPB_ID=B.ZCPB_ROWID \n" +
            " LEFT JOIN XY_CLB_ZC_DB C ON B.ZCPB_ZC_CODE=C.ZC_CODE \n" +
            " LEFT JOIN XY_GCB_PRJ_PLAN D ON  A.PRJ_ID=D.ROW_ID \n" +
            " WHERE A.CTR_CODE=#{ctrCode} AND A.IS_ORDER=0 \n" +
            " GROUP BY A.ROW_ID,B.CTR_CODE,C.ZC_SUP,D.EDIT_USER,A.MARK,B.ZCPB_DC \n" +
            "</script>")
    List<Map<String,Object>> getLcdList(@Param("ctrCode") String ctrCode) throws SQLException;


    @Insert("<script>" +
            "INSERT INTO\n" +
            "\t\tXY_CLB_ZC_ORDER (ORDER_ID,ORDER_DATE,CTR_CODE,OP_USERID,ORDER_JE,\n" +
            "\t\tORDER_STATUS,ORDER_TYPE,ORDER_SUP,EDIT_TYPE,ORDER_DIS)\n" +
            "\t\tVALUES(sys_guid(),SYSDATE,#{ctrCode,jdbcType=VARCHAR},#{opUserid,jdbcType=VARCHAR},\n" +
            "\t\t#{orderJe,jdbcType=VARCHAR},1,#{orderType,jdbcType=VARCHAR},#{orderSup,jdbcType=VARCHAR},1,0)\n" +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "orderId", keyColumn = "ORDER_ID")
    void addOrder(XyClbZcOrder xyClbZcOrder)throws SQLException;

    @Select("<script>" +
            "SELECT B.ZCPB_ZC_CODE,C.ZC_NAME,NVL(B.ZCPB_ZC_TYPE,'-') ZC_TYPE,C.ZC_PRICE_IN,B.ZCPB_PRICE ZC_PRICE_OUT,\n" +
            "A.QUANTITY,NVL(B.ZCPB_PP,'-') ZC_BRAND,C.ZC_SUP,NVL(B.ZCPB_SPEC,'-') ZC_SPEC,NVL(C.ZC_MATERIAL,'-') ZC_MATERIAL,\n" +
            "NVL(C.ZC_COLOR,'-') ZC_COLOR,NVL(B.ZCPB_UNIT,'-') ZC_UNIT,NVL(C.ZC_CYC,0) ZC_CYC,C.ZC_AREA,NVL(B.ZCPB_VERSION,'-') ZC_VERSION," +
            "NVL(A.MARK,'-') MARK,A.ROW_ID LCDID\n" +
            "FROM XY_GCB_PRJ_LCD A\n" +
            "LEFT JOIN XY_CLB_ZCPB_LIST B ON A.ZCPB_ID=B.ZCPB_ROWID\n" +
            "LEFT JOIN XY_CLB_ZC_DB C ON B.ZCPB_ZC_CODE=C.ZC_CODE\n" +
            "WHERE A.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} AND C.ZC_SUP=#{sup,jdbcType=VARCHAR}" +
            "</script>")
    List<Map<String,Object>> getOrderList(@Param("ctrCode") String ctrCode,@Param("sup") String orderSup) throws SQLException;

    @Insert("<script>" +
            "INSERT INTO\n" +
            "\t\tXY_CLB_ZC_ORDER_LIST\n" +
            "\t\t(ORDER_ID,ROW_ID,ZC_CODE,ZC_NAME,ZC_TYPE,ZC_PRICE_IN,ZC_PRICE_OUT,\n" +
            "\t\tZC_QTY,ZC_BRAND,ZC_SUP,ZC_SPEC,ZC_MATERIAL,ZC_COLOR,ZC_UNIT,ZC_MARK,ZC_CYC,\n" +
            "\t\tZC_AREA,ZC_VERSION, ZC_SHOP_STATUS)\n" +
            "\t\tVALUES(#{orderId,jdbcType=VARCHAR},sys_guid(),#{zcCode,jdbcType=VARCHAR},#{zcName,jdbcType=VARCHAR},#{zcType,jdbcType=VARCHAR},\n" +
            "\t\t#{zcPriceIn,jdbcType=VARCHAR},#{zcPriceOut,jdbcType=VARCHAR},#{zcQty,jdbcType=VARCHAR},#{zcBrand,jdbcType=VARCHAR},#{zcSup,jdbcType=VARCHAR}," +
            "\t\t#{zcSpec,jdbcType=VARCHAR},#{zcMaterial,jdbcType=VARCHAR},#{zcColor,jdbcType=VARCHAR},\n" +
            "\t\t#{zcUnit,jdbcType=VARCHAR},#{zcMark,jdbcType=VARCHAR},#{zcCyc,jdbcType=VARCHAR},#{zcArea,jdbcType=VARCHAR},#{zcVersion,jdbcType=VARCHAR},0)" +
            "</script>")
    void addOrderList(@Param("orderId") String orderId,@Param("zcCode") String zcCode,@Param("zcName") String zcName,
                      @Param("zcType") String zcType,@Param("zcPriceIn") String zcPriceIn,@Param("zcPriceOut") String zcPriceOut,
                      @Param("zcQty") String zcQty,@Param("zcBrand") String zcBrand,@Param("zcSup") String zcSup,
                      @Param("zcSpec") String zcSpec,@Param("zcMaterial") String zcMaterial,@Param("zcColor") String zcColor,
                      @Param("zcUnit") String zcUnit,@Param("zcMark") String zcMark,@Param("zcCyc") String zcCyc,
                      @Param("zcArea") String zcArea,@Param("zcVersion") String zcVersion)throws SQLException;

    /**
     *
     * @Description: 修改量尺单状态为下单
     * @author: GeWeiliang
     * @date: 2018\11\29 0029 16:35
     * @param: [lcdId]
     * @return: void
     */
    @Update("UPDATE XY_GCB_PRJ_LCD SET IS_ORDER=1\n" +
            "WHERE ROW_ID = #{lcdId,jdbcType=VARCHAR}")
    void updateLcdOrderState(@Param("lcdId") String lcdId) throws SQLException;

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
            "WHERE  d.ZCPB_ID=l.ZCPB_ROWID AND d.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND d.PRJ_ID=#{prjId}" +
            "</script>")
    List<Map<String,Object>> showLcdByCtrCode(@Param("ctrCode") String ctrCode,@Param("prjId") String prjId) throws SQLException;

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
