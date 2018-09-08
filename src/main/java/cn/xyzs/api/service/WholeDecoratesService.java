package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyClbZcFlMapper;
import cn.xyzs.api.mapper.XyClbZctxMbMapper;
import cn.xyzs.api.pojo.XyClbZcFl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String,Object> txDetail(String vrId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            obj = xyClbZctxMbMapper.vrDetail(vrId);
            String pic = (String)obj.get("VR_PIC");
            GoodService goodService = new GoodService();
            List<String> picList = goodService.conversionList(pic);
            obj.put("picList",picList);
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
     * @Description: 套系材料列表
     * @author: GeWeiliang
     * @date: 2018\9\3 0003 16:47
     * @param: [vrId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> txClList(String vrId,String mlId){
        GoodService goodService = new GoodService();
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            List<Map<String,Object>> clList = xyClbZctxMbMapper.txClList(vrId,mlId);
            for (Map<String, Object> map : clList) {
                String mlZcfl = (String)map.get("ML_ZCFL");
                if (mlZcfl!=null && mlZcfl!=""){
                    List<String> flList = xyClbZcFlMapper.getZcFl(goodService.conversionList(mlZcfl));
                    map.put("FL",flList);
                }else{
                    map.put("FL","-");
                }
                if (map.get("ZC_PRICE_OUT")==null||map.get("ZC_PRICE_OUT")==""){
                    map.put("ZC_PRICE_OUT","-");
                }
                if(map.get("ZC_CYC")==null||map.get("ZC_CYC")==""){
                    map.put("ZC_CYC","-");
                }
            }
            obj.put("clList",clList);
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
}
