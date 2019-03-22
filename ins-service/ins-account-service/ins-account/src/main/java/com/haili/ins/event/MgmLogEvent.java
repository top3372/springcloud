package com.haili.ins.event;

import com.haili.ins.dto.MgmLogParam;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @Author: leon
 * @Date: 2019/3/14 16:48
 * @Version 1.0
 */
@Getter
public class MgmLogEvent extends ApplicationEvent {

    private List<MgmLogParam> mgmLogParamList;

    public MgmLogEvent(Object source,List<MgmLogParam> mgmLogParamList ){
        super(source);
        this.mgmLogParamList = mgmLogParamList;
    }

}
