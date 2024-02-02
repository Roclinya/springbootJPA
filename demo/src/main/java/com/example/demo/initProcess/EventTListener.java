package com.example.demo.initProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EventTListener {
    private static Logger logger = LoggerFactory.getLogger(EventTListener.class);
    public EventTListener(){
        logger.info("EventTListener監聽器建立");
    }

}
