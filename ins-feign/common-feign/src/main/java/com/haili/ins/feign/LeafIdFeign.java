package com.haili.ins.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: leon
 * @Date: 2019/3/11 11:48
 * @Version 1.0
 */
@Component
@FeignClient(name = "ins-leaf")
public interface LeafIdFeign {

    /**
     * getSegmentID
     *
     * @param key
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/segment/get/{key}")
    String getSegmentID(@PathVariable(value = "key") String key);

    /**
     * getSnowflakeID
     *
     * @param key
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/snowflake/get/{key}")
    String getSnowflakeID(@PathVariable(value = "key") String key);
}
