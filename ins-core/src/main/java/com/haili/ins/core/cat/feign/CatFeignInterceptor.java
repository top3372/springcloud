package com.haili.ins.core.cat.feign;

import com.dianping.cat.Cat;
import com.dianping.cat.Cat.Context;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Transaction;
import com.haili.ins.core.cat.CatContext;
import com.haili.ins.core.cat.CatHttpConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatFeignInterceptor implements RequestInterceptor {

    private Logger logger = LoggerFactory.getLogger(CatFeignInterceptor.class);
    @Override
    public void apply(RequestTemplate requestTemplate) {

        Transaction t = Cat.newTransaction(CatConstants.TYPE_CALL, requestTemplate.method());

        // 保存和传递CAT调用链上下文
        Context ctx = new CatContext();
        Cat.logRemoteCallClient(ctx,Cat.getManager().getDomain());
        requestTemplate.header(CatHttpConstants.CAT_HTTP_HEADER_ROOT_MESSAGE_ID, ctx.getProperty(Context.ROOT));
        requestTemplate.header(CatHttpConstants.CAT_HTTP_HEADER_PARENT_MESSAGE_ID, ctx.getProperty(Context.PARENT));
        requestTemplate.header(CatHttpConstants.CAT_HTTP_HEADER_CHILD_MESSAGE_ID, ctx.getProperty(Context.CHILD));
        requestTemplate.header(CatHttpConstants.CAT_HTTP_HEADER_APPLICATION_KEY, Cat.getManager().getDomain());
        logger.info(" 开始Feign远程调用 : " + requestTemplate.method() + " 消息模型 : APPLICATION_KEY=" + Cat.getManager().getDomain() + " rootId = " + ctx.getProperty(Context.ROOT) + " parentId = " + ctx.getProperty(Context.PARENT) + " childId = " + ctx.getProperty(Context.CHILD));

        t.setStatus(Transaction.SUCCESS);
        t.complete();
    }
}
