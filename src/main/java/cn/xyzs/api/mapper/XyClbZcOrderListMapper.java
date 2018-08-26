package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZcOrderList;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;

public interface XyClbZcOrderListMapper extends Mapper<XyClbZcOrderList>{

    @Insert("INSERT INTO \n" +
            "\tXY_CLB_ZC_ORDER_LIST ( \n" +
            "\t\tORDER_ID,\n" +
            "\t\tROW_ID,\n" +
            "\t\tZC_CODE,\n" +
            "\t\tZC_NAME,\n" +
            "\t\tZC_TYPE,\n" +
            "\t\tZC_PRICE_IN,\n" +
            "\t\tZC_PRICE_OUT,\n" +
            "\t\tZC_QTY,\n" +
            "\t\tZC_BRAND,\n" +
            "\t\tZC_SUP,\n" +
            "\t\tZC_SPEC,\n" +
            "\t\tZC_MATERIAL,\n" +
            "\t\tZC_COLOR,\n" +
            "\t\tZC_UNIT,\n" +
            "\t\tZC_MARK,\n" +
            "\t\tZC_CYC,\n" +
            "\t\tZC_AREA,\n" +
            "\t\tZC_VERSION,\n" +
            "\t\tZC_SHOP_STATUS )\n" +
            "VALUES(\n" +
            "\t#{orderId},\n" +
            "\tsys_guid(),\n" +
            "\t#{zcCode},\n" +
            "\t#{zcName},\n" +
            "\t#{zcType},\n" +
            "\tto_number(#{zcPriceIn}),\n" +
            "\tto_number(#{zcPriceOut}),\n" +
            "\tto_number(#{zcQty}),\n" +
            "\t#{zcBrand},\n" +
            "\t#{zcSup},\n" +
            "\t#{zcSpec},\n" +
            "\t#{zcMaterial},\n" +
            "\t#{zcColor},\n" +
            "\t#{zcUnit},\n" +
            "\t#{zcMark},\n" +
            "\tto_number(#{zcCyc}),\n" +
            "\t#{zcArea},\n" +
            "\t#{zcVersion},\n" +
            "\t#{zcShopStatus}\n" +
            ")")
    public void addOrderList(XyClbZcOrderList xyClbZcOrderList) throws SQLException;
}
