package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZcOrderListFree;
import cn.xyzs.api.pojo.XyVal;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;

public interface XyClbZcOrderListFreeMapper extends Mapper<XyClbZcOrderListFree>{

    /**
     * 根据orderId查询非标商品
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/31 10:51
     * @param: [orderId]
     * @return: java.util.List<cn.xyzs.api.pojo.XyClbZcOrderListFree>
     */
    @Select("SELECT * FROM XY_CLB_ZC_ORDER_LIST_FREE xczolf WHERE xczolf.ORDER_ID = #{orderId}")
    @Results(id="getNonStandard",value={
            @Result(column = "ORDER_ID", property = "orderId", javaType = String.class),
            @Result(column = "ROW_ID", property = "rowId", javaType = String.class),
            @Result(column = "ZC_CODE", property = "zcCode", javaType = String.class),
            @Result(column = "ZC_NAME", property = "zcName", javaType = String.class),
            @Result(column = "ZC_TYPE", property = "zcType", javaType = String.class),
            @Result(column = "ZC_PRICE_IN", property = "zcPriceIn", javaType = String.class),
            @Result(column = "ZC_PRICE_OUT", property = "zcPriceOut", javaType = String.class),
            @Result(column = "ZC_QTY", property = "zcQty", javaType = String.class),
            @Result(column = "ZC_BRAND", property = "zcBrand", javaType = String.class),
            @Result(column = "ZC_SUP", property = "zcSup", javaType = String.class),
            @Result(column = "ZC_SPEC", property = "zcSpec", javaType = String.class),
            @Result(column = "ZC_MATERIAL", property = "zcMaterial", javaType = String.class),
            @Result(column = "ZC_COLOR", property = "zcColor", javaType = String.class),
            @Result(column = "ZC_UNIT", property = "zcUnit", javaType = String.class),
            @Result(column = "ZC_MARK", property = "zcMark", javaType = String.class),
            @Result(column = "ZC_CYC", property = "zcCyc", javaType = String.class),
            @Result(column = "ZC_AREA",property = "zcArea", javaType = String.class),
            @Result(column = "ZC_VERSION", property = "zcVersion", javaType = String.class),
            @Result(column = "ZC_SHOP_STATUS", property = "zcShopStatus", javaType = String.class),
            @Result(column="ZC_AREA",property="xyVal",one=@One(select="cn.xyzs.api.mapper.XyValMapper.getZcArea",fetchType= FetchType.EAGER))
    })
    public List<XyClbZcOrderListFree> getNonStandard(@Param("orderId") String orderId) throws SQLException;
}
