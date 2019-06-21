package com.haili.ins.config.message;

import com.haili.ins.config.message.RabbitmqMessage;
import lombok.Data;
import lombok.ToString;
import org.springframework.amqp.rabbit.connection.CorrelationData;

import java.io.Serializable;

/**
 * @author: LeonMa
 * @date: 2019/01/08 14:36
 */
@Data
@ToString
public class MessageCorrelationData<T> extends CorrelationData implements Serializable {

    private static final long serialVersionUID = 6256300521232589907L;

    public MessageCorrelationData() {
        super();
    }

    private RabbitmqMessage<T> rabbitmqMessage;

    public MessageCorrelationData(String id, RabbitmqMessage<T> rabbitmqMessage) {
        super(id);
        this.rabbitmqMessage = rabbitmqMessage;
    }

    /**
     * 交换机名称
     */
    private String exchange;

    /**
     * 路由key
     */
    private String routingKey;

    /**
     * 重试次数
     */
    private int retryCount = 0;

    public RabbitmqMessage<T> getRabbitmqMessage() {
        return rabbitmqMessage;
    }

    public void setRabbitmqMessage(RabbitmqMessage<T> rabbitmqMessage) {
        this.rabbitmqMessage = rabbitmqMessage;
    }


}
