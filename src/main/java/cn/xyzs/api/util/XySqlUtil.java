package cn.xyzs.api.util;

import cn.xyzs.api.mapper.sys.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class XySqlUtil {

	@Autowired
	private BaseMapper baseMapper;
	
	private static XySqlUtil sqlUtil;


	@PostConstruct
	public void init() {
		sqlUtil = this;
		sqlUtil.baseMapper = this.baseMapper; 
	}



	public static List<LinkedHashMap<String, Object>> createSql(String sql,Map<String, Object> map) throws Exception{
		Set<String> keys = map.keySet();
		StringBuffer sb = new StringBuffer(sql.trim());
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String value = map.get(key).toString();
			if(value.indexOf("-")>=0){
				throw new Exception("生成sql异常,有sql注入风险");
			}
			int index = sb.indexOf("#{"+key+"}");
			if(index>0){
				sb.replace(index, index+key.length()+3,"'"+map.get(key).toString()+"'");
			}
		}
		return sqlUtil.baseMapper.superManagerSelect(sb.toString());
	}

}
