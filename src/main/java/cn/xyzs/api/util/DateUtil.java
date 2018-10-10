package cn.xyzs.api.util;

import cn.xyzs.api.mapper.DateMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;

@Component
public class DateUtil {

    @Resource
    private DateMapper dateMapper;

    public String getSysDate(){
        String sysDate = "";
        try {
            sysDate = dateMapper.getSysDate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sysDate;
    }
}
