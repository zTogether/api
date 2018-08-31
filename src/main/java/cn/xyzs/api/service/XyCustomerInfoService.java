package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyClbZcShoppingMapper;
import cn.xyzs.api.mapper.XyCustomerInfoMapper;
import cn.xyzs.api.mapper.XySupplierMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XyCustomerInfoService {

    @Resource
    private XyCustomerInfoMapper xyCustomerInfoMapper;
    @Resource
    private XySupplierMapper xySupplierMapper;
    @Resource
    private XyClbZcShoppingMapper xyClbZcShoppingMapper;

    public Map<String, Object> getCustomerEngineeringInfo(String ctrTel){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String, Object>> CustomerEngineeringInfo = xyCustomerInfoMapper.getCustomerEngineeringInfo(ctrTel);
            code = "200";
            msg = "成功";
            obj.put("CustomerEngineeringInfo",CustomerEngineeringInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resultMap.put("code",code);
        resultMap.put("msg",msg);
        resultMap.put("resultData",obj);
        return resultMap;
    }


    /**
     *
     * @Description: 根据客户档案号获取客户信息
     * @author: GeWeiliang
     * @date: 2018\8\23 0023 11:12
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getCustInfoByCtrCode(String ctrCode){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> custInfo = xyCustomerInfoMapper.getCustInfoByCtrCode(ctrCode);
        map.put("customerInfo",custInfo);
        return map;
    }

}
