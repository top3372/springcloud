package com.haili.ins.invoke.dto;

import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @author Leon
 */
@Data
public class InvokeParameter implements Serializable {

    private static final String SPLITOR = "[分隔符]";
    private static final int SIZE = 9;

    private String serCode;
    private String sysTraceNo;
    private String originNo;
    private String targetNo;
    private String versionNo;
    private int dataLength;
//    private int msgCompress;
    private String dataMsg;
    private String token;
    private String requestSource;

    public void parse(String responseStr) {
        if (responseStr == null || responseStr.length() == 0) {
            throw new RuntimeException("无效请求参数");
        }

        String[] params = responseStr.split("\\[分隔符\\]");
        if (params.length != SIZE) {
            throw new RuntimeException("无效请求参数");
        }

        this.serCode = params[0];
        this.sysTraceNo = params[1];
        this.originNo = params[2];
        this.targetNo = params[3];
        this.versionNo = params[4];
        this.dataLength = Integer.parseInt(params[5]);
//        this.msgCompress = Integer.parseInt(params[6]);
        this.dataMsg = params[6];
        this.token = params[7];
        this.requestSource = params[8];
    }

    public void setDataMsg(String dataMsg) {
        this.dataMsg = dataMsg;
        try {
            this.dataLength = dataMsg.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {

        StringBuffer sbf = new StringBuffer();
        sbf.append(serCode).append(SPLITOR);
        sbf.append(sysTraceNo).append(SPLITOR);
        sbf.append(originNo).append(SPLITOR);
        sbf.append(targetNo).append(SPLITOR);
        sbf.append(versionNo).append(SPLITOR);
        sbf.append(dataLength).append(SPLITOR);
        //sbf.append(msgCompress).append(SPLITOR);
        sbf.append(dataMsg);
        sbf.append(token);
        sbf.append(requestSource);
        return sbf.toString();
    }

    public String print() {
        StringBuffer sbf = new StringBuffer();
        sbf.append("[").append(serCode).append("]");
        sbf.append("[").append(sysTraceNo).append("]");
        sbf.append("[").append(originNo).append("]");
        sbf.append("[").append(targetNo).append("]");
        sbf.append("[").append(token).append("]");
        sbf.append("[").append(requestSource).append("]");
        return sbf.toString();
    }

    public static void main(String[] args) {
        InvokeParameter param = new InvokeParameter();
        param.setSerCode("403001");
        param.setSysTraceNo("1");
        param.setOriginNo("404");
        param.setTargetNo("403");
        param.setVersionNo("v1");
        //param.setMsgCompress(0);
        param.setDataMsg("测试");
        param.setToken("12312313");
        param.setRequestSource("gateway");

        String tmp = param.toString();

        System.out.println(tmp);

        param.parse(tmp);

        System.out.println(param.getDataMsg());

    }
}
