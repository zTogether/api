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

    /**
     * 获取区域List
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/1 13:14
     * @param: [list]
     * @return: java.util.List<cn.xyzs.api.pojo.XyVal>
     */
    @Select("<script>" +
            "SELECT * FROM XY_VAL WHERE VAL_ID IN " +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> "+
                "#{item} "+
            "</foreach> " +
            " AND VALSET_ID = 'A3B32F221FF17256988E7C0A218EBF5C'" +
            "</script>")
    @Results(id="getZcAreaList",value={
            @Result(column = "VALSET_ID", property = "valsetId", javaType = String.class),
            @Result(column = "VAL_ID", property = "valId", javaType = String.class),
            @Result(column = "VAL_NAME", property = "valName", javaType = String.class),
    })
    public List<XyVal> getZcAreaList(@Param("list") List<String> list) throws SQLException;

    /**
     * 获取区域信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/1 13:14
     * @param: [valId]
     * @return: cn.xyzs.api.pojo.XyVal
     */
    @Select("<script>SELECT * FROM XY_VAL WHERE VAL_ID = #{valId} AND VALSET_ID = 'A3B32F221FF17256988E7C0A218EBF5C' </script>")
    @Results(id="getZcArea",value={
            @Result(column = "VALSET_ID", property = "valsetId", javaType = String.class),
            @Result(column = "VAL_ID", property = "valId", javaType = String.class),
            @Result(column = "VAL_NAME", property = "valName", javaType = String.class),
    })
    public XyVal getZcArea(@Param("valId") String valId) throws SQLException;

    /**
     * 根据
     * @Description:VALSET_ID获取区域list
     * @author: zheng shuai
     * @date: 2018/9/1 13:15
     * @param: [valsetId]
     * @return: java.util.List<cn.xyzs.api.pojo.XyVal>
     */
    @Select("<script>SELECT * FROM XY_VAL xv WHERE xv.VALSET_ID = #{valsetId}</script>")
    @Results(id="getZcAreaListByValsetId",value={
            @Result(column = "VALSET_ID", property = "valsetId", javaType = String.class),
            @Result(column = "VAL_ID", property = "valId", javaType = String.class),
            @Result(column = "VAL_NAME", property = "valName", javaType = String.class),
    })
    public List<XyVal> getZcAreaListByValsetId(@Param("valsetId") String valsetId) throws SQLException;


}
