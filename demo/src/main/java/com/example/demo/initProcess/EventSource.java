package com.example.demo.initProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("eventTListener")
public class EventSource {
    private static Logger logger = LoggerFactory.getLogger(EventSource.class);
    public EventSource(){
        logger.info("EventSource 事件源建立");
    }

}
