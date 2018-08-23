package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyVal;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;

public interface XyValMapper extends Mapper<XyVal>{

    @Select("<script>" +
            "SELECT * FROM XY_VAL WHERE VAL_ID IN " +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> "+
                "#{item} "+
            "</foreach> " +
            " AND VALSET_ID = 'A3B32F221FF17256988E7C0A218EBF5C'" +
            "</script>")
    @Results(id="getZcAreaList",value={
            @Result(column = "XY_VAL", property = "valsetId", javaType = String.class),
            @Result(column = "VAL_ID", property = "valId", javaType = String.class),
            @Result(column = "VAL_NAME", property = "valName", javaType = String.class),
    })
    public List<XyVal> getZcAreaList(@Param("list") List<String> list) throws SQLException;
}
