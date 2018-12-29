package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyCwbCapitalYytx;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyCwbCapitalYytxMapper extends Mapper<XyCwbCapitalYytx> {

    /**
     * 添加预约提现记录（userId：用户id；  appointmentMoney：预约提现金额；  remark：备注；）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/26 10:32
     * @param: [xyCwbCapitalYytx]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_CWB_CAPITAL_YYTX(\n" +
            "\tUSER_ID,\n" +
            "\tAPPOINTMENT_MONEY,\n" +
            "\tREMARK\n" +
            ")VALUES(\n" +
            "\t#{userId,jdbcType=VARCHAR},\n" +
            "\t#{appointmentMoney,jdbcType=VARCHAR},\n" +
            "\t#{remark,jdbcType=VARCHAR}\n" +
            ")" +
            "</script>")
    public void addCapitalYytx(XyCwbCapitalYytx xyCwbCapitalYytx) throws SQLException;

    /**
     * 根据userId获取预约提现记录（分页）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/26 10:54
     * @param: [userId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT B.*  FROM ( SELECT A.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\tSELECT\n" +
            "\t\tAPPOINTMENT_WITHDRAW_ID,\n" +
            "\t\tUSER_ID,\n" +
            "\t\tTO_CHAR( APPOINTMENT_DATE, 'yyyy-MM-dd HH24:mi:ss' ) APPOINTMENT_DATE,\n" +
            "\t\tAPPOINTMENT_MONEY,\n" +
            "\t\tREMARK \n" +
            "\tFROM\n" +
            "\t\tXY_CWB_CAPITAL_YYTX \n" +
            "\tWHERE\n" +
            "\t\tUSER_ID = #{userId,jdbcType=VARCHAR}\n" +
            "\t\tORDER BY APPOINTMENT_DATE DESC\n" +
            "\t) A  \n" +
            ")B\n" +
            "WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR} " +
            "</script>")
    public List<Map<String ,Object>> getYytxRecord(@Param("userId") String userId ,@Param("startNum") Integer startNum ,@Param("endNum") Integer endNum) throws SQLException;

    /**
     * 获取账户余额与今日已经预约金额
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/26 17:04
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tA.ALLOW_WITHDRAW_DEPOSIT_MONEY,\n" +
            "\tB.APPOINTMENT_MONEY\n" +
            "FROM \n" +
            "\tXY_CWB_CAPITAL A, \n" +
            "\t(\n" +
            "\t\tSELECT \n" +
            "\t\t\tSUM(APPOINTMENT_MONEY) APPOINTMENT_MONEY \n" +
            "\t\tFROM \n" +
            "\t\t\tXY_CWB_CAPITAL_YYTX \n" +
            "\t\tWHERE\n" +
            "\t\t\tUSER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\tAND\n" +
            "\t\t\tTO_DATE(TO_CHAR(APPOINTMENT_DATE, 'yyyy-MM-dd'), 'yyyy-MM-dd') = TO_DATE(TO_CHAR(SYSDATE, 'yyyy-MM-dd'), 'yyyy-MM-dd')\t\n" +
            "\t) B\n" +
            "WHERE\n" +
            "\tA.USER_ID = #{userId,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String ,Object> getAwdmoneyAndAmoney(String userId) throws SQLException;

    /**
     * 获取昨日预约提现金额与今日已经提现的金额
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/26 10:51
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tAPPOINTMENT_MONEY,\n" +
            "\tA.MONEY\n" +
            "FROM \n" +
            "\tXY_CWB_CAPITAL_YYTX,\n" +
            "\t(\n" +
            "\t\tSELECT \n" +
            "\t\t\tSUM(MONEY) MONEY\n" +
            "\t\tFROM \n" +
            "\t\t\tXY_CWB_CAPITAL_DETAIL \n" +
            "\t\tWHERE CAPITAL_ID = (SELECT CAPITAL_ID FROM XY_CWB_CAPITAL WHERE USER_ID = #{userId,jdbcType=VARCHAR}) \n" +
            "\t\tAND CAPITAL_TYPE = 1 \n" +
            "\t\tAND TO_DATE(TO_CHAR(OP_DATE, 'yyyy-MM-dd'), 'yyyy-MM-dd') = TO_DATE(TO_CHAR(SYSDATE, 'yyyy-MM-dd'), 'yyyy-MM-dd')\n" +
            "\t\t) A\n" +
            "WHERE \n" +
            "\tUSER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "AND \n" +
            "\tTO_DATE(TO_CHAR(APPOINTMENT_DATE, 'yyyy-MM-dd'), 'yyyy-MM-dd') = TO_DATE(SYSDATE - 1, 'yyyy-MM-dd')" +
            "</script>")
    public Map<String ,Object> getAmoneyAndMoney(String userId) throws SQLException;

    /**
     * 判断是否窜在预约提现记录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/26 10:35
     * @param: [userId]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tCOUNT(1) \n" +
            "FROM \n" +
            "\tXY_CWB_CAPITAL_YYTX \n" +
            "WHERE \n" +
            "\tUSER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "AND \n" +
            "\tTO_DATE(APPOINTMENT_DATE, 'yyyy-MM-dd') = TO_DATE(SYSDATE, 'yyyy-MM-dd')" +
            "</script>")
    public Integer existsYytxRecord(String userId) throws SQLException;

    /**
     * 修改预约提现金额
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/26 10:57
     * @param: [xyCwbCapitalYytx]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_CWB_CAPITAL_YYTX SET APPOINTMENT_MONEY = #{appointmentMoney,jdbcType=VARCHAR} " +
            "WHERE APPOINTMENT_WITHDRAW_ID = #{appointmentDithdrawId,jdbcType=VARCHAR}" +
            "</script>")
    public void updateAppointmentMoney(XyCwbCapitalYytx xyCwbCapitalYytx) throws SQLException;

    /**
     * 删除预约提现记录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/26 11:00
     * @param: [appointmentDithdrawId]
     * @return: void
     */
    @Delete("<script>" +
            "DELETE FROM XY_CWB_CAPITAL_YYTX WHERE APPOINTMENT_WITHDRAW_ID = #{appointmentDithdrawId,jdbcType=VARCHAR}" +
            "</script>")
    public void deleteYytxRecord(String appointmentDithdrawId) throws SQLException;
}
