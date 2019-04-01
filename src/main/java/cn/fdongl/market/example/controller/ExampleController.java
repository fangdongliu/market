package cn.fdongl.market.example.controller;

import cn.fdongl.market.example.service.TestService;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.util.ControllerBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@RestController
@RequestMapping("/example")//指定接口的一级路径
public class ExampleController extends ControllerBase {

    final
    TestService testService;



    int a = 0;

    @Autowired
    public ExampleController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping("aaaa")
    public Object aa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "asf";
    }
    @ApiOperation(value = "获取aaa",notes = "")
    @GetMapping("/hh")//指定接口的下一级路径，最终路径为 '/example/hh'
    public Object hhh(String hh,Integer dd) throws Exception {

     //   throw new Exception();
           log.info(1);
     //   Thread.sleep(3000);
//        try {
//            testService.hh();
//        } catch (Exception e) {
//            System.out.println("1");
//        }
       // log.info(a);
        return success(System.identityHashCode(result.get()));
    }
    @RolesAllowed("USER")    //描述访问接口所需权限
    @RequestMapping("/ad")
    public Object hha(AppUserDetail userDetail){//通过在参数中加入AppUserDetail以获取用户信息
        return fail(userDetail.getUsername());
    }
}
