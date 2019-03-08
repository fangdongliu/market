package cn.fdongl.market.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")//指定接口的一级路径
public class ExampleController {

    @PostMapping("/hh")//指定接口的下一级路径，最终路径为 '/example/hh'
    public Object hhh(String hh){
        return "adw";
    }


}
