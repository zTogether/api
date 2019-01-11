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
            "SELECT * FROM (\n" +
            "\tSELECT\n" +
            "\t\tC.USER_ID,\n" +
            "\t\tC.USER_NAME,\n" +
            "\t\tC.USER_TEL,\n" +
            "\t\tC.USER_SEX\n" +
            "\tFROM\n" +
            "\t\tXY_CRM_RELATION A \n" +
            "\tLEFT JOIN XY_USER C\n" +
            "\tON A.LOWER_USER_ID = C.USER_ID\n" +
            "\tWHERE\n" +
            "\t\tA.EXPRESS = #{express,jdbcType=VARCHAR}\n" +
            "\tSTART WITH A.USER_ID = #{userId,jdbcType=VARCHAR}\n" +
            "\tCONNECT BY A.USER_ID = PRIOR A.LOWER_USER_ID\n" +
            "\tUNION ALL\n" +
            "\tSELECT\n" +
            "\t\tC.USER_ID,\n" +
            "\t\tC.USER_NAME,\n" +
            "\t\tC.USER_TEL,\n" +
            "\t\tC.USER_SEX\n" +
            "\tFROM\n" +
            "\t\tXY_CRM_RELATION A \n" +
            "\tLEFT JOIN XY_USER C\n" +
            "\tON A.LOWER_USER_ID = C.USER_ID\n" +
            "\tSTART WITH A.USER_ID IN (\n" +
            "\t\tSELECT\n" +
            "\t\t\tB.LOWER_USER_ID\n" +
            "\t\tFROM\n" +
            "\t\t\tXY_CRM_RELATION B\n" +
            "\t\tWHERE \n" +
            "\t\t\tB.EXPRESS = #{express,jdbcType=VARCHAR}\n" +
            "AND\n" +
            "\t\t\tB.USER_ID = #{userId,jdbcType=VARCHAR}" +
            "\t)\n" +
            "\tCONNECT BY A.USER_ID = PRIOR A.LOWER_USER_ID\n" +
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
            "SELECT COUNT(1) FROM (\n" +
            "\tSELECT\n" +
            "\t\t'1'\n" +
            "\tFROM\n" +
            "\t\tXY_CRM_RELATION A \n" +
            "\tWHERE\n" +
            "\t\tA.EXPRESS = #{express,jdbcType=VARCHAR}\n" +
            "\tSTART WITH A.USER_ID = #{userId,jdbcType=VARCHAR}\n" +
            "\tCONNECT BY A.USER_ID = PRIOR A.LOWER_USER_ID\n" +
            "\tUNION ALL\n" +
            "\tSELECT\n" +
            "\t\t'1'\n" +
            "\tFROM\n" +
            "\t\tXY_CRM_RELATION A \n" +
            "\tSTART WITH A.USER_ID IN (\n" +
            "\t\tSELECT\n" +
            "\t\t\tB.LOWER_USER_ID\n" +
            "\t\tFROM\n" +
            "\t\t\tXY_CRM_RELATION B\n" +
            "\t\tWHERE \n" +
            "\t\t\tB.EXPRESS = #{express,jdbcType=VARCHAR}\n" +
            "\t\tAND\n" +
            "\t\t\tB.USER_ID = #{userId,jdbcType=VARCHAR}\n" +
            "\t)\n" +
            "\tCONNECT BY A.USER_ID = PRIOR A.LOWER_USER_ID\n" +
            ")" +
            "</script>")
    public Integer getSubordinateCount(@Param("express") String express ,@Param("userId") String userId) throws SQLException;

}
