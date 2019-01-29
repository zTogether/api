package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyVoteMain;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.Map;

public interface XyVoteMainMapper extends Mapper<XyVoteMain> {

    /**
     * 获取投票主表信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/27 17:01
     * @param: [xyVoteMain]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tVOTE_ID,\n" +
            "\tVOTE_NAME,\n" +
            "\tVOTE_NUM,\n" +
            "\tOP_USER,\n" +
            "\tTO_CHAR(OP_DATE,'yyyy-MM-dd HH24:mi:ss') OP_DATE,\n" +
            "\tVOTE_TYPE,\n" +
            "\tVOTE_SCORE\n" +
            "FROM \n" +
            "\tXY_VOTE_MAIN\n" +
            "WHERE \n" +
            "\tVOTE_ID = #{voteId,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String ,Object> getVoteInfo(XyVoteMain xyVoteMain) throws SQLException;
}
