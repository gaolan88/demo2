package com.wfd360.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HelloController {
    /**
     * 获取模拟数据
     */
    @RequestMapping("/data")
    @ResponseBody
    public String data() {
        System.out.println("---------8888---------");
        return "hello world 8888888";
    }
}
