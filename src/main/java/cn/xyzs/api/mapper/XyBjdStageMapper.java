package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyBjdStage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;

public interface XyBjdStageMapper extends Mapper<XyBjdStage> {

    /**
     * 一键报价添加bjdStage辅材
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/29 10:16
     * @param: [bjdCode, houseId, rgArray]
     * @return:
     */
    @InsertProvider(type = addAutoBjBjdStageFc.class,method = "addAutoBjBjdStageFc")
    public void addAutoBjBjdStageFc(String bjdCode ,String houseId ,String []rgArray) throws SQLException;
    public class addAutoBjBjdStageFc{
        public String addAutoBjBjdStageFc(String bjdCode ,String houseId ,String []rgArray){
            String tempSql = "";
            tempSql += "INSERT INTO XY_BJD_STAGE(\n" +
                    "\tSELECT \n" +
                    "\t\t'"+bjdCode+"' BJD_CODE,\n" +
                    "\t\t'CL' STAGE_TYPE,\n" +
                    "\t\tT.RG_STAGE BJD_STAGE,\n" +
                    "\t\tROUND(SUM(T.FC_PRICE_OUT*T.SL)*1.06,2) JE,\n" +
                    "\t\t0,\n" +
                    "\t\t0 \n" +
                    "\tFROM (\n" +
                    "\t\tSELECT\n" +
                    "\t\t\tV.RG_STAGE,\n" +
                    "\t\t\tV.FC_CODE,\n" +
                    "\t\t\tV.FC_PRICE_CODE,\n" +
                    "\t\t\tV.FC_NAME,\n" +
                    "\t\t\tV.FC_UNIT,\n" +
                    "\t\t\tV.BRAND_NAME,\n" +
                    "\t\t\tV.S_NAME,\n" +
                    "\t\t\tV.S_VAL,\n" +
                    "\t\t\tV.FC_PRICE_OUT,\n" +
                    "\t\t\tSUM( V.SL ) SL\n" +
                    "\t\tFROM (\n" +
                    "\t\t\tSELECT\n" +
                    "\t\t\t\tG.RG_STAGE,\n" +
                    "\t\t\t\tC.FC_CODE,\n" +
                    "\t\t\t\tE.FC_PRICE_CODE,\n" +
                    "\t\t\t\tC.FC_NAME,\n" +
                    "\t\t\t\tC.FC_UNIT,\n" +
                    "\t\t\t\tD.BRAND_NAME,\n" +
                    "\t\t\t\tD.S_NAME,\n" +
                    "\t\t\t\tD.S_VAL,\n" +
                    "\t\t\t\tE.FC_PRICE_OUT,\n" +
                    "\t\t\t\tCEIL(( CASE B.CLC_TYPE  WHEN '0' THEN B.SL ELSE B.SL * A.RG_QTY END ) / C.FC_N4 ) * C.FC_N4 SL   \n" +
                    "\t\t\tFROM\n" +
                    "\t\t\t\tXY_BJD_TEMPLATE_LIST A,\n" +
                    "\t\t\t\tXY_BJD_FC_MB B,\n" +
                    "\t\t\t\tXY_CLB_FC_DB C,\n" +
                    "\t\t\t\tXY_CLB_FC_BRAND D,\n" +
                    "\t\t\t\tXY_CLB_FC_DB_PRICE E,\n" +
                    "\t\t\t\tXY_MAIN_HOUSER F,\n" +
                    "\t\t\t\tXY_GCB_RG_VER_LIST G\n" +
                    "\t\t\tWHERE SUBSTR( A.RG_ID, 1, 10 ) = B.RG_CODE";
            if (rgArray != null && rgArray.length > 0){
                String tempVariable = "";
                for (int i = 0; i < rgArray.length ; i++) {
                    if (i == 0){
                        tempVariable += "'"+rgArray[i]+"'";
                    } else {
                        tempVariable += ",'"+rgArray[i]+"'";
                    }
                }
                tempSql += "\t\t\tAND A.RG_ID NOT IN("+tempVariable+")";
            }
            tempSql += "\t\t\tAND C.FC_CODE = D.FC_CODE\n" +
                    "\t\t\tAND D.BRAND_ID = E.BRAND_ID\n" +
                    "\t\t\tAND F.HOUSE_ID = A.TEMPLATE_ID\n" +
                    "\t\t\tAND F.HOUSE_ID = '"+houseId+"'\n" +
                    "\t\t\tAND C.FC_ISUSED = 1\n" +
                    "\t\t\tAND D.BRAND_ISUSED = 1\n" +
                    "\t\t\tAND E.FC_PRICE_ISUSED = 1\n" +
                    "\t\t\tAND B.FC_CODE LIKE '%'|| C.FC_CODE ||'%'\n" +
                    "\t\t\tAND G.RG_VER_CODE = F.RG_VER_CODE\n" +
                    "\t\t\tAND G.RG_ID = A.RG_ID\n" +
                    "\t\t\tAND (\n" +
                    "\t\t\t\tD.S_NAME IS NULL \n" +
                    "\t\t\t\tOR (\n" +
                    "\t\t\t\t\tD.S_NAME IS NOT NULL\n" +
                    "\t\t\t\t\tAND EXISTS (\n" +
                    "\t\t\t\t\t\tSELECT\n" +
                    "\t\t\t\t\t\t\t1\n" +
                    "\t\t\t\t\t\tFROM\n" +
                    "\t\t\t\t\t\t\tXY_HOUSE_FC_BRAND H\n" +
                    "\t\t\t\t\t\tWHERE h.house_id = a.template_id\n" +
                    "\t\t\t\t\t\tAND H.S_NAME = D.S_NAME\n" +
                    "\t\t\t\t\t\tAND H.S_VAL = D.S_VAL )))) V\n" +
                    "\t\t\tGROUP BY\n" +
                    "\t\t\t\tV.RG_STAGE,\n" +
                    "\t\t\t\tV.FC_CODE,\n" +
                    "\t\t\t\tV.FC_NAME,\n" +
                    "\t\t\t\tV.FC_UNIT,\n" +
                    "\t\t\t\tV.BRAND_NAME,\n" +
                    "\t\t\t\tV.S_NAME,\n" +
                    "\t\t\t\tV.S_VAL,\n" +
                    "\t\t\t\tV.FC_PRICE_OUT,\n" +
                    "\t\t\t\tV.FC_PRICE_CODE\n" +
                    "\t\t\tHAVING  SUM( V.SL ) <> 0\n" +
                    "\t\t)T\n" +
                    "\tGROUP BY T.RG_STAGE )";
            return tempSql;
        }
    }

