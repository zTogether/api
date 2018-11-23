package cn.xyzs.api.controller;

import cn.xyzs.api.service.ChatRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/chatRoom")
public class ChatRoomController {

    @Resource
    private ChatRoomService chatRoomService;

    /**
     *
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 9:21
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getServicePersonalInfoByCtrCode")
    public Map<String ,Object> getServicePersonalInfoByCtrCode(String ctrCode){
        return chatRoomService.getServicePersonalInfoByCtrCode(ctrCode);
    }

    /**
     * 根据CtrCode和Jd获取jdJs（在聊天页面使用）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/27 14:25
     * @param: [ctrCode, jd]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getJdjsVByCtrCodeAndJd")
    public Map<String ,Object> getJdjsVByCtrCodeAndJd(String ctrCode , String jd){
        return chatRoomService.getJdjsVByCtrCodeAndJd(ctrCode,jd);
    }

    /**
     * 获取允许执行员发送主材订单验收的主材订单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/19 13:24
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getZxySendZcYsOrder")
    public Map<String ,Object> getZxySendZcYsOrder(String ctrCode){
        return chatRoomService.getZxySendZcYsOrder(ctrCode);
    }

    /**
     * 客户主材订单验收
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/19 17:22
     * @param: [orderId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/khZcOrderYs")
    public Map<String ,Object> khZcOrderYs(String orderId){
        return chatRoomService.khZcOrderYs(orderId);
    }

    /**
     * 客户是否可以进行主材订单验收
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/19 17:38
     * @param: [orderId]
     * @return: \
     */
    @ResponseBody
    @RequestMapping("/isZcOrderYs")
    public Map<String ,Object>isZcOrderYs(String orderId){
        return chatRoomService.isZcOrderYs(orderId);
    }

    /**
     * 根据成员id获取成员电话
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/23 14:52
     * @param: [memberId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getMemberTel")
    public Map<String ,Object> getMemberTel(String memberId){
        return chatRoomService.getMemberTel(memberId);
    }

}
