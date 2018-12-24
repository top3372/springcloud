package com.haili.ins.core.cat.web;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.message.internal.AbstractMessage;
import com.dianping.cat.message.internal.NullMessage;
import com.haili.ins.core.cat.CatContext;
import com.haili.ins.core.cat.CatHttpConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CatServletFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(CatServletFilter.class);
	
    private String[] urlPatterns = new String[0];

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String patterns = filterConfig.getInitParameter("CatHttpModuleUrlPatterns");
        if (patterns != null) {
            patterns = patterns.trim();
            urlPatterns = patterns.split(",");
            for (int i = 0; i < urlPatterns.length; i++) {
                urlPatterns[i] = urlPatterns[i].trim();
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info(" 开始CatServletFilter调用 : " );
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String url = request.getRequestURL().toString();
        for (String urlPattern : urlPatterns) {
            if (url.startsWith(urlPattern)) {
                url = urlPattern;
            }
        }

        Transaction t = Cat.newTransaction(CatConstants.TYPE_SERVICE, url);


        try {
            CatContext catContext = new CatContext();
            String rootId = request.getHeader(CatHttpConstants.CAT_HTTP_HEADER_ROOT_MESSAGE_ID);
            String parentId = request.getHeader(CatHttpConstants.CAT_HTTP_HEADER_PARENT_MESSAGE_ID);
            String childId = request.getHeader(CatHttpConstants.CAT_HTTP_HEADER_CHILD_MESSAGE_ID);

            catContext.addProperty(Cat.Context.ROOT, rootId);
            catContext.addProperty(Cat.Context.PARENT,parentId );
            catContext.addProperty(Cat.Context.CHILD, childId);
            Cat.logRemoteCallServer(catContext);
            logger.info(" 开始CatServletFilter调用 : " + url + " 消息模型 : APPLICATION_KEY=" + Cat.getManager().getDomain()
                    + " rootId = " + rootId + " parentId = " + parentId
                    + " childId = " + childId);


            this.createProviderCross(request, t);
            Cat.logEvent("Service.method", request.getMethod(), Message.SUCCESS, request.getRequestURL().toString());
            Cat.logEvent("Service.feign", request.getRemoteHost());

            filterChain.doFilter(servletRequest, servletResponse);

            t.setStatus(Transaction.SUCCESS);
        } catch (Exception ex) {
            Event event = Cat.newEvent("HTTP_REST_CAT_ERROR", url);
            event.setStatus(ex);
            completeEvent(event);
            t.addChild(event);
            t.setStatus(ex.getClass().getSimpleName());
            Cat.logError(ex);
            throw ex;
        } finally {
            t.complete();
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 串联provider端消息树
     *
     * @param request
     * @param t
     */
    private void createProviderCross(HttpServletRequest request, Transaction t) {
        Event crossAppEvent = Cat.newEvent(CatHttpConstants.PROVIDER_CALL_APP, request.getHeader(CatHttpConstants.CAT_HTTP_HEADER_APPLICATION_KEY));
        Event crossServerEvent = Cat.newEvent(CatHttpConstants.PROVIDER_CALL_SERVER, request.getRemoteAddr());
        crossAppEvent.setStatus(Event.SUCCESS);
        crossServerEvent.setStatus(Event.SUCCESS);
        completeEvent(crossAppEvent);
        completeEvent(crossServerEvent);
        t.addChild(crossAppEvent);
        t.addChild(crossServerEvent);
    }

    private void completeEvent(Event event) {
        if (event != NullMessage.EVENT) {
            AbstractMessage message = (AbstractMessage) event;
            message.setCompleted(true);
        }
    }

}