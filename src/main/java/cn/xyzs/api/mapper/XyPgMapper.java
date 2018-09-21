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
            "SELECT pg.*,u.USER_NAME,u.USER_TEL,gr.GR_NAME,gr.GR_TEL,xv.VAL_NAME,pg.PG_MONEY_YN " +
            "FROM XY_PG pg\n" +
            "LEFT JOIN XY_USER u ON u.USER_ID=pg.PG_OP_USER\n" +
            "LEFT JOIN XY_GCB_GRXX gr ON gr.GR_ID=pg.PG_GR\n" +
            "LEFT JOIN XY_VAL xv ON pg.PG_STAGE=xv.VAL_ID AND xv.VALSET_ID='B3B32F221FF14256988E7C0A218EBF5C'\n" +
            "WHERE pg.CTR_CODE=#{ctrCode} " +
            "</script>")
    public List<Map<String,Object>> getPrjList(String ctrCode) throws SQLException;

    /**
     *
     * @Description: 收款明细
     * @author: GeWeiliang
     * @date: 2018\9\19 0019 9:19
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>SELECT * FROM XY_CWB_SK WHERE CTR_CODE=#{ctrCode} </script>")
    public List<Map<String,Object>> skList(String ctrCode) throws SQLException;

    /**
     *
     * @Description: 辅材清单
     * @author: GeWeiliang
     * @date: 2018\9\19 0019 9:45
     * @param: [ctrCcode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT fcm.*,u.USER_NAME,xv.VAL_NAME FROM XY_CLB_FC_CKD_MAIN fcm\n" +
            "LEFT JOIN XY_USER u ON u.USER_ID=fcm.CKD_OP_USER\n" +
            "LEFT JOIN XY_VAL xv ON fcm.CKD_FC_TYPE=xv.VAL_ID AND xv.VALSET_ID='39AB9E59F1EF4CF6ACD71CF9D89F8129'" +
            "WHERE CTR_CODE=#{ctrCode}" +
            "</script>")
    public List<Map<String,Object>> fcList(String ctrCcode) throws SQLException;

    /**
     *
     * @Description: 报价清单
     * @author: GeWeiliang
     * @date: 2018\9\20 0020 9:32
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM XY_BJD_MAIN WHERE CTR_CODE=#{ctrCode}" +
            "</script>")
    public List<Map<String,Object>> bjdList(String ctrCode) throws SQLException;

    /**
     *
     * @Description: 收款情况
     * @author: GeWeiliang
     * @date: 2018\9\20 0020 10:56
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("SELECT SUM(CASE WHEN CWB_SK_IO='2' THEN -(CWB_SK_MONEY)\n" +
            "\t\tWHEN CWB_SK_IO='1' THEN CWB_SK_MONEY END) AS MONEY,\n" +
            "\t\tCWB_SK_CONTENT AS B\n" +
            "\t\tFROM XY_CWB_SK WHERE CTR_CODE=#{ctrCode} GROUP BY CWB_SK_CONTENT")
    public List<Map<String,Object>> skCondition(String ctrCode);
}
