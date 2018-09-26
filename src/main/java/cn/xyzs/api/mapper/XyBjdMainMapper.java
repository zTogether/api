package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyBjdMain;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyBjdMainMapper extends Mapper<XyBjdMain>{

    /**
     *
     * @Description: 报价清单
     * @author: GeWeiliang
     * @date: 2018\9\20 0020 9:32
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txbm.* ,\n" +
            "\tTO_CHAR(xbm.CREATE_DATE,'yyyy-MM-dd HH24:mi:ss') CREATEDATE\n" +
            "FROM\n" +
            "\tXY_BJD_MAIN xbm\t\n" +
            "WHERE\n" +
            "\tCTR_CODE = #{ctrCode}" +
            "</script>")
    public List<Map<String,Object>> bjdList(String ctrCode) throws SQLException;
}
