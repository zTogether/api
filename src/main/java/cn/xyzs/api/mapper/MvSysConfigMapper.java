package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.MvSysConfig;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.Map;

public interface MvSysConfigMapper extends Mapper<MvSysConfig> {

    @Select("SELECT * FROM MV_SYS_CONFIG WHERE PARAMETER_KEY=#{parameterKey}")
    public Map<String ,Object> getMvSysConfig(MvSysConfig mvSysConfig) throws SQLException;

    @Select("SELECT COUNT(1) FROM MV_SYS_CONFIG WHERE PARAMETER_KEY=#{parameterKey}")
    public Integer getCount(MvSysConfig mvSysConfig) throws SQLException;

    @Insert("INSERT INTO MV_SYS_CONFIG VALUES(#{parameterKey},#{parameterValue},#{parameterMark})")
    public void addMvSysConfig(MvSysConfig mvSysConfig) throws SQLException;

    @Update("UPDATE MV_SYS_CONFIG SET PARAMETER_VALUE=#{parameterValue} WHERE PARAMETER_KEY=#{parameterKey}")
    public void updateMvSysConfig(MvSysConfig mvSysConfig) throws SQLException;

    @Delete("DELETE FROM MV_SYS_CONFIG WHERE PARAMETER_KEY=#{parameterKey}")
    public void deleteMvSysConfig(MvSysConfig mvSysConfig) throws SQLException;
}
