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
            "\tTO_CHAR(vxpw.PG_OP_DATE, 'yyyy-MM-dd hh24:mi:ss' ) PG_OP_DATE, \n" +
            "\tvxpw.PG_ID,\n" +
            "\tvxpw.GR_ID, \n" +
            "\tvxpw.GR_NAME, \n" +
            "\tvxpw.DISTANCE, \n" +
            "\tTO_CHAR(vxpw.PG_BEGIN_DATE, 'yyyy-MM-dd hh24:mi:ss' ) PG_BEGIN_DATE,\n" +
            "\tvxpw.GZ, \n" +
            "\tvxpw.ADD_MONEY, \n" +
            "\tvxpw.CTR_CODE, \n" +
            "\tvxpw.CTR_NAME, \n" +
            "\tvxpw.CTR_ADDR, \n" +
            "\tvxpw.ORG_PRJ_NAME, \n" +
            "\tvxpw.STATE,\n" +
            "\txp.PG_DAYS,\n" +
            "\tTO_CHAR(( vxpw.PG_BEGIN_DATE + xp.PG_DAYS ), 'yyyy-MM-dd hh24:mi:ss' ) ENDDATE \n" +
            "FROM\n" +
            "\tVW_XY_PG_WAITER vxpw,\n" +
            "\tXY_PG xp \n" +
            "WHERE\n" +
            "\tvxpw.GR_ID = #{grId,jdbcType=VARCHAR}\n" +
            "\tAND vxpw.PG_ID = xp.PG_ID \n" +
            "ORDER BY\n" +
            "\tvxpw.STATE DESC,\n" +
            "\tvxpw.DISTANCE" +
            "</script>")
    public List<Map<String ,Object>> getVwXyPgWaiters(String grId) throws SQLException;
}
