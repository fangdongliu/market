package cn.fdongl.market.example.controller;

import cn.fdongl.market.example.service.TestService;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.util.ControllerBase;
import javafx.util.Pair;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/example")//指定接口的一级路径
public class ExampleController extends ControllerBase {

    @Autowired
    TestService testService;

    @RequestMapping("/gg")
    public Object ff(@RequestParam("ss[]") Integer[] ss,@RequestParam("gg[]")Integer[]gg){
        return new Pair<>(ss,gg);
    }

    @GetMapping("/hh")//指定接口的下一级路径，最终路径为 '/example/hh'
    public Object hhh(String hh,Integer dd){
        try {
            testService.hh();
        } catch (Exception e) {
            System.out.println("1");
        }
        return success("1234");
    }
    @RolesAllowed("READ_EX")    //描述访问接口所需权限
    @RequestMapping("/ad")
    public Object hha(AppUserDetail userDetail){//通过在参数中加入AppUserDetail以获取用户信息
        return fail(userDetail.getUsername());
    }
}
