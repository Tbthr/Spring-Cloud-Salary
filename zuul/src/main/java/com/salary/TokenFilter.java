package com.salary;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context=RequestContext.getCurrentContext();
        HttpServletRequest req=context.getRequest();
        List<String> urls=new ArrayList<>();
        urls.add("/salary/forget");
        urls.add("/salary/sendMail");
        urls.add("/salary/login");
        String url=req.getRequestURI();
        for (String s:urls){
            //在放行的路径里
            if (s.equals(url)){
                return  false;
            }
        }
        return true;
    }

    @SneakyThrows
    @Override
    public Object run() throws ZuulException {
        RequestContext context=RequestContext.getCurrentContext();
        HttpServletRequest req=context.getRequest();
        /*
         * 是否带有token
         * */
        String token=req.getParameter("token");
        if (token==null){
            //设置不进行路由
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            //也可以返回数据
            context.setResponseBody("<h1>timeout token</h1>");
            return null;
        }
        /*
         * token验证是否有效
         * */
        String id = JWTtoken.getInfoFromToken(token);
        //无效的
        if (StringUtils.isEmpty(id)){
            //设置不进行路由
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            //也可以返回数据
            context.setResponseBody("<h1>erro token</h1>");
        }

        return null;
    }
}
