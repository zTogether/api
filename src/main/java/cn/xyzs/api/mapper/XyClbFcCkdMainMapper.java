package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbFcCkdMain;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbFcCkdMainMapper extends Mapper<XyClbFcCkdMain>{

    /**
     *
     * @Description: 辅材清单
     * @author: GeWeiliang
     * @date: 2018\9\19 0019 9:45
     * @param: [ctrCcode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tfcm.CKD_CODE, \n" +
            "\tfcm.CTR_CODE, \n" +
            "\tfcm.CKD_TYPE, \n" +
            "\tTO_CHAR(fcm.CKD_INPUT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_INPUT_DATE, \n" +
            "\tfcm.CKD_FC_TYPE, \n" +
            "\tfcm.CKD_CK, \n" +
            "\tfcm.CKD_OP_USER, \n" +
            "\tfcm.CKD_ZJ, \n" +
            "\tTO_CHAR(fcm.CKD_AUDIT_DATE,'yyyy-MM-dd HH24:mi:ss') CKD_AUDIT_DATE, \n" +
            "\tfcm.CKD_STATU, \n" +
            "\tfcm.CKD_MARK,\n" +
            "\tu.USER_NAME,\n" +
            "\txv.VAL_NAME \n" +
            "FROM\n" +
            "\tXY_CLB_FC_CKD_MAIN fcm\n" +
            "\tLEFT JOIN XY_USER u ON u.USER_ID = fcm.CKD_OP_USER\n" +
            "\tLEFT JOIN XY_VAL xv ON fcm.CKD_FC_TYPE = xv.VAL_ID \n" +
            "\tAND xv.VALSET_ID = '39AB9E59F1EF4CF6ACD71CF9D89F8129' \n" +
            "WHERE\n" +
            "\tCTR_CODE = #{ctrCcode,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> fcList(String ctrCcode) throws SQLException;
}
