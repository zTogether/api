package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbFcDb;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbFcDbMapper extends Mapper<XyClbFcDb>{

    /**
     * 根据材料分类获取辅材商品
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/12 10:09
     * @param: [ctrCode, fcType]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tC.FC_PRICE_CODE,\n" +
            "\tA.FC_NAME,\n" +
            "\tB.BRAND_NAME,\n" +
            "\tA.FC_SPEC,\n" +
            "\tA.FC_UNIT,\n" +
            "\tC.FC_PRICE_OUT,\n" +
            "\tA.FC_PUR_STY,\n" +
            "\tA.FC_C1 \n" +
            "FROM\n" +
            "\tXY_CLB_FC_DB A,\n" +
            "\tXY_CLB_FC_BRAND B,\n" +
            "\tXY_CLB_FC_DB_PRICE C \n" +
            "WHERE\n" +
            "\tA.FC_CODE = B.FC_CODE \n" +
            "\tAND B.BRAND_ID = C.BRAND_ID \n" +
            "\tAND #{fcType ,jdbcType=VARCHAR} LIKE '%' || A.FC_TYPE || '%' \n" +
            "\tAND C.FC_PRICE_ISUSED = 1 \n" +
            "\tAND ((\n" +
            "\t\t\tEXISTS (\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\t1 \n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tXY_BJD_FC_TEMP D \n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\tD.CTR_CODE = #{ctrCode ,jdbcType=VARCHAR} \n" +
            "\t\t\t\tAND D.S_NAME = B.S_NAME \n" +
            "\t\t\t\tAND D.S_VAL = B.S_VAL \n" +
            "\t\t\t\tAND D.S_VAL IS NOT NULL \n" +
            "\t\t\t) \n" +
            "\t\t\tAND B.S_NAME IS NOT NULL \n" +
            "\t\t\t) \n" +
            "\t\tOR B.S_NAME IS NULL \n" +
            "\t) \n" +
            "ORDER BY\n" +
            "\t3,2" +
            "</script>")
    public List<Map<String ,Object>> getFcGoodByFcType(@Param("ctrCode") String ctrCode, @Param("fcType") String fcType) throws SQLException;
}
