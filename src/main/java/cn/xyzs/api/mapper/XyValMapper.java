package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyVal;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取允许验收的工种
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/28 16:17
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tval.VAL_ID,\n" +
            "\tval.VAL_NAME\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT \n" +
            "\t\t\txv.VAL_ID,\n" +
            "\t\t\txv.VAL_NAME\n" +
            "\t\tFROM \n" +
            "\t\t\tXY_VAL xv \n" +
            "\t\tWHERE \n" +
            "\t\t\txv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C'\n" +
            "\t\tAND\n" +
            "\t\t\txv.VAL_ID IN(10,21,22,30,40,50)\n" +
            "\t) val\n" +
            "WHERE val.VAL_ID NOT IN(\n" +
            "\tSELECT xpy.YS_GZ FROM XY_PG_YS xpy WHERE xpy.CTR_CODE = #{ctrCode}\n" +
            ")" +
            "</script>")
    public List<Map<String ,Object>> getSllowYsGz(String ctrCode) throws SQLException;

    /**
     *
     * @Description:
     * @author: GeWeiliang
     * @date: 2018\10\6 0006 17:20
     * @param: [valId]
     * @return: java.util.List<cn.xyzs.api.pojo.XyVal>
     */
    @Select("<script>" +
            "SELECT VAL_NAME FROM XY_VAL WHERE VAL_ID=#{valId} AND VALSET_ID='B3B32F221FF14256988E7C0A218EBF5C'" +
            "</script>")
    public List<XyVal> getRgVer(@Param("valId") String valId) throws SQLException;
}
