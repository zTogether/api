package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.VwXyPgWaiter;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface VwXyPgWaiterMapper extends Mapper<VwXyPgWaiter> {
    @Select("<script>" +
            "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tVW_XY_PG_WAITER vxpw \n" +
            "WHERE \n" +
            "\tvxpw.GR_ID = #{grId}\n" +
            "ORDER BY\n" +
            "\tvxpw.STATE DESC,\n" +
            "\tvxpw.DISTANCE" +
            "</script>")
    public List<Map<String ,Object>> getVwXyPgWaiters(String grId) throws SQLException;
}
