package cn.fdongl.market.example.controller;

import cn.fdongl.market.example.service.TestService;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.util.ControllerBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/example")//指定接口的一级路径
public class ExampleController extends ControllerBase {

    @Autowired
    TestService testService;

    @RequestMapping("aaaa")
    public Object aa(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "asf";
    }
    @ApiOperation(value = "获取aaa",notes = "")
    @GetMapping("/hh")//指定接口的下一级路径，最终路径为 '/example/hh'
    public Object hhh(String hh,Integer dd){
        try {

            testService.hh();
        } catch (Exception e) {
            System.out.println("1");
        }
        return success("1234");
    }
    @RolesAllowed("USER")    //描述访问接口所需权限
    @RequestMapping("/ad")
    public Object hha(AppUserDetail userDetail){//通过在参数中加入AppUserDetail以获取用户信息
        return fail(userDetail.getUsername());
    }
}
