package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyHouseFcBrand;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyHouseFcBrandMapper extends Mapper<XyHouseFcBrand> {

    /**
     * 根据条件获取辅材品牌
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/27 10:13
     * @param: [xyHouseFcBrand]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getFcBrandByCondition.class,method = "getFcBrandByCondition")
    public List<Map<String ,Object>> getFcBrandByCondition(XyHouseFcBrand xyHouseFcBrand) throws SQLException;
    public class getFcBrandByCondition{
        public String getFcBrandByCondition(XyHouseFcBrand xyHouseFcBrand){
            return new SQL(){{
                SELECT("S_NAME,\n" +
                        "\tS_VAL,\n" +
                        "\tHOUSE_ID");
                FROM("XY_HOUSE_FC_BRAND");
                //根据小区查询
                if (!"".equals(xyHouseFcBrand.getHouseId()) && !"null".equals(xyHouseFcBrand.getHouseId()) && xyHouseFcBrand.getHouseId() != null){
                    WHERE("HOUSE_ID = '"+xyHouseFcBrand.getHouseId()+"'");
                }
                //根据户型查询
                if (!"".equals(xyHouseFcBrand.getSName()) && !"null".equals(xyHouseFcBrand.getSName()) && xyHouseFcBrand.getSName() != null){
                    WHERE("S_NAME = '"+ xyHouseFcBrand.getSName() +"'");
                }
                //根据风格查询
                if (!"".equals(xyHouseFcBrand.getSVal()) && !"null".equals(xyHouseFcBrand.getSVal()) && xyHouseFcBrand.getSVal() != null){
                    WHERE("S_VAL = '"+ xyHouseFcBrand.getSVal() +"'");
                }
                ORDER_BY("S_VAL");
            }}.toString();
        }
    }
}
