package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyStarEvaList;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyStarEvaListMapper extends Mapper<XyStarEvaList> {

    @Insert("<script>" +
            "INSERT INTO XY_STAR_EVA_LIST \n" +
            "VALUES(#{evaNo,jdbcType=VARCHAR},#{quality,jdbcType=VARCHAR},#{evaluation,jdbcType=VARCHAR}," +
            "#{evaName,jdbcType=VARCHAR},sys_guid(),#{evaType,jdbcType=VARCHAR},SYSDATE,#{service,jdbcType=VARCHAR}," +
            "#{days,jdbcType=VARCHAR},#{hygiene,jdbcType=VARCHAR}) " +
            "</script>")
    public void addEvaList(@Param("evaNo") String evaNo, @Param("quality") String quality,@Param("evaluation") String evaluation,
                           @Param("evaName") String evaName, @Param("evaType") String evaType,@Param("service") String service,
                           @Param("days") String days,@Param("hygiene") String hygiene) throws SQLException;


    /**
     *
     * @Description: 查询出工人及其是否特权
     * @author: GeWeiliang
     * @date: 2018\12\16 0016 10:21
     * @param: [ctrCode, pgStage1, pgStage2]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getIsPriv.class,method = "getIsPriv")
    List<Map<String,Object>> getIsPriv(@Param("ctrCode") String evaNo,@Param("pgStage1") String pgStage1,@Param("pgStage2") String pgStage2)throws SQLException;
    class getIsPriv{
        public String getIsPriv(@Param("ctrCode") String evaNo,@Param("pgStage1") String pgStage1,@Param("pgStage2") String pgStage2){
            return new SQL(){{
                SELECT("w.GR_ID,p.PG_STAGE,w.PRIV_YN");
                FROM("XY_PG_WAITER w");
                LEFT_OUTER_JOIN("XY_PG p ON p.PG_ID=w.PG_ID");
                WHERE("p.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND w.ZT='抢单成功'");
                if(pgStage1!=null&&pgStage1!=""&&pgStage2!=null&pgStage2!=""){
                    WHERE("(p.PG_STAGE=#{pgStage1,jdbcType=VARCHAR} OR p.PG_STAGE=#{pgStage2,jdbcType=VARCHAR})");
                }else{
                    WHERE("p.PG_STAGE=#{pgStage1,jdbcType=VARCHAR}");
                }
            }}.toString();
        }
    }

    /**
     *
     * @Description: 获取工人当前抢单等级
     * @author: GeWeiliang
     * @date: 2018\12\16 0016 11:57
     * @param: [grId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT GR_ID,GR_LEVEL FROM XY_GCB_GRXX WHERE GR_ID=#{grId,jdbcType=VARCHAR}" +
            "</script>")
    Map<String,Object> getCruLevel(@Param("grId") String grId)throws SQLException;

    /**
     *
     * @Description: 更改工人抢单等级
     * @author: GeWeiliang
     * @date: 2018\12\16 0016 10:25
     * @param: [grId, level]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_GCB_GRXX SET GR_LEVEL=#{level,jdbcType=VARCHAR} WHERE GR_ID=#{grId,jdbcType=VARCHAR}" +
            "</script>")
    void UpdateGrLevel(@Param("grId") String grId,@Param("level") String level)throws SQLException;
}
