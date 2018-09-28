package cn.xyzs.api.mapper;

import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

public interface DateMapper {
    /**
     *
     * @Description: 获取系统时间
     * @author: GeWeiliang
     * @date: 2018\9\28 0028 10:13
     * @param: []
     * @return: java.lang.String
     */
    @Select("select TO_CHAR(SYSDATE,'yyyy-MM-dd HH24:mi:ss')sysdate  from dual")
    public String getSysDate() throws SQLException;
}
