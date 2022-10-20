package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DemoService;

@RestController
@RequestMapping(value="/api/doService")
public class DemoServiceController {
    @Qualifier("one") // <-- DemoServiceOneImpl 的Bean名稱為 "one"
    @Autowired
    private DemoService demoService;

    @GetMapping("/")
    public void demo() {
        demoService.doService(); // 實際上是呼叫DemoServiceOneImpl.doService()
    }

}
