package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZcOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface XyClbZcOrderMapper extends Mapper<XyClbZcOrder> {
    /**
     *
     * @Description: 添加订单主表
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 18:39
     * @param: [orderDate, ctrCode, opUserid, orderJe, orderMark]
     * @return: int
     */
    @Insert("INSERT INTO XY_CLB_ZC_ORDER(ORDER_DATE,CTR_CODE,OP_USERID,ORDER_MARK,ORDER_STATUS) \n" +
            "VALUES(#{orderDate},#{ctrCode},#{opUserid},#{orderJe},#{orderMark},1)")
    int addZcOrder(@Param("orderDate") String orderDate, @Param("ctrCode") String ctrCode,
                   @Param("opUserid") String opUserid, @Param("orderJe") String orderJe,
                   @Param("orderMark") String orderMark);



}
