package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyAttendancemMapper;
import cn.xyzs.api.mapper.XyShopPositionInfoMapper;
import cn.xyzs.common.pojo.XyAttendance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceService {

    @Resource
    private XyShopPositionInfoMapper xyShopPositionInfoMapper;

    @Resource
    private XyAttendancemMapper xyAttendancemMapper;

    /**
     * 获取店铺信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/13 13:12
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getShopInfoList(XyAttendance xyAttendance){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> shopInfoList = xyShopPositionInfoMapper.getShopInfoList();
            Integer todayAttendanceCount = xyAttendancemMapper.getTodayAttendanceCount(xyAttendance);
            List<Map<String ,Object>> todayAttendanceRecord = xyAttendancemMapper.getAttendanceRecordBySelectFlag(
                    "0",String.valueOf(xyAttendance.getUserId()));


            obj.put("shopInfoList",shopInfoList);
            obj.put("todayAttendanceCount",todayAttendanceCount);
            obj.put("todayAttendanceRecord",todayAttendanceRecord);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 执行考勤打卡
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/16 15:08
     * @param: [xyAttendance]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> executionAttendance(XyAttendance xyAttendance ){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            String attendanceDistanceStr = xyAttendancemMapper.getAttendanceDistance(xyAttendance);
            Double attendanceDistance = Double.valueOf(attendanceDistanceStr);
            if (attendanceDistance <= 100){
                Integer todayAttendanceCount = xyAttendancemMapper.getTodayAttendanceCount(xyAttendance);
                if (todayAttendanceCount == 2){
                    code = "401";
                    msg = "打卡次数超限！";
                } else  {
                    //userId：用户id，deviceNumber：设备号，distance：距离，longitude：经度，latitude：纬度，subordinateUnit：隶属单位，
                    //attendanceAddress：考勤地址，attendanceType：考勤类型，attendanceImg：考勤图片
                    xyAttendance.setDistance(attendanceDistanceStr);
                    if (todayAttendanceCount == 0){
                        xyAttendance.setAttendanceType("0");
                    } else {
                        xyAttendance.setAttendanceType("1");
                    }
                    xyAttendancemMapper.addAttendanceRecord(xyAttendance);
                    code = "200";
                    msg = "";
                }
            } else {
                code = "400";
                msg = "当前所在位置与所选旗舰店位置较远无法进行打卡！";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 根据selectFlag查询考勤记录（0：当天，1：昨天：2：本周，3：本月，4：上月）
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/16 15:07
     * @param: [selectFlag, userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getAttendanceRecord(String selectFlag , String userId){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> attendanceRecord = xyAttendancemMapper.getAttendanceRecordBySelectFlag(selectFlag,userId);
            obj.put("attendanceRecord",attendanceRecord);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }
}
