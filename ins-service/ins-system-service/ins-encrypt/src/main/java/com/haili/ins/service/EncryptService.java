package com.haili.ins.service;

import com.haili.ins.common.utils.security.MD5Util;
import com.haili.ins.common.invoke.dto.InvokeRequest;
import com.haili.ins.common.invoke.dto.InvokeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: leon
 * @Date: 2019/3/6 17:24
 * @Version 1.0
 */
@Service
@Slf4j
public class EncryptService {

    @Value("${encrypt.keyword:GAOEX&021#38645}")
    private String keyWord;

    private EncryptService() {
        // 读取证书地址

    }

    public InvokeResponse encrypt(InvokeRequest invokeRequest) throws Exception {
        InvokeResponse invokeResponse = new InvokeResponse();

        String dataMsg = invokeRequest.getDataMsg();

        String encryptMsg = encryptedMsg(dataMsg);

        invokeResponse.buildSuccResp(encryptMsg);

        return invokeResponse;
    }

    /**
     * 关键密文加密
     *
     * @param flatMsg
     * @return
     * @throws Exception
     */
    public synchronized String encryptedMsg(String flatMsg) throws Exception {
        return MD5Util.md5Hex(flatMsg + keyWord);
    }

//    public static void main(String[] args) throws Exception{
//        String msg = MsgEncrypt.getInstance().encryptedMsg("sagdjlsadjglsajgdlsajdglasjasdgasdgasdgsadgsadgdglasjgdlsajdglaskdjgksadjgksadjgksajgdlsajdglsajgdlsadjg");
//        System.out.println(msg);
//    }

}
