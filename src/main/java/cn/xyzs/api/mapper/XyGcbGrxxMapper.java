package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyGcbGrxx;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyGcbGrxxMapper extends Mapper<XyGcbGrxx>{
    @Select("<script>SELECT * FROM XY_GCB_GRXX WHERE GR_TEL=#{grTel}</script>")
    public Map<String,Object> getGrInfo(String grTel)throws SQLException;
    /***
     *
     * @Description: 修改工人密码
     * @author: GeWeiliang
     * @date: 2018\9\8 0008 14:44
     * @param: [grTel, password]
     * @return: void
     */
    @Update("<script>UPDATE XY_GCB_GRXX SET PASSWORD=#{password} WHERE GR_TEL=#{grTel}</script>")
    public void changeGrPassword(@Param("grTel") String grTel,@Param("password") String password)throws SQLException;

    /***
     *
     * @Description: 修改工人信息
     * @author: GeWeiliang
     * @date: 2018\9\8 0008 15:44
     * @param: [name, idCard, grTel, grBankId, grAdd, grId]
     * @return: void
     */
    @UpdateProvider(type = updateGrInfo.class,method = "updateGrInfo")
    public void changeGrInfo(@Param("name") String name,@Param("idCard") String idCard,@Param("grTel") String grTel,
                             @Param("grBankId") String grBankId,@Param("grAdd") String grAdd,
                             @Param("grId") String grId)throws SQLException;
    class updateGrInfo{
        public String updateGrInfo(@Param("name") String name,@Param("idCard") String idCard,@Param("grTel") String grTel,
                                   @Param("grBankId") String grBankId,@Param("grAdd") String grAdd,@Param("grId") String grId){
            return new SQL(){{
                UPDATE("XY_GCB_GRXX");
                if(name!=null&&name!=""){
                    SET("GR_NAME=#{name}");
                }
                if (idCard!=null&&idCard!=""){
                    SET("GR_IDCARD=#{idCard}");
                }
                if (grTel!=null&&grTel!=""){
                    SET("GR_TEL=#{grTel}");
                }
                if (grBankId!=null&&grBankId!=""){
                    SET("GR_BANKID=#{grBankId}");
                }
                if(grAdd!=null&&grAdd!=""){
                    SET("GR_ADDR=#{grAdd}");
                }
                WHERE("GR_ID=#{grId}");
            }}.toString();
        }
    }
}
