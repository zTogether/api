package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyPg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyPgMapper extends Mapper<XyPg>{

    /**
     * 根据pgId获取此标的详情
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:24
     * @param: [pgId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\tXY_PG xp\n" +
            "WHERE\n" +
            "\txp.PG_ID = #{pgId}" +
            "</script>")
    public Map<String ,Object> getXyPgInfoByPgId(String pgId) throws SQLException;

    /**
     * 修改派工主表的工人id
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:25
     * @param: [pgId, grId]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_PG\n" +
            "SET\n" +
            "\tPG_GR = #{grId}\n" +
            "WHERE\n" +
            "\tPG_ID = #{pgId}\t" +
            "</script>")
    public void updatePgGr(@Param("pgId") String pgId , @Param("grId") String grId) throws SQLException;
}
