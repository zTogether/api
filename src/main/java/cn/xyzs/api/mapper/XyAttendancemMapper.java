package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyAttendance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyAttendancemMapper extends Mapper<XyAttendance> {

    /**
     * 添加考勤记录
     * (userId：用户id，deviceNumber：设备号，distance：距离，longitude：经度，latitude：纬度，subordinateUnit：隶属单位，
     *  attendanceAddress：考勤地址，attendanceType：考勤类型，attendanceImg：考勤图片)
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/14 14:33
     * @param: [xyAttendance]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_ATTENDANCE(\n" +
            "\tUSER_ID,\n" +
            "\tDEVICE_NUMBER,\n" +
            "\tDISTANCE,\n" +
            "\tLONGITUDE,\n" +
            "\tLATITUDE,\n" +
            "\tSUBORDINATE_UNIT,\n" +
            "\tATTENDANCE_ADDRESS,\n" +
            "\tATTENDANCE_TYPE,\n" +
            "\tATTENDANCE_IMG\n" +
            ") VALUES ( \n" +
            "\t#{userId,jdbcType=VARCHAR},\n" +
            "\t#{deviceNumber,jdbcType=VARCHAR},\n" +
            "\t#{distance,jdbcType=VARCHAR},\n" +
            "\t#{longitude,jdbcType=VARCHAR},\n" +
            "\t#{latitude,jdbcType=VARCHAR},\n" +
            "\t#{subordinateUnit,jdbcType=VARCHAR},\n" +
            "\t#{attendanceAddress,jdbcType=VARCHAR},\n" +
            "\t#{attendanceType,jdbcType=VARCHAR},\n" +
            "\t#{attendanceImg,jdbcType=VARCHAR}\n" +
            ")" +
            "</script>")
    public void addAttendanceRecord(XyAttendance xyAttendance) throws SQLException;

    /**
     * 根据当前选择的打卡旗舰店获取打卡距离
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/14 15:23
     * @param: [shopPositionInfoId, currentPositionLa, currentPositionLo]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tGETDISTANCE(SHOP_LATITUDE,SHOP_LONGITUDE," +
            "TO_NUMBER(#{latitude,jdbcType=VARCHAR})," +
            "TO_NUMBER(#{longitude,jdbcType=VARCHAR})) * 1000\n" +
            "FROM \n" +
            "\tXY_SHOP_POSITION_INFO \n" +
            "WHERE \n" +
            "\tSHOP_POSITION_INFO_ID = #{subordinateUnit,jdbcType=VARCHAR}" +
            "</script>")
    public String getAttendanceDistance(XyAttendance xyAttendance) throws SQLException;

    /**
     * 获取今天的考勤记录
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/14 16:51
     * @param: [userId]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT COUNT(1) FROM XY_ATTENDANCE \n" +
            "WHERE TO_CHAR(ATTENDANCE_DATE,'yyyy-MM-dd') = TO_CHAR(SYSDATE,'yyyy-MM-dd') \n" +
            "AND USER_ID = #{userId,jdbcType=VARCHAR}" +
            "</script>")
    public Integer getTodayAttendanceCount(XyAttendance xyAttendance) throws SQLException;

    /**
     * 根据userId获取今日的考勤记录
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/14 17:27
     * @param: [xyAttendance]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tATTENDANCE_ID, \n" +
            "\tUSER_ID, \n" +
            "\tTO_CHAR(ATTENDANCE_DATE,'MM-dd HH24:mi') ATTENDANCE_DATE, \n" +
            "\tTO_CHAR(ATTENDANCE_DATE,'d') ATTENDANCE_DATE_WEEk, \n" +
            "\tDEVICE_NUMBER, \n" +
            "\tDISTANCE, \n" +
            "\tLONGITUDE, \n" +
            "\tLATITUDE, \n" +
            "\tSUBORDINATE_UNIT, \n" +
            "\tATTENDANCE_ADDRESS, \n" +
            "\tATTENDANCE_TYPE, \n" +
            "\tATTENDANCE_IMG \n" +
            "FROM \n" +
            "\tXY_ATTENDANCE \n" +
            "WHERE \n" +
            "\tTO_CHAR(ATTENDANCE_DATE,'yyyy-MM-dd') = TO_CHAR(SYSDATE,'yyyy-MM-dd') \n" +
            "AND \n" +
            "\tUSER_ID = #{userId,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getTodyAttendanceRecordByUserId(XyAttendance xyAttendance) throws SQLException;
}
