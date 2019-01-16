package cn.xyzs.api.controller;

import cn.xyzs.api.service.AttendanceService;
import cn.xyzs.common.pojo.XyAttendance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/attendance")
public class AttendanceController {

    @Resource
    private AttendanceService attendanceService;

    /**
     * 获取店铺信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/13 13:12
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getShopInfoList")
    public Map<String ,Object> getShopInfoList(XyAttendance xyAttendance){
        return attendanceService.getShopInfoList(xyAttendance);
    }

    @ResponseBody
    @RequestMapping("/executionAttendance")
    public Map<String ,Object> attendance(XyAttendance xyAttendance){
        return attendanceService.executionAttendance(xyAttendance);
    }
}
