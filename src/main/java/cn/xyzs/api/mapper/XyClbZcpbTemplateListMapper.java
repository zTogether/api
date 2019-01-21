package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbZcpbTemplateList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZcpbTemplateListMapper extends Mapper<XyClbZcpbTemplateList> {

    /**
     * 获取主材或软装（flag：  0：主材；8：软装）
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 16:17
     * @param: [xyClbZcpbTemplateList, flag, startNum, endNum]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT J.*  FROM ( SELECT H.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\tSELECT \n" +
            "\t\tA.* ,\n" +
            "\t\tB.ZC_NAME\n" +
            "\tFROM \n" +
            "\t\tXY_CLB_ZCPB_TEMPLATE_LIST A\n" +
            "\tLEFT JOIN XY_CLB_ZC_DB B\n" +
            "\tON A.ZCPB_ZC_CODE = B.ZC_CODE\n" +
            "\tWHERE \n" +
            "\t\tZCPB_MBID = (" +
            "SELECT HOUSE_TEMPLATE_ZC_ID FROM XY_MAIN_HOUSER WHERE HOUSE_ID = #{houseId,jdbcType=VARCHAR}" +
            ")\n" +
            "\tAND\n" +
            "\t\tZCPB_ZC_CODE <![CDATA[<>]]> '0'\n" +
            "\tAND\n" +
            "\t\tSUBSTR(ZCPB_ZC_CODE, 1, 1) = #{acOrRzFlag,jdbcType=VARCHAR}\t\n" +
            "\t) H \n" +
            ")J\n" +
            "WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getMbZcOrRzList(@Param("houseId") String houseId ,@Param("acOrRzFlag") String acOrRzFlag , @Param("startNum") Integer startNum ,
                                         @Param("endNum") Integer endNum) throws SQLException;
}
