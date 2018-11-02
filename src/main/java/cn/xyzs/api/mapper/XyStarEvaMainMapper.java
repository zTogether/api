package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyStarEvaMain;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;

public interface XyStarEvaMainMapper extends Mapper<XyStarEvaMain> {
    @Insert("<script>" +
            "INSERT INTO XY_STAR_EVA_MAIN(EVA_NO,EVA_TYPE,EVA_DATE) VALUES(#{evaNo,jdbcType=VARCHAR},#{evaType,jdbcType=VARCHAR},SYSDATE)" +
            "</script>")
    public void addEvaMain(@Param("evaNo") String evaNo, @Param("evaType") String evaType) throws SQLException;

    @Insert("<script>" +
            "INSERT INTO XY_STAR_EVA_LIST VALUES(#{evaNo,jdbcType=VARCHAR},#{level,jdbcType=VARCHAR},#{evaluation,jdbcType=VARCHAR},#{evaName,jdbcType=VARCHAR}) " +
            "</script>")
    public void addEvaList(@Param("evaNo") String evaNo,@Param("level") String level,
                           @Param("evaluation") String evaluation,@Param("evaName") String evaName) throws SQLException;

}
