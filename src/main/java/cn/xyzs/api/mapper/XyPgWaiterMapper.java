package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyPgWaiter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
            "\txcii.CTR_TEL,\n" +
            "\txcii.CTR_NAME,\n" +
            "\t\n" +
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
            "\t) VAL_NAME,\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\txp.PG_DAYS \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t) PG_DAYS,\n" +
            "\tTO_CHAR((\n" +
            "\tSELECT\n" +
            "\t\txp.PG_BEGIN_DATE \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t), 'yyyy-MM-dd') PGBEGINDATE,\n" +
            "\tTO_CHAR((\n" +
            "\tSELECT\n" +
            "\t\txp.PG_BEGIN_DATE + (\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\txp.PG_DAYS \n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tXY_PG xp \n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t\t) \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t), 'yyyy-MM-dd') ENDDATE,\n" +
            "\tTO_CHAR((\n" +
            "\tSELECT\n" +
            "\t\txp.PG_OP_DATE \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t), 'yyyy-MM-dd') PG_OP_DATE,\n" +
            "\tTO_CHAR((\n" +
            "\tSELECT\n" +
            "\t\txp.PG_OP_DATE + 1\n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t), 'yyyy-MM-dd hh24:mi:ss') open_tender,\n" +
            "\t(\n" +
            "\t\tSELECT vwpw.ORG_PRJ_NAME FROM VW_XY_PG_WAITER vwpw WHERE vwpw.GR_ID = #{grId} AND vwpw.PG_ID = xpw.PG_ID\n" +
            "\t) ORG_PRJ_NAME,\n" +
            "\t(\n" +
            "\t\tSELECT vwpw.ADD_MONEY FROM VW_XY_PG_WAITER vwpw WHERE vwpw.GR_ID = #{grId} AND vwpw.PG_ID = xpw.PG_ID\n" +
            "\t) ADD_MONEY,\n" +
            "\tGETDISTANCE(xcii.CTR_X,xcii.CTR_Y,grxx.GR_GPS_X,grxx.GR_GPS_Y) distance\n" +
            "FROM\n" +
            "\tXY_PG_WAITER xpw,\n" +
            "\tXY_CUSTOMER_INFO xcii,\n" +
            "\t(SELECT GR_GPS_X,GR_GPS_Y FROM XY_GCB_GRXX WHERE GR_ID = #{grId}) grxx\n" +
            "WHERE\n" +
            "\txpw.GR_ID = #{grId} \n" +
            "AND \n" +
            "\txpw.CTR_CODE = xcii.CTR_CODE\n" +
            "AND \n" +
            "\txpw.ZT = #{zt}" +
            "</script>")
    public List<Map<String, Object>> getTenderHistoryList(@Param("grId") String grId, @Param("zt") String zt) throws SQLException;

    @Select("<script>" +
            "SELECT\n" +
            "\txpw.ZT,\n" +
            "\txpw.GR_ID,\n" +
            "\tTO_CHAR( xpw.OP_DATE, 'yyyy-MM-dd' ) OP_DATE,\n" +
            "\tTO_CHAR( xpw.END_DATE, 'yyyy-MM-dd' ) END_DATE,\n" +
            "\txpw.PG_ID,\n" +
            "\txpw.CTR_CODE,\n" +
            "\txcii.CTR_TEL,\n" +
            "\txcii.CTR_NAME,\n" +
            "\t\n" +
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
            "\t) VAL_NAME,\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\txp.PG_DAYS \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t) PG_DAYS,\n" +
            "\tTO_CHAR((\n" +
            "\tSELECT\n" +
            "\t\txp.PG_BEGIN_DATE \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t), 'yyyy-MM-dd') PGBEGINDATE,\n" +
            "\tTO_CHAR((\n" +
            "\tSELECT\n" +
            "\t\txp.PG_BEGIN_DATE + (\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\txp.PG_DAYS \n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tXY_PG xp \n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t\t) \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t), 'yyyy-MM-dd') ENDDATE,\n" +
            "\tTO_CHAR((\n" +
            "\tSELECT\n" +
            "\t\txp.PG_OP_DATE \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t), 'yyyy-MM-dd') PG_OP_DATE,\n" +
            "\tTO_CHAR((\n" +
            "\tSELECT\n" +
            "\t\txp.PG_OP_DATE + 1\n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t), 'yyyy-MM-dd hh24:mi:ss') open_tender,\n" +
            "\t(\n" +
            "\t\tSELECT vwpw.ORG_PRJ_NAME FROM VW_XY_PG_WAITER vwpw WHERE vwpw.GR_ID = #{grId} AND vwpw.PG_ID = xpw.PG_ID\n" +
            "\t) ORG_PRJ_NAME,\n" +
            "\t(\n" +
            "\t\tSELECT vwpw.ADD_MONEY FROM VW_XY_PG_WAITER vwpw WHERE vwpw.GR_ID = #{grId} AND vwpw.PG_ID = xpw.PG_ID\n" +
            "\t) ADD_MONEY,\n" +
            "\tGETDISTANCE(xcii.CTR_X,xcii.CTR_Y,grxx.GR_GPS_X,grxx.GR_GPS_Y) distance\n" +
            "FROM\n" +
            "\tXY_PG_WAITER xpw,\n" +
            "\tXY_CUSTOMER_INFO xcii,\n" +
            "\t(SELECT GR_GPS_X,GR_GPS_Y FROM XY_GCB_GRXX WHERE GR_ID = #{grId}) grxx\n" +
            "WHERE\n" +
            "\txpw.GR_ID = #{grId} \n" +
            "AND \n" +
            "\txpw.CTR_CODE = xcii.CTR_CODE\n" +
            "AND \n" +
            "\txpw.ZT = '抢单成功'\n" +
            "AND\n" +
            "\txpw.YS_DATE IS NOT NULL" +
            "</script>")
    public List<Map<String ,Object>> getConstructionSiteList(@Param("grId") String grId) throws SQLException;

    @Select("<script>" +
            "SELECT\n" +
            "\txpw.ZT,\n" +
            "\txpw.GR_ID,\n" +
            "\tTO_CHAR( xpw.OP_DATE, 'yyyy-MM-dd' ) OP_DATE,\n" +
            "\tTO_CHAR( xpw.END_DATE, 'yyyy-MM-dd' ) END_DATE,\n" +
            "\txpw.PG_ID,\n" +
            "\txpw.CTR_CODE,\n" +
            "\txcii.CTR_TEL,\n" +
            "\txcii.CTR_NAME,\n" +
            "\t\n" +
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
            "\t) VAL_NAME,\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\txp.PG_DAYS \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t) PG_DAYS,\n" +
            "\tTO_CHAR((\n" +
            "\tSELECT\n" +
            "\t\txp.PG_BEGIN_DATE \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t), 'yyyy-MM-dd') PGBEGINDATE,\n" +
            "\tTO_CHAR((\n" +
            "\tSELECT\n" +
            "\t\txp.PG_BEGIN_DATE + (\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\txp.PG_DAYS \n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tXY_PG xp \n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t\t) \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t), 'yyyy-MM-dd') ENDDATE,\n" +
            "\tTO_CHAR((\n" +
            "\tSELECT\n" +
            "\t\txp.PG_OP_DATE \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t), 'yyyy-MM-dd') PG_OP_DATE,\n" +
            "\tTO_CHAR((\n" +
            "\tSELECT\n" +
            "\t\txp.PG_OP_DATE + 1\n" +
            "\tFROM\n" +
            "\t\tXY_PG xp \n" +
            "\tWHERE\n" +
            "\t\txp.PG_ID = xpw.PG_ID \n" +
            "\t), 'yyyy-MM-dd hh24:mi:ss') open_tender,\n" +
            "\t(\n" +
            "\t\tSELECT vwpw.ORG_PRJ_NAME FROM VW_XY_PG_WAITER vwpw WHERE vwpw.GR_ID = #{grId} AND vwpw.PG_ID = xpw.PG_ID\n" +
            "\t) ORG_PRJ_NAME,\n" +
            "\t(\n" +
            "\t\tSELECT vwpw.ADD_MONEY FROM VW_XY_PG_WAITER vwpw WHERE vwpw.GR_ID = #{grId} AND vwpw.PG_ID = xpw.PG_ID\n" +
            "\t) ADD_MONEY,\n" +
            "\tGETDISTANCE(xcii.CTR_X,xcii.CTR_Y,grxx.GR_GPS_X,grxx.GR_GPS_Y) distance\n" +
            "FROM\n" +
            "\tXY_PG_WAITER xpw,\n" +
            "\tXY_CUSTOMER_INFO xcii,\n" +
            "\t(SELECT GR_GPS_X,GR_GPS_Y FROM XY_GCB_GRXX WHERE GR_ID = #{grId}) grxx\n" +
            "WHERE\n" +
            "\txpw.GR_ID = #{grId} \n" +
            "AND \n" +
            "\txpw.CTR_CODE = xcii.CTR_CODE\n" +
            "AND \n" +
            "\txpw.ZT = '抢单成功'\n" +
            "AND\n" +
            "\txpw.YS_DATE IS NULL" +
            "</script>")
    public List<Map<String ,Object>> getConstructionSiteIngList(@Param("grId") String grId) throws SQLException;

    @Insert("<script>" +
            "INSERT INTO XY_PG_WAITER(\n" +
            "\tGR_ID,PG_ID,ZT,OP_DATE,END_DATE\n" +
            ") \n" +
            "VALUES(\n" +
            "\t#{grId}',\n" +
            "\t#{pgId},\n" +
            "\t'已报名',\n" +
            "\tSYSDATE,\n" +
            "\tTO_DATE(#{endDate}, 'yyyy-MM-dd hh24:mi:ss')\n" +
            ")" +
            "</script>")
    public void addXyPgWaiterInfo(@Param("grId") String grId,@Param("pgId") String pgId, @Param("endDate") String endDate) throws SQLException;

    @Update("<script>" +
            "UPDATE XY_PG_WAITER\n" +
            "SET\n" +
            "\tZT = '抢单成功'\n" +
            "WHERE\n" +
            "\tGR_ID = #{grId}\n" +
            "AND\n" +
            "\tPG_ID = #{pgId}" +
            "</script>")
    public void updateXyPgWaiterInfo(@Param("grId") String grId,@Param("pgId") String pgId) throws SQLException;

}
