package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyClbZcDbMapper;
import cn.xyzs.api.mapper.XyClbZcFlMapper;
import cn.xyzs.api.mapper.XyValMapper;
import cn.xyzs.api.pojo.XyClbZcDb;
import cn.xyzs.api.pojo.XyVal;
import org.springframework.stereotype.Service;

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


}