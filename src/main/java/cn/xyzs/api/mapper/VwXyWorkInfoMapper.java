package cn.xyzs.api.mapper;

import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface VwXyWorkInfoMapper {

    /**
     * 获取信息处理流程
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/12 15:34
     * @param: [custId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tBTIME,\n" +
            "\tUSERID,\n" +
            "\tUSER_NAME,\n" +
            "\tRESULT,\n" +
            "\tMARK,\n" +
            "\tCUST_ID\n" +
            "FROM\n" +
            "\tVW_XY_WORK_INFO\n" +
            "WHERE\n" +
            "\tCUST_ID = #{custId,jdbcType=VARCHAR}\n" +
            "ORDER BY BTIME DESC" +
            "</script>")
    public List<Map<String ,Object>> getInfoHistoryFlowList(String custId) throws SQLException;
}
