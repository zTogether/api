package cn.xyzs.api.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CommonSetFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //解决跨域问题
        HttpServletResponse httpServletResponse =(HttpServletResponse)response;
        // 指定允许其他域名访问
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        // 响应类型
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST");
        // 响应头设置
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
