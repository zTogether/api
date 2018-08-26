package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZcOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;

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



}
