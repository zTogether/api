package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyClbZcFlMapper;
import cn.xyzs.api.mapper.XyClbZctxMbMapper;
import cn.xyzs.api.pojo.XyClbZcFl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

@Service
public class WholeDecoratesService {
    @Resource
    private XyClbZctxMbMapper xyClbZctxMbMapper;
    @Resource
    private XyClbZcFlMapper xyClbZcFlMapper;

    /***
     *
     * @Description: 展示主材套系vr
     * @author: GeWeiliang
     * @date: 2018\9\2 0002 17:47
     * @param:
     * @return:
     */
    public Map<String,Object> showZctxVr(String vrStyle){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            List<Map<String,Object>> vrList = xyClbZctxMbMapper.showZctxVr(vrStyle);
            obj.put("zctxVr",vrList);
            code = "200";
            msg = "成功";
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
     * 获取套系的详细信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:33
     * @param: [vrId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> txDetail(String vrId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            Map<String,Object> vrDetail = xyClbZctxMbMapper.vrDetail(vrId);
            String pic = (String)vrDetail.get("VR_PIC");
            GoodService goodService = new GoodService();
            List<String> picList = goodService.conversionList(pic);
            obj.put("picList",picList);
            obj.put("vrDetail",vrDetail);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
            return  resultMap;
    }

    /***
     *
     * @Description: 获取材料列表
     * @author: GeWeiliang
     * @date: 2018\9\10 0010 17:18
     * @param: [vrId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getZctxMbInfo(String vrId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> yzZctxMblist = xyClbZctxMbMapper.getZctxMbList(vrId,"A");
            List<List<Map<String ,Object>>> yzZctxMbCommitlist =  dataFormat(yzZctxMblist);

            List<Map<String ,Object>> rzZctxMblist = xyClbZctxMbMapper.getZctxMbList(vrId,"B");
            List<List<Map<String ,Object>>> rzZctxMbCommitlist =  dataFormat(rzZctxMblist);

            obj.put("yzZctxMbCommitlist",yzZctxMbCommitlist);
            obj.put("rzZctxMbCommitlist",rzZctxMbCommitlist);
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
     * 数据分离
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:33
     * @param: [listMap]
     * @return: java.util.List<java.util.List<java.util.Map<java.lang.String,java.lang.Object>>>
     */
    public  List<List<Map<String ,Object>>> dataFormat( List<Map<String ,Object>> listMap){
        Set<String> yzFlSet = new TreeSet<>();
        GoodService goodService = new GoodService();
        for (Map<String, Object> map : listMap) {
            yzFlSet.add(String.valueOf(map.get("FL_BH")));
        }
        List<List<Map<String ,Object>>> yzZctxMbCommitlist = new ArrayList<>();
        for (String s : yzFlSet) {
            List<Map<String ,Object>> tempList = new ArrayList<>();
            for (Map<String, Object> map : listMap) {
                if (s.equals(String.valueOf(map.get("FL_BH")))){
                    tempList.add(map);
                }

                if(map.get("ZC_PRICE_OUT")==null||map.get("ZC_PRICE_OUT")==""){
                    map.put("ZC_PRICE_OUT","-");
                }
                if (map.get("ZC_CYC")==null||map.get("ZC_CYC")==""){
                    map.put("ZC_CYC","-");
                }
            }

            yzZctxMbCommitlist.add(tempList);
        }
        return yzZctxMbCommitlist;
    }

}
