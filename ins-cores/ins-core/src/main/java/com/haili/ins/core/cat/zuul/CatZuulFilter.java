package com.haili.ins.core.cat.zuul;

import com.dianping.cat.Cat;
import com.dianping.cat.Cat.Context;
import com.dianping.cat.message.Transaction;
import com.haili.ins.core.cat.CatContext;
import com.haili.ins.core.cat.CatHttpConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.RIBBON_ROUTING_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;

public class CatZuulFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(CatZuulFilter.class);

    @Override
    public String filterType() {
        return ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return RIBBON_ROUTING_FILTER_ORDER  - 1 ;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run()  {
        logger.info(" 开始Zuul路由调用 : " );

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String routeName = request.getRequestURL().toString();
        Transaction t = Cat.getProducer().newTransaction("ZuulRouteFilter", request.getRequestURL().toString());
        try {
            Context context = new CatContext();
            Cat.logEvent("routeName", routeName);
            Cat.logRemoteCallClient(context,Cat.getManager().getDomain());
            requestContext.addZuulRequestHeader(CatHttpConstants.CAT_HTTP_HEADER_ROOT_MESSAGE_ID, context.getProperty(Context.ROOT));
            requestContext.addZuulRequestHeader(CatHttpConstants.CAT_HTTP_HEADER_PARENT_MESSAGE_ID, context.getProperty(Context.PARENT));
            requestContext.addZuulRequestHeader(CatHttpConstants.CAT_HTTP_HEADER_CHILD_MESSAGE_ID, context.getProperty(Context.CHILD));
            requestContext.addZuulRequestHeader(CatHttpConstants.CAT_HTTP_HEADER_APPLICATION_KEY, Cat.getManager().getDomain());
            logger.info(" 开始Zuul路由调用 : " + routeName + " 消息模型 : APPLICATION_KEY=" + Cat.getManager().getDomain()
                    + " rootId = " + context.getProperty(Context.ROOT) + " parentId = " + context.getProperty(Context.PARENT)
                    + " childId = " + context.getProperty(Context.CHILD));
            t.setStatus(Transaction.SUCCESS);
        } catch (Exception e) {
            t.setStatus(e);
            Cat.logError(e);
            throw e;
        } finally {
            t.complete();
            return null;
        }
    }


}
