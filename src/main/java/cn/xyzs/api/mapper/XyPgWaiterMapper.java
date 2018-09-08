package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyPgWaiter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyPgWaiterMapper extends Mapper<XyPgWaiter>{

    @Select("<script>" +
                "SELECT\n" +
            "\txpw.ZT,\n" +
            "\txpw.GR_ID,\n" +
            "\tTO_CHAR( xpw.OP_DATE, 'yyyy-MM-dd' ) OP_DATE,\n" +
            "\tTO_CHAR( xpw.END_DATE, 'yyyy-MM-dd' ) END_DATE,\n" +
            "\txpw.PG_ID,\n" +
            "\txpw.CTR_CODE,\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\txu.USER_NAME \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO xci,\n" +
            "\t\tXY_USER xu \n" +
            "\tWHERE\n" +
            "\t\txci.CTR_CODE = xpw.CTR_CODE \n" +
            "\t\tAND xci.CTR_GCJL = xu.USER_ID \n" +
            "\t) GCJL_NAME,\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\txu.USER_TEL \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO xci,\n" +
            "\t\tXY_USER xu \n" +
            "\tWHERE\n" +
            "\t\txci.CTR_CODE = xpw.CTR_CODE \n" +
            "\t\tAND xci.CTR_GCJL = xu.USER_ID \n" +
            "\t) GCJL_TEL,\n" +
            "\txcii.CTR_ADDR,\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\txv.VAL_NAME \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp,\n" +
            "\t\tXY_VAL xv \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t\tAND xp.PG_STAGE = xv.VAL_ID \n" +
            "\t\tAND xv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C' \n" +
            "\t) VAL_NAME \n" +
            "FROM\n" +
            "\tXY_PG_WAITER xpw,\n" +
            "\tXY_CUSTOMER_INFO xcii \n" +
            "WHERE\n" +
            "\txpw.GR_ID = #{grId} \n" +
            "AND \n" +
            "\txpw.CTR_CODE = xcii.CTR_CODE\n" +
            "AND \n" +
            "\txpw.ZT = #{zt}" +
            "</script>")
    public List<Map<String, Object>> getTenderHistoryList(@Param("grId") String grId, @Param("zt") String zt) throws SQLException;
}
