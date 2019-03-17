package cn.fdongl.market.example.controller;

import cn.fdongl.market.example.service.TestService;
import cn.fdongl.market.security.entity.AppUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/example")//指定接口的一级路径
public class ExampleController {

    @Autowired
    TestService testService;

    @GetMapping("/hh")//指定接口的下一级路径，最终路径为 '/example/hh'
    public Object hhh(String hh){
        try {
            testService.hh();
        } catch (Exception e) {
            System.out.println("1");
        //    e.printStackTrace();
        }
        return "adw";
    }
    @RolesAllowed("READ_EX")
    @RequestMapping("/ad")
    public Object hha(AppUserDetail userDetail){
        return userDetail.getUsername();
    }


}
