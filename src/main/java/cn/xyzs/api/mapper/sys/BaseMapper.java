package cn.xyzs.api.mapper.sys;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

@Component
public interface BaseMapper {
	List<LinkedHashMap<String, Object>> superManagerSelect(String sql);

}
