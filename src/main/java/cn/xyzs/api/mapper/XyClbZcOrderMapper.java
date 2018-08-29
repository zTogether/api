package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZcOrder;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZcOrderMapper extends Mapper<XyClbZcOrder> {
    /**
     *
     * @Description: 添加订单主表
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 18:39
     * @param: [orderDate, ctrCode, opUserid, orderJe, orderMark]
     * @return: int
     */
    @Insert("INSERT INTO \n" +
            "\tXY_CLB_ZC_ORDER ( \n" +
            "\t\tORDER_ID,\n" +
            "\t\tORDER_DATE,\n" +
            "\t\tCTR_CODE,\n" +
            "\t\tOP_USERID,\n" +
            "\t\tORDER_JE,\n" +
            "\t\tORDER_MARK,\n" +
            "\t\tORDER_STATUS,\n" +
            "\t\tORDER_TYPE,\n" +
            "\t\tORDER_SUP,\n" +
            "\t\tEDIT_TYPE,\n" +
            "\t\tORDER_DIS,\n" +
            "\t\tORDER_DIS_MARK,\n" +
            "\t\tORDER_ISRETURN\n" +
            "\n" +
            "\t) \n" +
            "VALUES(\n" +
            "\tsys_guid(),\n" +
            "\tSYSDATE,\n" +
            "\t#{ctrCode},\n" +
            "\t#{opUserid},\n" +
            "\t#{orderJe},\n" +
            "\t#{orderMark},\n" +
            "\t#{orderStatus},\n" +
            "\t#{orderType},\n" +
            "\t#{orderSup},\n" +
            "\t#{editType},\n" +
            "\t#{orderDis},\n" +
            "\t#{orderDisMark},\n" +
            "\t#{orderIsreturn}\n" +
            ")")
    @Options(useGeneratedKeys=true, keyProperty="orderId", keyColumn="ORDER_ID")
    public void addZcOrder(XyClbZcOrder xyClbZcOrder) throws SQLException;

    /***
     *
     * @Description: 根据ctrCode查询订单
     * @author: GeWeiliang
     * @date: 2018\8\29 0029 9:40
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("SELECT * FROM(\n" +
            "\tSELECT A.*,ROWNUM RN FROM(\n" +
            "\t\tSELECT zo.*,u.USER_NAME,sup.SUP_NAME \n" +
            "\t\tFROM XY_CLB_ZC_ORDER zo,XY_USER u,XY_SUPPLIER sup\n" +
            "\t\tWHERE zo.CTR_CODE=2018000468 AND zo.OP_USERID=u.USER_ID AND sup.SUP_CODE=zo.ORDER_SUP\n" +
            "\t\tORDER BY zo.ORDER_DATE DESC" +
            "\t) A\n" +
            ") WHERE RN BETWEEN 1 AND 10")
    public List<Map<String,Object>> queryOrderByctrCode(@Param("ctrCode") String ctrCode);

    /***
     *
     * @Description: 删除订单
     * @author: GeWeiliang
     * @date: 2018\8\29 0029 9:48
     * @param: [orderId]
     * @return: void
     */
    @Delete("DELETE FROM XY_CLB_ZC_ORDER WHERE ORDER_ID=#{orderId}")
    public void deleteFromOrder(@Param("orderId") String orderId);
    @Delete("DELETE FROM XY_CLB_ZC_ORDER_LIST WHERE ORDER_ID=#{orderId}")
    public void deleteFromOrderList(@Param("orderId") String orderId);

}
