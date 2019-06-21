package com.haili.ins.common.invoke;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志输出
 *
 * @author jack
 */
@Slf4j
public class InvokeLogger {

    private static final String SER_CODE = "serCode";

    private static final String SYS_TRACE_NO = "sysTraceNo";


    private static final ThreadLocal<Map<String, String>> sessionMap = new ThreadLocal<Map<String, String>>();

    /**
     * This Method should be used in Service Facade.
     * <p>
     * Note: service implementations should not use this method to change the session
     * identification
     *
     * @param serCode
     * @param sysTraceNo
     */
    public static void setSession(String serCode, String sysTraceNo) {
        Map<String, String> sessionMap = new HashMap<String, String>(2, 1);
        sessionMap.put(SER_CODE, serCode);
        sessionMap.put(SYS_TRACE_NO, sysTraceNo);

        InvokeLogger.sessionMap.set(sessionMap);
    }

    public static void removeSession() {
        InvokeLogger.sessionMap.remove();
    }

    public static boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    public static boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public static boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    public static void trace(String msg, Object... obj) {
        log.trace(wrapMsg(msg, obj));
    }

    public static void debug(String msg, Object... obj) {
        log.debug(wrapMsg(msg, obj));
    }

    public static void info(String msg, Object... obj) {
        log.info(wrapMsg(msg, obj));
    }

    public static void warn(String msg, Object... obj) {
        log.warn(wrapMsg(msg, obj));
    }

    public static void error(String msg, Object... obj) {
        log.error(wrapMsg(msg, obj));
    }

    public static void error(String msg, Throwable cause) {
        log.error(wrapMsg(msg), cause);
    }

    private static String wrapMsg(String msg, Object... keyValues) {
        StringBuilder sb = new StringBuilder();
        sb.append("[Thread-")
                .append((Thread.currentThread()).getId())
                .append("]")
                .append("[")
                .append(InvokeLogger.sessionMap.get() == null ? "undefined" : InvokeLogger.sessionMap.get().get(SYS_TRACE_NO))
                .append("][")
                .append(InvokeLogger.sessionMap.get() == null ? "undefined" : InvokeLogger.sessionMap.get().get(SER_CODE))
                .append("]")
                .append(msg);

        if (keyValues != null && keyValues.length > 0) {
            boolean isKey = true;
            for (Object entry : keyValues) {
                if (isKey) {
                    isKey = false;
                    sb.append(":");
                    sb.append(entry);
                } else {
                    sb.append("=");
                    sb.append(entry);
                    isKey = true;
                }
            }
        }

        return sb.toString();
    }
}