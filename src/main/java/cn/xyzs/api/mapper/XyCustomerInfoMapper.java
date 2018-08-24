package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyCustomerInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyCustomerInfoMapper extends Mapper<XyCustomerInfo> {

    /**
     * 根据手机号码判断是否存在此客户
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/9 15:16
     * @param: [ctrTel]
     * @return: java.lang.Integer
     */
    @Select("SELECT COUNT(1) FROM XY_CUSTOMER_INFO xci WHERE xci.CTR_TEL = #{ctrTel}")
    public Integer isCustomer(String ctrTel) throws SQLException;

    /**
     * 根据客户电话查询客户工程信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/11 15:49
     * @param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select(" SELECT " +
                "xci.CTR_CODE ctrCode," +
                "xci.CTR_CRT_DATE ctrCrtDate," +
                "xci.CTR_KG_DATE ctrKgDate," +
                "xci.CTR_ADDR ctrAddr," +
                "xci.CTR_AREA ctrArea," +
                "xci.CTR_STRUCTURE ctrStructure," +
                "xci.CTR_FLOOR ctrFloor," +
                "xci.CTR_LIFT ctrLift," +
                "xci.CTR_PRJ_TYPE ctrPrjType," +
                "xci.CTR_QT_RANGE ctrQtQange," +
                "(SELECT USER_NAME FROM XY_USER WHERE USER_ID = " +
                    "(SELECT CTR_SJS FROM XY_CUSTOMER_INFO WHERE CTR_CODE = xci.CTR_CODE)" +
                ") ctrSjs," +
                "(SELECT USER_NAME FROM XY_USER WHERE USER_ID = " +
                    "(SELECT CTR_GCJL FROM XY_CUSTOMER_INFO WHERE CTR_CODE = xci.CTR_CODE)" +
                ") ctrGcjl," +
                "xo.ORG_NAME orgName " +
            " FROM " +
                "XY_CUSTOMER_INFO xci," +
                "XY_ORG xo" +
            " WHERE " +
                "xci.CTR_ORG = xo.ORG_CODE" +
            " AND " +
                "xci.CTR_TEL = #{ctrTel}")
    public List<Map<String, Object>> getCustomerEngineeringInfo(String ctrTel) throws SQLException;

    /**
     *
     * @Description: 根据客户号获取客户信息
     * @author: GeWeiliang
     * @date: 2018\8\23 0023 11:13
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("SELECT " +
            "xci.CTR_CODE ctrCode,xci.CTR_CRT_DATE ctrCrtDate,\"NVL\"(xci.CTR_NAME,'-') ctrName," +
            "xci.CTR_TEL ctrTel,\"NVL\"(xci.CTR_CARDID,'-') ctr_cardid,\"NVL\"(xci.CTR_ADDR,'-') ctrAddr," +
            "\"NVL\"(xci.CTR_AREA,-1) ctrArea,\"NVL\"(xci.CTR_STRUCTURE,'-') ctrStructure,\"NVL\"(xci.CTR_FLOOR,-1) ctrFloor," +
            "\"NVL\"(xci.CTR_LIFT,'-') ctrLift,\"NVL\"(xci.CTR_PRJ_TYPE,'-') ctrPrjType,\"NVL\"(xci.CTR_QT_RANGE,'-') ctrQtRange," +
            "\"NVL\"(xci.CTR_QT_TYPE,'-') ctrQtType,\"NVL\"(xci.RG_VER_CODE,'-') rgVerCode," +
            "\"NVL\"((SELECT xu.USER_NAME FROM XY_CUSTOMER_INFO xci,XY_USER xu WHERE xci.CTR_WAITER=xu.USER_ID AND xci.CTR_CODE=#{ctrCode}), '-') ctrWaiter," +
            "\"NVL\"((SELECT xo.ORG_NAME FROM XY_ORG xo,XY_CUSTOMER_INFO xci WHERE xci.CTR_ORG=xo.ORG_CODE AND xci.CTR_CODE=#{ctrCode}),'-') ctrOrg," +
            "\"NVL\"((SELECT xu.USER_NAME FROM XY_CUSTOMER_INFO xci,XY_USER xu WHERE xci.CTR_SJS=xu.USER_ID AND xci.CTR_CODE=#{ctrCode}),'-') ctrSjs," +
            "\"NVL\"(xci.CTR_GCJL,'-') CtrGctl," +
            "\"NVL\"((SELECT xu.USER_NAME FROM XY_CUSTOMER_INFO xci,XY_USER xu WHERE xci.CTR_CLDD=xu.USER_ID AND xci.CTR_CODE=#{ctrCode}),'-') CtrCldd," +
            "\"NVL\"(xci.CTR_ROWID,'-') ctrRowid,\"NVL\"(xci.CTR_AREA_MA,'-') ctrAreaMa," +
            "\"NVL\"((SELECT xo.ORG_NAME FROM XY_ORG xo,XY_CUSTOMER_INFO xci WHERE xci.CTR_PRO_ORG=xo.ORG_CODE AND xci.CTR_CODE=#{ctrCode}),'-') ctrProOrg," +
            "xci.CTR_KG_DATE ctrKgDate," +
            "\"NVL\"(xci.CTR_STATU,'-') ctrStatu,\"NVL\"(xci.CTR_DRAW,'-') ctrDraw,\"NVL\"(xci.DRAW_STATU,'-') drawStatu," +
            "\"NVL\"(xci.CTR_X,'-') ctrX," +
            "\"NVL\"(xci.CTR_Y,'-') ctrY,\"NVL\"(xci.CTR_MAP_VERSION,'-') ctrMapVersion" +
            " FROM XY_CUSTOMER_INFO xci WHERE CTR_CODE=#{ctrCode}")
//    @Select("SELECT * FROM XY_CUSTOMER_INFO WHERE CTR_CODE=#{ctrCode}")
    public Map<String,Object> getCustInfoByCtrCode(@Param("ctrCode")String ctrCode);
}
