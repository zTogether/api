package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyEffectiveInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyEffectiveInfoMapper extends Mapper<XyEffectiveInfo> {

    /**
     * 添加有效信息记录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/3 11:22
     * @param: [xyEffectiveInfo]
     * @return:
     */
    @Insert("<script>" +
            "INSERT INTO XY_EFFEDTIVE_INFO ( \n" +
            "\tSUBMIT_USER_ID, \n" +
            "\tEFFECTIVE_INFO_NAME, \n" +
            "\tEFFECTIVE_INFO_TEL, \n" +
            "\tEFFECTIVE_INFO_ADDRESS,\n" +
            "\tREMARK )\n" +
            "VALUES\n" +
            "\t(\n" +
            "\t#{submitUserId,jdbcType=VARCHAR},\n" +
            "\t#{effectiveInfoName,jdbcType=VARCHAR},\n" +
            "\t#{effectiveInfoTel,jdbcType=VARCHAR},\n" +
            "\t#{effectiveInfoAddress,jdbcType=VARCHAR},\n" +
            "\t#{remark,jdbcType=VARCHAR})" +
            "</script>")
    public void addEffectiveInfo(XyEffectiveInfo xyEffectiveInfo) throws SQLException;

    /**
     * 根据有效信息id修改有效信息部分信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/3 11:26
     * @param: [xyEffectiveInfo]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_EFFEDTIVE_INFO \n" +
            "SET \n" +
            "\tEFFECTIVE_INFO_NAME = #{effectiveInfoName,jdbcType=VARCHAR},\n" +
            "\tEFFECTIVE_INFO_TEL = #{effectiveInfoTel,jdbcType=VARCHAR},\n" +
            "\tEFFECTIVE_INFO_ADDRESS = #{effectiveInfoAddress,jdbcType=VARCHAR},\n" +
            "\tREMARK = #{remark,jdbcType=VARCHAR}\n" +
            "WHERE EFFECTIVE_INFO_ID = #{effectiveInfoId,jdbcType=VARCHAR}" +
            "</script>")
    public void updateEffectiveInfo(XyEffectiveInfo xyEffectiveInfo) throws SQLException;

    /**
     * 根据有效信息id删除有效信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/3 13:12
     * @param: [effectiveInfoId]
     * @return: void
     */
    @Delete("<script>" +
            "DELETE FROM XY_EFFEDTIVE_INFO WHERE EFFECTIVE_INFO_ID = #{effectiveInfoId,jdbcType=VARCHAR}" +
            "</script>")
    public void deleteEffectiveInfoByEffectiveInfoId(String effectiveInfoId) throws SQLException;

    /**
     * 根据提交人id获取
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/3 11:28
     * @param: [submitUserId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tA.EFFECTIVE_INFO_ID, \n" +
            "\tA.SUBMIT_USER_ID, \n" +
            "\tA.EFFECTIVE_INFO_NAME, \n" +
            "\tA.EFFECTIVE_INFO_TEL, \n" +
            "\tA.EFFECTIVE_INFO_ADDRESS, \n" +
            "\tTO_CHAR(A.SUBMIT_DATE,'yyyy-MM-dd HH24:mi:ss') SUBMIT_DATE, \n" +
            "\tA.EFFECTIVE_INFO_STATUS, \n" +
            "\tA.REMARK\n" +
            "FROM \n" +
            "\tXY_EFFEDTIVE_INFO A\n" +
            "WHERE\n" +
            "\tA.SUBMIT_USER_ID = #{submitUserId,jdbcType=VARCHAR}" +
            "\tORDER BY A.SUBMIT_DATE DESC" +
            "</script>")
    public List<Map<String ,Object>> getEffectiveInfoBySubmitUserId(String submitUserId) throws SQLException;

}
