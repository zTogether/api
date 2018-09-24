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

    /**
     *
     * @Description: 工程清单
     * @author: GeWeiliang
     * @date: 2018\9\19 0019 9:14
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tpg.PG_ID, \n" +
            "\tpg.CTR_CODE,\n" +
            "\tTO_CHAR(pg.PG_OP_DATE,'yyyy-MM-dd HH24:mi:ss') PG_OP_DATE,\n" +
            "\tpg.PG_STAGE,\n" +
            "\tpg.PG_GR,\n" +
            "\tTO_CHAR(pg.PG_BEGIN_DATE,'yyyy-MM-dd HH24:mi:ss') PG_BEGIN_DATE,\n" +
            "\tpg.PG_DAYS,\n" +
            "\tpg.PG_OP_USER,\n" +
            "\tpg.PG_MONEY_YN,\n" +
            "\tpg.PG_PRINT_YN,\n" +
            "\tpg.PG_ADD_MONEY,\n" +
            "\tu.USER_NAME pg_op_name,\n" +
            "\tu.USER_TEL,\n" +
            "\tgr.GR_NAME,\n" +
            "\tgr.GR_TEL,\n" +
            "\txv.VAL_NAME\n" +
            "FROM\n" +
            "\tXY_PG pg\n" +
            "\tLEFT JOIN XY_USER u ON u.USER_ID = pg.PG_OP_USER\n" +
            "\tLEFT JOIN XY_GCB_GRXX gr ON gr.GR_ID = pg.PG_GR\n" +
            "\tLEFT JOIN XY_VAL xv ON pg.PG_STAGE = xv.VAL_ID \n" +
            "\tAND xv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C' \n" +
            "WHERE\n" +
            "\tpg.CTR_CODE = #{ctrCode}" +
            "</script>")
    public List<Map<String,Object>> getPrjList(String ctrCode) throws SQLException;

}
