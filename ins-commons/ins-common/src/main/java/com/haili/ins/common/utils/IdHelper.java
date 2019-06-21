package com.haili.ins.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author Leon
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "spring.haili.id.generator.enabled", havingValue = "true")
public class IdHelper {

    //数据中心id
    @Value("${datacenterId}")
    private int datacenterId;

    //工作节点id
    @Value("${workerId}")
    private int workerId;

    private SnowflakeIdWorker snowflakeIdWorker;

    @PostConstruct
    private void init() {
        log.info("datacenterId : {}", datacenterId);
        log.info("workerId : {}", workerId);

        snowflakeIdWorker = new SnowflakeIdWorker(datacenterId, workerId);
    }

    /**
     * 获取ID
     *
     * @author sunk
     */
    public synchronized long nextId() {
        return snowflakeIdWorker.nextId();
    }

    /**
     *
     */
    public synchronized Map<String, Object> parseInfo(String id) {
        return snowflakeIdWorker.parseInfo(id);
    }
}
