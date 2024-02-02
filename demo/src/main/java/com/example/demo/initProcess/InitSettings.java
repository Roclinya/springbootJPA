package com.example.demo.initProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
public class InitSettings {
    private static Logger logger = LoggerFactory.getLogger(InitSettings.class);
    @PostConstruct
    @Transactional
    public void init() {
        String logMsgStart = "============Start InitSettings =======================\n"
                + "        =====================================================";
        logger.debug(logMsgStart);
    }

    public InitSettings(){
        logger.info("InitSettings 建立");
    }
}
