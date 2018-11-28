package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyUserGps;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;

public interface XyUserGpsMapper extends Mapper<XyUserGps> {

    /**
     * 根据userId判断定位数据是否存在
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/28 10:33
     * @param: [userId]
     * @return: int
     */
    @Select("<script>" +
            "SELECT COUNT(1) FROM XY_USER_GPS A WHERE A.USER_ID = #{userId,jdbcType=VARCHAR}" +
            "</script>")
    public int isExistData(String userId) throws SQLException;

    /**
     * 添加定位消息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/28 10:36
     * @param: [xyUserGps]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_USER_GPS(\n" +
            "\tUSER_ID, \n" +
            "\tCOORDINATE_TYPE, \n" +
            "\tLATITUDE, \n" +
            "\tLONGITUDE, \n" +
            "\tCOORDINATE_UPDATE_DATE\n" +
            ") VALUES (\n" +
            "\t#{userId,jdbcType=VARCHAR},\n" +
            "\t#{coordinateType,jdbcType=VARCHAR},\n" +
            "\t#{latitude,jdbcType=VARCHAR},\n" +
            "\t#{longitude,jdbcType=VARCHAR},\n" +
            "\tSYSDATE\n" +
            ")" +
            "</script>")
    public void addUserGps(XyUserGps xyUserGps) throws SQLException;

    /**
     * 根据userId修改定位信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/28 10:39
     * @param: [xyUserGps]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_USER_GPS SET\n" +
            "\tCOORDINATE_TYPE = #{coordinateType,jdbcType=VARCHAR}, \n" +
            "\tLATITUDE = #{latitude,jdbcType=VARCHAR}, \n" +
            "\tLONGITUDE = #{longitude,jdbcType=VARCHAR}, \n" +
            "\tCOORDINATE_UPDATE_DATE = SYSDATE\n" +
            "WHERE USER_ID = #{userId,jdbcType=VARCHAR}" +
            "</script>")
    public void updateUserGpsByUserId(XyUserGps xyUserGps) throws SQLException;
}
