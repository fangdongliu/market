package cn.fdongl.market.security.controller;

import cn.fdongl.market.example.mapper.ExampleMapper;
import cn.fdongl.market.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

    @Autowired
    UserMapper mapper;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }

    @RequestMapping("/data")
    @ResponseBody
    public Object cas(String a,Integer b){

        return mapper.test();
        //mapper.getListOne();
    }

}
