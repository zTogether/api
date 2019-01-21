package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyBjdTemplateList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyBjdTemplateListMapper extends Mapper<XyBjdTemplateList>{

    /**
     * 获取人工费项目
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 17:12
     * @param: [templateId, startNum, endNum]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT J.*  FROM ( SELECT H.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\tSELECT \n" +
            "\t\t* \n" +
            "\tFROM \n" +
            "\t\tXY_BJD_TEMPLATE_LIST\n" +
            "\tWHERE\n" +
            "\t\tTEMPLATE_ID = (" +
            "SELECT HOUSE_TEMPLATE_RG_ID FROM XY_MAIN_HOUSER WHERE HOUSE_ID = #{houseId,jdbcType=VARCHAR}" +
            ")\n" +
            "\t) H \n" +
            ")J\n" +
            "WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getMbRgList(@Param("houseId") String houseId ,
                                               @Param("startNum") Integer startNum , @Param("endNum") Integer endNum) throws SQLException;
}
