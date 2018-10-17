package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyPgYs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyPgYsMapper extends Mapper<XyPgYs> {

    /**
     * 根据ctrCode获取派工验收表里的信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/24 15:26
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txpy.YS_ID, \n" +
            "\txpy.CTR_CODE, \n" +
            "\txpy.YS_GZ, \n" +
            "\tTO_CHAR(xpy.OP_DATE,'yyyy-MM-dd') OP_DATE, \n" +
            "\txpy.OP_USERID, \n" +
            "\txpy.YS_STATU, \n" +
            "\txpy.ZXY_MARK, \n" +
            "\txpy.CUST_MARK, \n" +
            "\tTO_CHAR(xpy.YS_DATE,'yyyy-MM-dd') YS_DATE,\n" +
            "\t(SELECT xv.VAL_NAME FROM XY_VAL xv WHERE xv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C' AND xv.VAL_ID = xpy.YS_GZ) YS_GZ_NAME,\n" +
            "\t(SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID = xpy.OP_USERID) OP_USER_NAME\n" +
            "FROM\n" +
            "\tXY_PG_YS xpy\n" +
            "WHERE\n" +
            "\txpy.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} " +
            "ORDER BY xpy.YS_STATU" +
            "</script>")
    public List<Map<String ,Object>> getXyPgYsListByCtrCode(String ctrCode) throws SQLException;

    /**
     *
     * @Description: 添加验收(带验收时间)
     * @author: GeWeiliang
     * @date: 2018\9\28 0028 9:39
     * @param: [ctrCode, ysGz, opDate, opUserid, ysStatu, zxyMark, custMark, ysDate]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_PG_YS(" +
                "YS_ID," +
                "CTR_CODE," +
                "YS_GZ," +
                "OP_DATE," +
                "OP_USERID," +
                "YS_STATU," +
                "ZXY_MARK," +
                "CUST_MARK," +
                "YS_DATE) " +
            "VALUES(" +
                "SYS_GUID()," +
                "#{ctrCode,jdbcType=VARCHAR}," +
                "#{ysGz,jdbcType=VARCHAR}," +
                "SYSDATE," +
                "#{opUserid,jdbcType=VARCHAR}," +
                "#{ysStatu,jdbcType=VARCHAR}," +
                "#{zxyMark,jdbcType=VARCHAR}," +
                "#{custMark,jdbcType=VARCHAR}," +
                "TO_DATE(#{ysDate,jdbcType=VARCHAR}, 'yyyy-MM-dd HH24:mi:ss'))" +
            "</script>")
    public void addYanshou(@Param("ctrCode") String ctrCode,@Param("ysGz")String ysGz,
                           @Param("opUserid")String opUserid,@Param("ysStatu")String ysStatu,@Param("zxyMark")String zxyMark,
                           @Param("custMark")String custMark, @Param("ysdate")String ysDate) throws SQLException;

    /**
     *
     * @Description: 添加验收(不带验收时间)
     * @author: GeWeiliang
     * @date: 2018\9\28 0028 9:39
     * @param: [ctrCode, ysGz, opDate, opUserid, ysStatu, zxyMark, custMark, ysDate]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_PG_YS(" +
                "YS_ID," +
                "CTR_CODE," +
                "YS_GZ," +
                "OP_DATE," +
                "OP_USERID," +
                "YS_STATU," +
                "ZXY_MARK," +
                "CUST_MARK) " +
            "VALUES(" +
                "SYS_GUID()," +
                "#{ctrCode,jdbcType=VARCHAR}," +
                "#{ysGz,jdbcType=VARCHAR}," +
                "SYSDATE," +
                "#{opUserid,jdbcType=VARCHAR}," +
                "#{ysStatu,jdbcType=VARCHAR}," +
                "#{zxyMark,jdbcType=VARCHAR}," +
                "#{custMark,jdbcType=VARCHAR})" +
            "</script>")
    public void addYanshouB(@Param("ctrCode") String ctrCode,@Param("ysGz")String ysGz,
                           @Param("opUserid")String opUserid,@Param("ysStatu")String ysStatu,@Param("zxyMark")String zxyMark,
                           @Param("custMark")String custMark) throws SQLException;

    /**
     * 客户执行验收(同意)
     * @Description:
     * @author: GeWeiliang
     * @date: 2018\9\28 0028 9:39
     * @param: [ysId, custMark, ysDate]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_PG_YS SET YS_STATU = '1',CUST_MARK=#{custMark,jdbcType=VARCHAR},YS_DATE=TO_DATE(#{sysDate,jdbcType=VARCHAR},'yyyy-MM-dd HH24:mi:ss') " +
            "WHERE CTR_CODE=#{ctrCode,jdbcType=VARCHAR} " +
            "AND YS_GZ = #{ysGz,jdbcType=VARCHAR}" +
            "</script>")
    public void updateYanshou(@Param("custMark") String custMark, @Param("ctrCode") String ctrCode, @Param("ysGz") String ysGz,@Param("sysDate") String sysDate) throws SQLException;

    /**
     * 客户执行验收(不同意)
     * @Description:
     * @author: GeWeiliang
     * @date: 2018\9\28 0028 9:39
     * @param: [ysId, custMark, ysDate]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_PG_YS xps SET YS_STATU=#{ysStatu,jdbcType=VARCHAR},CUST_MARK = #{custMark,jdbcType=VARCHAR} " +
            "WHERE CTR_CODE = #{ctrCode,jdbcType=VARCHAR} " +
            "AND YS_GZ = #{ysGz,jdbcType=VARCHAR}" +
            "</script>")
    public void updateYanShouYsStatuAndCustMark(@Param("custMark") String custMark, @Param("ctrCode") String ctrCode, @Param("ysGz") String ysGz,@Param("ysStatu") String ysStatu) throws SQLException;

    /**
     * 根据ctrCode，ysId获取是否已提交验收
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/28 14:16
     * @param: [ctrCode, ysId]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tCOUNT(1) \n" +
            "FROM \n" +
            "\tXY_PG_YS xpy \n" +
            "WHERE \n" +
            "\txpy.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}\n" +
            "AND\n" +
            "\txpy.YS_GZ = #{ysGz,jdbcType=VARCHAR} " +
            "AND xpy.YS_STATU <![CDATA[<>]]> '2'" +
            "</script>")
    public Integer getCountByCtrCodeAndYszt(@Param("ctrCode") String ctrCode, @Param("ysGz") String ysGz) throws SQLException;

    /**
     * 客户验收时获取验收状态
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/15 16:49
     * @param: [ctrCode, ysGz]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT xpy.ys_statu FROM XY_PG_YS xpy WHERE xpy.ctr_code = #{ctrCode,jdbcType=VARCHAR} AND xpy.ys_gz = #{ysGz,jdbcType=VARCHAR}" +
            "</script>")
    public String gteYsStatu(@Param("ctrCode") String ctrCode ,@Param("ysGz") String ysGz) throws SQLException;
}
