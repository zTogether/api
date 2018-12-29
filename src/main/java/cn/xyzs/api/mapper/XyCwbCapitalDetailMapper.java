package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyCwbCapitalDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyCwbCapitalDetailMapper extends Mapper<XyCwbCapitalDetail> {

    /**
     * 添加资金明细
     * capitalId：关联资金表主键id ； capitalType：资金类型（0：转入，1：转出）
     * menoy：金额；  capitalNetailDescription：描述；  opDeviceNum：操作人设备号
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/23 13:25
     * @param: [xyCwbCapitalDetail]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_CWB_CAPITAL_DETAIL(\n" +
            "\tCAPITAL_ID,\n" +
            "\tCAPITAL_TYPE,\n" +
            "\tMONEY,\n" +
            "\tCAPITAL_DETAIL_DESCRIPTION,\n" +
            "\tOP_DEVICE_NUM\n" +
            ") VALUES(\n" +
            "\t#{capitalId,jdbcType=VARCHAR},\n" +
            "\t#{capitalType,jdbcType=VARCHAR},\n" +
            "\t#{money,jdbcType=VARCHAR},\n" +
            "\t#{capitalNetailDescription,jdbcType=VARCHAR},\n" +
            "\t#{opDeviceNum,jdbcType=VARCHAR}\n" +
            ")" +
            "</script>")
    public void addCapitalDetail(XyCwbCapitalDetail xyCwbCapitalDetail) throws SQLException;

    /**
     * 获取最新五天资金明细
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/23 14:46
     * @param: [capitalId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tCAPITAL_DETAIL_ID, \n" +
            "\tCAPITAL_ID, \n" +
            "\tCAPITAL_TYPE, \n" +
            "\tMONEY, \n" +
            "\tTO_CHAR(OP_DATE,'yyyy-MM-dd') OP_DATE, \n" +
            "\tCAPITAL_DETAIL_DESCRIPTION, \n" +
            "\tOP_DEVICE_NUM \n" +
            "FROM XY_CWB_CAPITAL_DETAIL \n" +
            "<![CDATA[WHERE OP_DATE >= trunc(sysdate - 4)]]>\n" +
            "AND CAPITAL_ID = #{capitalId,jdbcType=VARCHAR}\n" +
            "ORDER BY OP_DATE DESC" +
            "</script>")
    public List<Map<String ,Object>> getRecentCapitalDetail(String capitalId) throws SQLException;

    /**
     * 获取最新的一条收入记录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/24 9:23
     * @param: [capitalId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT B.*  FROM ( SELECT A.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\t\tSELECT \n" +
            "\t\t\tCAPITAL_DETAIL_ID, \n" +
            "\t\t\tCAPITAL_ID, \n" +
            "\t\t\tCAPITAL_TYPE, \n" +
            "\t\t\tMONEY, \n" +
            "\t\t\tTO_CHAR(OP_DATE,'yyyy-MM-dd HH24:mi:ss') OP_DATE, \n" +
            "\t\t\tCAPITAL_DETAIL_DESCRIPTION, \n" +
            "\t\t\tOP_DEVICE_NUM \n" +
            "\t\tFROM XY_CWB_CAPITAL_DETAIL \n" +
            "\t\tWHERE CAPITAL_TYPE = 0\n" +
            "\t\tAND CAPITAL_ID = #{capitalId,jdbcType=VARCHAR}\n" +
            "\t\tORDER BY OP_DATE DESC\n" +
            "\t) A  \n" +
            ") B\n" +
            "WHERE RN BETWEEN 1 AND 1" +
            "</script>")
    public Map<String ,Object> getNewCapitalDetail(String capitalId) throws SQLException;

    /**
     * 根据全部类别分页查询
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/24 9:27
     * @param: [userId, startNum, endNum]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT B.*  FROM ( SELECT A.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\tSELECT\n" +
            "\t\tCAPITAL_DETAIL_ID, \n" +
            "\t\tCAPITAL_ID, \n" +
            "\t\tCAPITAL_TYPE, \n" +
            "\t\tMONEY, \n" +
            "\t\tTO_CHAR(OP_DATE,'yyyy-MM-dd HH24:mi:ss') OP_DATE,\n" +
            "\t\tCAPITAL_DETAIL_DESCRIPTION, \n" +
            "\t\tOP_DEVICE_NUM\n" +
            "\tFROM\n" +
            "\t\tXY_CWB_CAPITAL_DETAIL\n" +
            "\tWHERE \n" +
            "\t\tCAPITAL_ID = (SELECT CAPITAL_ID FROM XY_CWB_CAPITAL WHERE USER_ID = #{userId,jdbcType=VARCHAR}) \n" +
            "\tORDER BY OP_DATE DESC\n" +
            "\t) A  \n" +
            ")B\n" +
            "WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getAllCapitalDetail(@Param("userId") String userId, @Param("startNum") Integer startNum, @Param("endNum") Integer endNum) throws SQLException;

    /**
     * 根据收入或支出类别分页查询
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/24 9:31
     * @param: [capitalType, userId, startNum, endNum]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT B.*  FROM ( SELECT A.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\tSELECT\n" +
            "\t\tCAPITAL_DETAIL_ID, \n" +
            "\t\tCAPITAL_ID, \n" +
            "\t\tCAPITAL_TYPE, \n" +
            "\t\tMONEY, \n" +
            "\t\tTO_CHAR(OP_DATE,'yyyy-MM-dd HH24:mi:ss') OP_DATE,\n" +
            "\t\tCAPITAL_DETAIL_DESCRIPTION, \n" +
            "\t\tOP_DEVICE_NUM\n" +
            "\tFROM\n" +
            "\t\tXY_CWB_CAPITAL_DETAIL\n" +
            "\tWHERE \n" +
            "\t\tCAPITAL_ID = (SELECT CAPITAL_ID FROM XY_CWB_CAPITAL WHERE USER_ID = #{userId,jdbcType=VARCHAR})\n" +
            "\tAND\n" +
            "\t\tCAPITAL_TYPE = #{capitalType,jdbcType=VARCHAR}\n" +
            "\tORDER BY OP_DATE DESC\n" +
            "\t) A  \n" +
            ")B\n" +
            "WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getSrOrZcCapitalDetail(@Param("userId") String userId, @Param("capitalType")
            String capitalType, @Param("startNum") Integer startNum, @Param("endNum") Integer endNum) throws SQLException;
}
