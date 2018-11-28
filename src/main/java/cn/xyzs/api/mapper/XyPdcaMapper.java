package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyPdca;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;;import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyPdcaMapper extends Mapper<XyPdca> {

    /**
     * 根据userId获取所有的报表
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/10 9:52
     * @param: [userId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txp.PDCA_ID,\n" +
            "\txp.USER_ID, \n" +
            "\tNVL(xp.POSITION, ' ') POSITION, \n" +
            "\tTO_CHAR(xp.PDCA_DATE,'yyyy-MM-dd') PDCA_DATE, \n" +
            "\txp.PRESOURCE, \n" +
            "\txp.OPINION, \n" +
            "\txp.STATU, \n" +
            "\tNVL(xp.RES, ' ') RES,\n" +
            "\txp.ISSUE,\n" +
            "\txu.USER_NAME\n" +
            "FROM\n" +
            "\tXY_PDCA xp,\n" +
            "\tXY_USER xu\n" +
            "WHERE\n" +
            "\txp.USER_ID = xu.USER_ID\n" +
            "AND\n" +
            "\txp.USER_ID = #{userId,jdbcType=VARCHAR}" +
            "AND \n" +
            "\txp.PDCA_DATE\n" +
            "BETWEEN TO_DATE(#{beginDate,jdbcType=VARCHAR}, 'yyyy-MM-dd HH24:mi:ss')\n" +
            "AND TO_DATE(#{endDate,jdbcType=VARCHAR}, 'yyyy-MM-dd HH24:mi:ss')" +
            "</script>")
    public List<Map<String ,Object>> getPdcaByUserId(@Param("userId") String userId ,@Param("beginDate") String beginDate ,@Param("endDate") String endDate) throws SQLException;

    /**
     * 获取下级pdca
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/10 17:12
     * @param: [userId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txp.PDCA_ID,\n" +
            "\txp.USER_ID, \n" +
            "\tNVL(xp.POSITION, ' ') POSITION, \n" +
            "\tTO_CHAR(xp.PDCA_DATE,'yyyy-MM-dd') PDCA_DATE, \n" +
            "\txp.PRESOURCE, \n" +
            "\txp.OPINION, \n" +
            "\txp.STATU, \n" +
            "\tNVL(xp.RES, ' ') RES,\n" +
            "\txp.ISSUE,\n" +
            "\txu.USER_NAME\n" +
            "FROM\n" +
            "\tXY_PDCA xp,\n" +
            "\tXY_USER xu\n" +
            "WHERE\n" +
            "\txp.USER_ID = xu.USER_ID\n" +
            "AND\n" +
            "\txp.USER_ID IN (\n" +
            "\t\tSELECT\n" +
            "\t\t\txur.FOLLOWER_ID \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_USER_RELATION_PDCA xur \n" +
            "\t\tWHERE \n" +
            "\t\t\txur.LEADER_ID <![CDATA[!=]]> #{userId,jdbcType=VARCHAR}\n" +
            "\t\tSTART WITH xur.LEADER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\tCONNECT BY xur.LEADER_ID = PRIOR xur.FOLLOWER_ID\t\t\t\n" +
            "\t)" +
            "</script>")
    public List<Map<String ,Object>> getSubordinatePdca(String userId) throws SQLException;



}
