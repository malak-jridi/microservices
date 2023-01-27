package org.comAndDev.infoservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
@CrossOrigin(origins="http://localhost:3000")

public class InfoController {

    private static Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Value("${my.msg}")
    private String myMessage;

    @GetMapping
    public String sayHello(){
        logger.info("Running from info service ....");
        logger.info("Message : " + myMessage);
        return  "Hello from Info Service.";

    }
}