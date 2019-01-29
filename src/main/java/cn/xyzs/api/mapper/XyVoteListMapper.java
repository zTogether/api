package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyVoteList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyVoteListMapper extends Mapper<XyVoteList> {

    /**
     * 根据voteId获取待投票人
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/27 16:27
     * @param: [voteId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT J.*  FROM ( SELECT H.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\tSELECT\n" +
            "\t\tA.VOTE_ID,\n" +
            "\t\tA.VOTE_XH,\n" +
            "\t\tA.SELECT_ID,\n" +
            "\t\tA.SELECT_NAME, \n" +
            "\t\t(SELECT COUNT(1) \n" +
            "\t\tFROM XY_VOTE_LOG \n" +
            "\t\tWHERE VOTE_ID = #{voteId,jdbcType=VARCHAR} \n" +
            "\t\tAND SELECT_ID = A.SELECT_ID ) TOTAL_VOTE" +
            "\tFROM\n" +
            "\t\tXY_VOTE_LIST A\n" +
            "\tWHERE\n" +
            "\t\tVOTE_ID = #{voteId,jdbcType=VARCHAR}\n" +
            "\t) H  \n" +
            ")J\n" +
            "WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getVoteListByVoteId(@Param("voteId") String voteId ,
                                                         @Param("startNum") Integer startNum ,
                                                         @Param("endNum") Integer endNum) throws SQLException;


}
