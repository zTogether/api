package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbZcpbList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZcpbListMapper extends Mapper<XyClbZcpbList> {
    /**
     *
     * @Description: 获取一级目录
     * @author: GeWeiliang
     * @date: 2018\12\23 0023 9:56
     * @param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT ZCPB_ML FROM XY_CLB_ZCPB_LIST\n" +
            "LEFT JOIN ON XY_CLB_ZCPB_MAIN zm ON zm.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}" +
            "WHERE CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND ZCPB_STAGE='A' AND ZCPB_ZC_CODE IS NOT NULL\n" +
            "GROUP BY ZCPB_XH,ZCPB_ML\n" +
            "ORDER BY ZCPB_XH" +
            "</script>")
    List<Map<String,Object>> getFirstMl(@Param("ctrCode") String ctrCode,@Param("zcType") String zcType)throws SQLException;

    /**
     *
     * @Description: 获取主材配比列表
     * @author: GeWeiliang
     * @date: 2018\12\23 0023 13:45
     * @param: [ctrCode, mlName, zcType]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getZcpbList.class,method = "getZcpbList")
    List<Map<String,Object>> getZcpbList(@Param("ctrCode") String ctrCode,@Param("mlName") String mlName,
                                         @Param("zcType") String zcType,@Param("condition") String condition)throws SQLException;
    class getZcpbList{
        public String getZcpbList(@Param("ctrCode") String ctrCode,@Param("mlName") String mlName,
                                  @Param("zcType") String zcType,@Param("condition") String condition){
            return new SQL(){{
                SELECT("zl.*,zd.ZC_VERSION");
                FROM("XY_CLB_ZCPB_LIST zl");
                LEFT_OUTER_JOIN("XY_CLB_ZC_DB zd ON zl.ZCPB_ZC_CODE=zd.ZC_CODE");
                LEFT_OUTER_JOIN("XY_CLB_ZCPB_MAIN zm ON zm.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}");
                WHERE("ZL.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND ZL.ZCPB_ZC_CODE IS NOT NULL");
                if(mlName!=null&&mlName!=""){
                    WHERE("zl.ZCPB_ML=#{mlName,jdbcType=VARCHAR}");
                }
                if(zcType!=null&&zcType!=""){
                    WHERE("zl.ZCPB_STAGE=#{zcType,jdbcType=VARCHAR}");
                    WHERE("ZM.ZCPB_STATU like {#condition,jdbcType=VARCHAR}");
                }
                ORDER_BY("ZL.ZCPB_XH");
            }}.toString();
        }
    }

    @SelectProvider(type = getZj.class,method = "getZj")
    String selectedZj(@Param("ctrCode") String ctrCode,@Param("mlName") String mlName,
                                  @Param("zcType") String zcType,@Param("condition") String condition)throws SQLException;
    class getZj{
        public String getZj(@Param("ctrCode") String ctrCode,@Param("mlName") String mlName,
                            @Param("zcType") String zcType){
            return new SQL(){{
                SELECT("SUM(ZCPB_XJ) ZJ");
                FROM("XY_CLB_ZCPB_LIST");
                LEFT_OUTER_JOIN("XY_CLB_ZCPB_MAIN zm ON zm.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}");
                WHERE("CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND ZCPB_ZC_CODE IS NOT NULL");
                if(mlName!=null&&mlName!=""){
                    WHERE("ZCPB_ML=#{mlName,jdbcType=VARCHAR}");
                }
                if(zcType!=null&&zcType!=""){
                    WHERE("ZCPB_STAGE=#{zcType,jdbcType=VARCHAR}");
                    WHERE("ZM.ZCPB_STATU like {#condition,jdbcType=VARCHAR}");
                }
            }}.toString();
        }
    }

}
