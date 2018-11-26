package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyStarEvaList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;

public interface XyStarEvaListMapper extends Mapper<XyStarEvaList> {

    @Insert("<script>" +
            "INSERT INTO XY_STAR_EVA_LIST \n" +
            "VALUES(#{evaNo,jdbcType=VARCHAR},#{level,jdbcType=VARCHAR},#{evaluation,jdbcType=VARCHAR},#{evaName,jdbcType=VARCHAR},sys_guid(),#{evaType,jdbcType=VARCHAR},SYSDATE) " +
            "</script>")
    public void addEvaList(@Param("evaNo") String evaNo,@Param("level") String level,
                           @Param("evaluation") String evaluation,@Param("evaName") String evaName,@Param("evaType") String evaType) throws SQLException;

}
