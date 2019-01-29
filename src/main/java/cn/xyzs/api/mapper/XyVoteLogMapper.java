package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyVoteLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyVoteLogMapper extends Mapper<XyVoteLog> {

    /**
     * 添加投票记录
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/27 16:49
     * @param: [xyVoteLog]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_VOTE_LOG (\n" +
            "\tVOTE_ID,\n" +
            "\tSELECT_ID,\n" +
            "\tOP_USER,\n" +
            "\tVOTE_SCORE\n" +
            ") VALUES (\n" +
            "\t#{voteId,jdbcType=VARCHAR},\n" +
            "\t#{selectId,jdbcType=VARCHAR},\n" +
            "\t#{opUser,jdbcType=VARCHAR},\n" +
            "\t#{voteScore,jdbcType=VARCHAR}\n" +
            ")" +
            "</script>")
    public void addVoteLog(XyVoteLog xyVoteLog) throws SQLException;

    /**
     * 获取今日当前项目以投票的记录
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/27 16:54
     * @param: [xyVoteLog]
     * @return: int
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tVOTE_ID,\n" +
            "\tTO_CHAR(OP_DATE,'yyyy-MM-dd HH24:mi:ss') OP_DATE,\n" +
            "\tSELECT_ID,\n" +
            "\tOP_USER,\n" +
            "\tVOTE_SCORE\n" +
            "FROM\n" +
            "\tXY_VOTE_LOG\n" +
            "WHERE\n" +
            "\tVOTE_ID = #{voteId,jdbcType=VARCHAR}\n" +
            "AND \n" +
            "\tTO_CHAR(OP_DATE,'yyyy-MM-dd') = TO_CHAR(SYSDATE,'yyyy-MM-dd')\n" +
            "AND\n" +
            "\tOP_USER = #{opUser,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getTodayVoteLog(XyVoteLog xyVoteLog) throws SQLException;

    /**
     * 获取今日当前项目当前被投票人的以投票的记录数
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/28 9:26
     * @param: [xyVoteLog]
     * @return: int
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCOUNT(1)\n" +
            "FROM\n" +
            "\tXY_VOTE_LOG\n" +
            "WHERE\n" +
            "\tVOTE_ID = #{voteId,jdbcType=VARCHAR}\n" +
            "AND \n" +
            "\tTO_CHAR(OP_DATE,'yyyy-MM-dd') = TO_CHAR(SYSDATE,'yyyy-MM-dd')\n" +
            "AND\n" +
            "\tOP_USER = #{opUser,jdbcType=VARCHAR}\n" +
            "AND\n" +
            "\tSELECT_ID = #{selectId,jdbcType=VARCHAR}" +
            "</script>")
    public int getTodayVoteLogCount(XyVoteLog xyVoteLog) throws SQLException;

    /**
     * 获取当前项目的当前被投票人投票总记录数
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/28 11:03
     * @param: []
     * @return: int
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tCOUNT(1) \n" +
            "FROM \n" +
            "\tXY_VOTE_LOG \n" +
            "WHERE \n" +
            "\tVOTE_ID = #{voteId,jdbcType=VARCHAR} \n" +
            "AND \n" +
            "\tSELECT_ID = #{selectId,jdbcType=VARCHAR}" +
            "</script>")
    public int getVoteLogCount(XyVoteLog xyVoteLog) throws SQLException;
}
