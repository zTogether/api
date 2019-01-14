package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyRoleFuc;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyRoleFucMapper extends Mapper<XyRoleFuc> {

    /**
     * 根据条件获取菜单配置信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/12 9:41
     * @param: [xyRoleFuc]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getRolefuc.class,method = "getRolefuc")
    public List<Map<String ,Object>> getRolefuc(XyRoleFuc xyRoleFuc) throws SQLException;
    public class getRolefuc{
        public String getRolefuc(XyRoleFuc xyRoleFuc){
            return new SQL(){{
                SELECT("ROLE_ID,COMPO_ID,OP_ID");
                FROM("XY_ROLE_FUC");
                if (xyRoleFuc.getRoleId() != null && !"".equals(xyRoleFuc.getRoleId())){
                    WHERE("ROLE_ID = '"+xyRoleFuc.getRoleId()+"'");
                }

                if (xyRoleFuc.getCompoId() != null && !"".equals(xyRoleFuc.getCompoId())){
                    WHERE("COMPO_ID = '"+xyRoleFuc.getCompoId()+"'");
                }

                if (xyRoleFuc.getOpId() != null && !"".equals(xyRoleFuc.getOpId())){
                    WHERE("OP_ID = '"+xyRoleFuc.getOpId()+"'");
                }
            }}.toString();
        }
    }
}
