package cn.xyzs.api.filter;

import cn.xyzs.api.ws.radis.RedisOperation;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 请求过滤
 * 1.版本检测
 */
@WebFilter
public class VersionFilter implements Filter {
    //标示符：表示当前版本需要更新执行方法
    String NO_VERSION = "-101";
    String REDIS_VERSION = "1.0";

    //不需要版本检测url
    String[] includeUrls = new String[]{"/login","/register"};

    @Resource
    private RedisOperation redisOperation;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        //        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpSession session = request.getSession(false);
//        String uri = request.getRequestURI();
//        System.out.println("filter url:"+uri);
//        //是否需要过滤
//        boolean needFilter = isNeedFilter(uri);
//
//        if (!needFilter) { //不需要过滤直接传给下一个过滤器
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else { //需要过滤器
//
//            //检测版本（从redis里面获取版本号）
//
//            String requestType = request.getHeader("X-Requested-With");
//            //判断是否是ajax请求
//            if(requestType!=null && "XMLHttpRequest".equals(requestType)){
//                response.getWriter().write(this.NO_VERSION);
//            }
//            return;
//        }
    }

    public boolean isNeedFilter(String uri) {

        for (String includeUrl : includeUrls) {
            if(includeUrl.equals(uri)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