    /**
     * 一键报价添加bjdStage人工
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/29 10:21
     * @param: [houseId, rgArray]
     * @return: void
     */
    @InsertProvider(type = addAutoBjBjdStageRg.class,method = "addAutoBjBjdStageRg")
    public void addAutoBjBjdStageRg(String bjdCode ,String houseId ,String []rgArray) throws SQLException;
    public class addAutoBjBjdStageRg{
        public String addAutoBjBjdStageRg(String bjdCode ,String houseId ,String []rgArray){
            String tempSql = "";
            tempSql += "INSERT INTO XY_BJD_STAGE(\n" +
                    "\t\tSELECT '"+bjdCode+"' BJD_CODE,'RG'STAGE_TYPE,RG_STAGE BJD_STAGE,SUM(RG_PRICE*RG_QTY)STAGE_HJ,0,0  \n" +
                    "\t\tFROM XY_BJD_TEMPLATE_LIST " +
                    "WHERE TEMPLATE_ID=(" +
                    "SELECT HOUSE_TEMPLATE_RG_ID FROM XY_MAIN_HOUSER WHERE HOUSE_ID = '"+houseId+"') ";
            if (rgArray != null && rgArray.length > 0){
                String tempVariable = "";
                for (int i = 0; i < rgArray.length ; i++) {
                    if (i == 0){
                        tempVariable += "'"+rgArray[i]+"'";
                    } else {
                        tempVariable += ",'"+rgArray[i]+"'";
                    }
                }
                tempSql += "\t\t\tAND RG_ID NOT IN("+tempVariable+")";
            }
            tempSql += "\t\tGROUP BY RG_STAGE)";
            return tempSql;
        }
    }

    /**
     * 一键报价删除操作
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 9:02
     * @param: [bjdCode]
     * @return: void
     */
    @Delete("<script>" +
            "DELETE FROM XY_BJD_STAGE WHERE BJD_CODE = #{bjdCode,jdbcType=VARCHAR}" +
            "</script>")
    public void autoBjDelete(String bjdCode) throws SQLException;
}
