package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyStarEvaList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;

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

}
