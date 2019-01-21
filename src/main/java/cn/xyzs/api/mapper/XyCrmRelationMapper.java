package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyCrmRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyCrmRelationMapper extends Mapper<XyCrmRelation> {

    /**
     * 根据userId和roleId获取下属
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/4 13:05
     * @param: [xyCrmRelation]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM (" +
            "SELECT\n" +
            "\tDISTINCT \n" +
            "\tC.USER_ID,\n" +
            "  C.USER_NAME,\n" +
            "  C.USER_TEL,\n" +
            "  C.USER_SEX\n" +
            "FROM\n" +
            "\tXY_CRM_RELATION A \n" +
            "LEFT JOIN XY_USER C \n" +
            "ON A.LOWER_USER_ID = C.USER_ID\n" +
            "START WITH  a.USER_ID=#{userId,jdbcType=VARCHAR} and a.EXPRESS = #{express,jdbcType=VARCHAR} \n" +
            "CONNECT BY A.USER_ID = PRIOR (case  a.is_end when 1 then A.LOWER_USER_ID else TO_NCHAR('aaa') end)" +
            ") \n" +
            "WHERE USER_NAME LIKE '%'||#{condition,jdbcType=VARCHAR}||'%'\n" +
            "OR USER_TEL = #{condition,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getSubordinateByUserIdAndRoleId(@Param("express") String express ,@Param("userId") String userId ,@Param("condition") String condition) throws SQLException;

    /**
     * 获取下属人数
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/4 16:56
     * @param: [express, userId]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCOUNT(1)\n" +
            "FROM\n" +
            "\tXY_CRM_RELATION A \n" +
            "START WITH  a.USER_ID=#{userId,jdbcType=VARCHAR} and a.EXPRESS = #{express,jdbcType=VARCHAR} \n" +
            "CONNECT BY A.USER_ID = PRIOR (case  a.is_end when 1 then A.LOWER_USER_ID else TO_NCHAR('aaa') end)" +
            "</script>")
    public Integer getSubordinateCount(@Param("express") String express ,@Param("userId") String userId) throws SQLException;


}
