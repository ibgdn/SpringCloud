package com.ibgdn.zuul.controller;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul Filter
 */
@Component
public class PermissionController extends ZuulFilter {
    /**
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     * <p>
     * 过滤器的类型，权限判断一般是 pre
     *
     * @return A String representing that type
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     * <p>
     * 过滤器优先级
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     * <p>
     * 是否过滤
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * if shouldFilter() is true, this method will be invoked. this method is the core method of a ZuulFilter
     * <p>
     * 核心的过滤逻辑
     *
     * @return Some arbitrary artifact may be returned. Current implementation ignores it.
     * @throws ZuulException if an error occurs during execution.
     */
    @Override
    public Object run() throws ZuulException {
        // 获取当前请求
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!"user".equals(username) || !"123456".equals(password)) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(800);
            currentContext.addZuulResponseHeader("content-type", "text/html;charset=utf-8");
            currentContext.setResponseBody("Zuul 登录信息错误");
        }
        return null;
    }
}
