package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyCrmKgcj;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyCrmKgcjMapper extends Mapper<XyCrmKgcj> {

    /**
     * 获取开工促进处理记录
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/21 10:28
     * @param: [custId, startNum, endNum]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT J.*  FROM ( SELECT H.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\tSELECT\n" +
            "\t\tROW_ID, \n" +
            "\t\tCUST_ID, \n" +
            "\t\tOP_USER, \n" +
            "\t\tUSER_NAME OP_USER_NAME,\n" +
            "\t\tTO_CHAR(OP_DATE,'MM-dd HH24:mi') OP_DATE_STR, \n" +
            "\t\tOP_DATE,\n" +
            "\t\tMARK \n" +
            "\tFROM\n" +
            "\t\tXY_CRM_KGCJ\n" +
            "\tLEFT JOIN XY_USER\n" +
            "\tON OP_USER = USER_ID\n" +
            "\tWHERE\n" +
            "\t\tCUST_ID = #{custId,jdbcType=VARCHAR}\n" +
            "\tORDER BY OP_DATE DESC\n" +
            "\t) H  \n" +
            ")J\n" +
            "WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getKgcjRecord(@Param("custId") String custId , @Param("startNum") Integer startNum ,
                                                   @Param("endNum")Integer endNum) throws SQLException;

    /**
     * 添加开工促进记录
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/21 10:45
     * @param: [xyCrmKgcj]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_CRM_KGCJ(\n" +
            "\tROW_ID, \n" +
            "\tCUST_ID, \n" +
            "\tOP_USER, \n" +
            "\tMARK \n" +
            ") VALUES (\n" +
            "\tKGCJ_RECORD_ROW_ID.NEXTVAL,\n" +
            "\t#{custId,jdbcType=VARCHAR},\n" +
            "\t#{opUser,jdbcType=VARCHAR},\n" +
            "\t#{mark,jdbcType=VARCHAR}\n" +
            ")" +
            "</script>")
    public void addKgcjRecord(XyCrmKgcj xyCrmKgcj) throws SQLException;
}
