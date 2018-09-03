package com.miaoshasha.common.filter;

import com.miaoshasha.common.utils.Utils;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * 拦截日志的traceID，放在MDC中。供logback日志使用
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-30
 * Time：17:11
 * ================================
 */
public class TraceRequestFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            String ipAddr = String.format("[ip:%s]", Utils.getIp(request));
            String traceId = request.getHeader("X-B3-TraceId");
            String spanId = request.getHeader("X-B3-SpanId");
            // Setup MDC data:
            String traceData = String.format("[traceId:%s | spanId:%s]", traceId, spanId);
            if(StringUtils.isEmpty(traceId) && StringUtils.isEmpty(spanId)){
                traceData = "" ;
            }

            MDC.put("ip", ipAddr);
            MDC.put("trace", traceData);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {

    }
}
