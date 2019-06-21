package com.haili.ins.common.spring.webmvc.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;


import javax.servlet.*;
import java.io.IOException;

@Slf4j
@Order(-1)
public class WebServletFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        StringBuilder sb = new StringBuilder();
        sb.append("###WebFilter[Thread-")
                .append((Thread.currentThread()).getId())
                .append("]");
        log.info(sb.toString());
        filterChain.doFilter(servletRequest, servletResponse);
    }


}