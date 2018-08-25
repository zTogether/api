package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyClbZcDbMapper;
import cn.xyzs.api.mapper.XyClbZcFlMapper;
import cn.xyzs.api.mapper.XyCustomerInfoMapper;
import cn.xyzs.api.mapper.XyValMapper;

import cn.xyzs.api.mapper.*;
import cn.xyzs.api.mapper.*;
import cn.xyzs.api.pojo.XyClbZcDb;
import cn.xyzs.api.pojo.XyClbZcShopping;
import cn.xyzs.api.pojo.XyVal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodService {

    @Resource
    private XyClbZcFlMapper xyClbZcFlMapper;

    @Resource
    private XyClbZcDbMapper xyClbZcDbMapper;

    @Resource
    private XyValMapper xyValMapper;

    @Resource
    private XyCustomerInfoMapper xyCustomerInfoMapper;

    @Resource
    private XyClbZcShoppingMapper xyClbZcShoppingMapper;


    /**
     * 获取下级目录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/20 14:53
     * @param: [zcflCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public  Map<String, Object> getSubdirectory(String zcflCode){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String, Object>> Subdirectory = xyClbZcFlMapper.getSubdirectory(zcflCode);
            obj.put("Subdirectory",Subdirectory);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    public  Map<String, Object> sortFilter(String zcflCode,String startNum,String endNum){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        List<String> zcflCodeList = new ArrayList<>();
        String code = "500";
        String msg = "系统异常";
        int a = 0;
        try {
            if (startNum == null || startNum == ""){
                startNum = "1";
                endNum = "10";
            }
            List<Map<String, Object>> YSubdirectory = xyClbZcFlMapper.getSubdirectory(zcflCode);
            if (YSubdirectory.size() < 1){
                System.out.println(1);
                zcflCodeList.add(zcflCode);
            } else {
                test(YSubdirectory,zcflCodeList);
            }
            List<XyClbZcDb> goodList = xyClbZcDbMapper.getGoodByZcType(zcflCodeList,startNum,endNum);
            for (XyClbZcDb xyClbZcDb : goodList) {
                List<XyVal> xyZcAcerList = xyValMapper.getZcAreaList(conversionList(xyClbZcDb.getZcArea()));
                xyClbZcDb.setXyZcAreas(xyZcAcerList);
            }
            obj.put("goodList",goodList);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    public void test(List<Map<String, Object>> YSubdirectory,List<String> zcflCodeList){
        try {
            for (Map<String, Object> map : YSubdirectory) {
                YSubdirectory = xyClbZcFlMapper.getSubdirectory((String) map.get("ZCFL_CODE"));
                if (YSubdirectory.size()<1){
                    zcflCodeList.add((String) map.get("ZCFL_CODE"));
                    continue;
                } else {
                    test(YSubdirectory,zcflCodeList);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<String> conversionList(String a){
        String []b = a.split(",");
        List<String> list = new ArrayList<>();
        for (int i = 0; i <b.length ; i++) {
            list.add(b[i]);
        }
        return list;
    }

    public Map<String,Object> getCustomerInfoByUserId(String userId,String startNum,String endNum,String roleType){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String,Object>> CustomerInfoList = null;
            if ("E".equals(roleType)){
                CustomerInfoList = xyCustomerInfoMapper.getCustomerInfoByRoleTypeE(userId,startNum,endNum);
            } else if("R".equals(roleType)){
                CustomerInfoList = xyCustomerInfoMapper.getCustomerInfoByRoleTypeR(userId,startNum,endNum);
            }
            obj.put("CustomerInfoList",CustomerInfoList);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     *
     * @Description: 根据客户号查询购物车
     * @author: GeWeiliang
     * @date: 2018\8\23 0023 15:05
     * @param: [ctrCOde]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getZcShoppingByCtrCode(String ctrCode){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        double sum;
        try{
            List<Map<String,Object>> shoppingList = xyClbZcShoppingMapper.showZcShopping(ctrCode);
            for (Map<String, Object> map : shoppingList) {
                String area = xyClbZcShoppingMapper.getArea((String)map.get("ZC_TYPE"),(String)map.get("ZC_CODE"));
                List<XyVal> list = xyValMapper.getZcAreaList(conversionList(area));
                map.put("areaList",list);
            }
            code = "200";
            msg = "成功";
            obj.put("shoppingList",shoppingList);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     *
     * @Description: 添加购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 9:30
     * @param: [ctrCode, opUserid, zcCode, zcQty, zcArea, zcMark]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> addShoppingCart(String ctrCode,String opUserid,String zcCode,String zcQty,String zcArea,String zcMark){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            List<Map<String,Object>> zcList = xyClbZcShoppingMapper.queryZcDb(zcCode);
            XyClbZcDb xyClbZcDb = new XyClbZcDb();
            for (Map<String, Object> map : zcList) {
                xyClbZcDb.setZcType((String) map.get("ZC_TYPE"));
                xyClbZcDb.setZcName((String) map.get("ZC_NAME"));
                xyClbZcDb.setZcPriceIn((String) map.get("ZC_PRICE_IN"));
                xyClbZcDb.setZcPirceLook((String) map.get("ZC_PRICE_LOOK"));
                xyClbZcDb.setZcPriceOut((String) map.get("ZC_PRICE_OUT"));
                xyClbZcDb.setZcPriceHd((String) map.get("ZC_PRICE_HD"));
                xyClbZcDb.setZcBrand((String)map.get("ZC_BRAND"));
                xyClbZcDb.setZcSup((String)map.get("ZC_SUP"));
                xyClbZcDb.setZcSpec((String) map.get("ZC_SPEC"));
                xyClbZcDb.setZcMaterial((String)map.get("ZC_MATERIAL"));
                xyClbZcDb.setZcColor((String)map.get("ZC_COLOR"));
                xyClbZcDb.setZcStyle((String)map.get("ZC_STYLE"));
                xyClbZcDb.setZcArea(zcArea);
                xyClbZcDb.setZcUnit((String) map.get("ZC_UNIT"));
                xyClbZcDb.setZcCyc((String) map.get("ZC_CYC"));
            }
            xyClbZcShoppingMapper.addShoppingCart(ctrCode,opUserid,zcCode,xyClbZcDb.getZcName(),xyClbZcDb.getZcType(),
                    zcQty,xyClbZcDb.getZcPriceIn(),xyClbZcDb.getZcPriceOut(),xyClbZcDb.getZcBrand(),xyClbZcDb.getZcSup(),
                    xyClbZcDb.getZcSpec(),xyClbZcDb.getZcMaterial(),xyClbZcDb.getZcColor(),xyClbZcDb.getZcUnit(),zcMark,
                    xyClbZcDb.getZcCyc(),zcArea);

            code = "200";
            msg = "已加入购物车";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     *
     * @Description: 将商品移出购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 9:35
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> removeGoods(String rowId){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            xyClbZcShoppingMapper.removeGoods(rowId);
            code = "200";
            msg = "移除成功";
        }catch (SQLException e){
            e.printStackTrace();;
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     *
     * @Description: 修改购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 10:06
     * @param: [rowId, zcQty, zcArea, zcMark]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> updateGoods(String rowId,String zcQty,String zcArea,String zcMark){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            xyClbZcShoppingMapper.updateGoods(rowId,zcQty,zcArea,zcMark);
            code = "200";
            msg = "更改成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

}
