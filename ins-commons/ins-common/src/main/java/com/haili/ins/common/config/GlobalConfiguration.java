package com.haili.ins.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: leon
 * @Date: 2019/4/16 15:56
 * @Version 1.0
 */
@Configuration
public class GlobalConfiguration {

    @Value("${haili.ins.profile.down-load-path}")
    private static String downLoadPath;

    public static String getDownLoadPath() {
        return downLoadPath;
    }

}
