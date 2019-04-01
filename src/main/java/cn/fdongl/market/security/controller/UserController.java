package cn.fdongl.market.security.controller;

import cn.fdongl.market.security.entity.AddUserInput;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.security.entity.UserPageQuery;
import cn.fdongl.market.security.service.UserService;
import cn.fdongl.market.util.ControllerBase;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends ControllerBase {

    @Autowired
    UserService userService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @PostMapping("list")
    public Object list(AppUserDetail userDetail) throws Exception {
        return success(userService.list(userDetail.getId()));
    }

    @PostMapping("enable")
    public Object enable(@RequestParam Integer userId) throws Exception {
        userService.enable(userId);
        return success();
    }

    @PostMapping("query")
    public Object query(@Valid UserPageQuery input) throws Exception {
        return success(userService.query(
                (input.getPage()-1)*input.getPageSize(),
                input.getPageSize(),
                input.getUserType(),
                input.getUsername(),
                input.getFullname()));
    }

    @PostMapping("disable")
    public Object disable(@RequestParam Integer userId) throws Exception {
        userService.disable(userId);
        return success();
    }

    @RolesAllowed("USER")
    @PostMapping("userType")
    public Object userType(AppUserDetail appUserDetail) throws Exception {
        return appUserDetail.getUserType();
    }

    @RolesAllowed("USER")
    @PostMapping("userInfo")
    public Object userInfo(AppUserDetail userDetail,Integer userId) throws Exception {
        if(userId!=null){
            return userService.userInfo(userId);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("userType",userDetail.getUserType());
        map.put("userId",userDetail.getId());
        map.put("username",userDetail.getUsername());
        map.put("fullname",userDetail.getFullname());
        return map;
    }

    @PostMapping("child")
    public Object child(AppUserDetail userDetail, Integer father) throws Exception {
        if(father==null){
            return success(userService.child(userDetail.getId()));
        }else{
            return success(userService.child(father));
        }
    }

    @PostMapping("addUsers")
    public Object addUsers(
            AppUserDetail userDetail,
            @Valid AddUserInput input
            ) throws Exception {
        if(userDetail.getId()==1){
            input.setUserType(1);
        }else if(input.getParent()==null){
            input.setUserType(2);
            input.setParent(userDetail.getId());
        }else{
            input.setUserType(3);
        }
        userService.addUsers(userDetail.getId(),
                input.getParent(),
                input.getUserType(),
                input.getPrefix(),
                input.getName(),
                input.getStartCount(),
                input.getCount());
        return success();
    }

    @PostMapping("role/info")
    public Object roleInfo(@RequestParam Integer userId) throws Exception {
        return success(userService.userInfo(userId));
    }

    @PostMapping("role/change")
    public Object roleChange(
            @RequestParam Integer userId,
            @RequestParam("roles[]")Integer[]roles,
            AppUserDetail userDetail
    ) throws Exception {
        userService.changeRoles(userDetail.getId(),userId,roles);
        return success();
    }

    @PostMapping("menu")
    public Object menu(AppUserDetail userDetail) throws Exception {
        return success(userService.getMenu(userDetail.getId()));
    }

    @PostMapping("updatePassword")
    public Object updatePassword(AppUserDetail userDetail,@RequestParam String oldPassword,@RequestParam String newPassword) throws Exception {
        if(passwordEncoder.encode(oldPassword).equals(userDetail.getPassword())) {
            userService.updatePassword(newPassword, userDetail.getId());
            return success();
        }
        return fail(null,"密码校验失败");
    }

}
