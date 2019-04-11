package com.haili.ins.common.cloud.version.web;

import lombok.Getter;
import org.springframework.http.MediaType;

/**
 * @Author: leon
 * @Date: 2019/4/11 17:25
 * @Version 1.0
 */
@Getter
public class InsMediaType {

    private static final String MEDIA_TYPE_TEMP = "application/vnd.%s.%s+json";

    private final String appName = "haili.ins";
    private final String version;
    private final MediaType mediaType;

    public InsMediaType(String version) {
        this.version = version;
        this.mediaType = MediaType.valueOf(String.format(MEDIA_TYPE_TEMP, appName, version));
    }

    @Override
    public String toString() {
        return mediaType.toString();
    }
}
