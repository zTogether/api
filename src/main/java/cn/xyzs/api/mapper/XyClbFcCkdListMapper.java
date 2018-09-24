package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbFcCkdList;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbFcCkdListMapper extends Mapper<XyClbFcCkdList> {

    /**
     * 获取辅材出库明细
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/23 10:08
     * @param: [ckdCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txcfcl.CKD_CODE, \n" +
            "\txcfcl.CKD_ROW, \n" +
            "\txcfcl.FC_PRICE_ID, \n" +
            "\txcfcl.FC_PP, \n" +
            "\txcfcl.FC_NAME, \n" +
            "\txcfcl.FC_UNIT, \n" +
            "\txcfcl.FC_QTY, \n" +
            "\txcfcl.FC_PRICE, \n" +
            "\txcfcl.FC_JE, \n" +
            "\txcfcl.FC_PER, \n" +
            "\txcfcl.FC_YF,\n" +
            "\txcfcl.FC_XJ, \n" +
            "\txcfcl.FC_MARK, \n" +
            "\txcfcl.FC_ISWC, \n" +
            "\txcfcl.FC_ISEDIT, \n" +
            "\txcfcl.FC_PRINT_GROUP\n" +
            "FROM\n" +
            "\tXY_CLB_FC_CKD_LIST xcfcl\t\n" +
            "WHERE\n" +
            "\txcfcl.CKD_CODE = #{ckdCode}" +
            "</script>")
    public List<Map<String ,Object>> getFcCkdList(String ckdCode) throws SQLException;

}
