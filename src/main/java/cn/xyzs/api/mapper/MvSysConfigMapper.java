package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.MvSysConfig;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.Map;

public interface MvSysConfigMapper extends Mapper<MvSysConfig> {
    @Select("SELECT * FROM MV_SYS_CONFIG WHERE PARAMETER_KEY=#{key}")
    public Object getObj(@Param("key") String parameterKey);

    @Select("SELECT * FROM MV_SYS_CONFIG WHERE PARAMETER_KEY=#{key}")
    public int queryConfig(@Param("key") String parameterKey);

    @Insert("INSERT INTO MV_SYS_CONFIG VALUES(#{key},#{value})")
    public void addConfig(@Param("key") String parameterKey,@Param("value") String value)throws SQLException;

    @Update("UPDATE MV_SYS_CONFIG SET PARAMETER_VALUE=#{value} WHERE PARAMETER_KEY=#{key}")
    public void updateConfig(@Param("key") String parameterKey,@Param("value") String value)throws SQLException;

    @Delete("DELETE FROM MV_SYS_CONFIG WHERE PARAMETER_KEY=#{key}")
    public void deleteConfig(@Param("key") String parameterKey);
}
