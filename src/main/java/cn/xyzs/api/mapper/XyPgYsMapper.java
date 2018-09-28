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
            "\tTO_CHAR(xpy.OP_DATE,'yyyy-MM-dd HH24:mi:ss') OP_DATE, \n" +
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
            "\txpy.CTR_CODE = #{ctrCode}" +
            "</script>")
    public List<Map<String ,Object>> getXyPgYsListByCtrCode(String ctrCode) throws SQLException;

    /**
     *
     * @Description: 添加验收
     * @author: GeWeiliang
     * @date: 2018\9\28 0028 9:39
     * @param: [ctrCode, ysGz, opDate, opUserid, ysStatu, zxyMark, custMark, ysDate]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_PG_YS(YS_ID,CTR_CODE,YS_GZ,OP_DATE,OP_USERID,YS_STATU,ZXY_MARK,CUST_MARK,YS_DATE) " +
            "VALUES(SYS_GUID(),#{ctrCode},#{ysGz},TO_DATE(#{opDate}, 'yyyy-MM-dd HH24:mi:ss'),#{opUserid},#{ysStatu}," +
            "    #{zxyMark},#{custMark},TO_DATE(#{ysDate}), 'yyyy-MM-dd HH24:mi:ss')" +
            "</script>")
    public void addYanshou(@Param("ctrCode") String ctrCode,@Param("ysGz")String ysGz,@Param("opDate")String opDate,
                           @Param("opUserid")String opUserid,@Param("ysStatu")String ysStatu,@Param("zxyMark")String zxyMark,
                           @Param("custMark")String custMark, @Param("ysdate")String ysDate) throws SQLException;

    /**
     *
     * @Description: 根据YS_ID修改验收
     * @author: GeWeiliang
     * @date: 2018\9\28 0028 9:39
     * @param: [ysId, custMark, ysDate]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_PG_YS SET CUST_MARK=#{custMark},YS_DATE=TO_DATE(#{ysDate}, 'yyyy-MM-dd HH24:mi:ss')" +
            "WHERE YS_ID=#{ysId}" +
            "</script>")
    public void updateYanshou(@Param("ysId") String ysId,@Param("custMark") String custMark,
                              @Param("ysDate") String ysDate) throws SQLException;
}
