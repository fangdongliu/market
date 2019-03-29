package cn.fdongl.market.security.controller;

import cn.fdongl.market.security.entity.AddUserInput;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.security.entity.UserPageQuery;
import cn.fdongl.market.security.service.UserService;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController extends ControllerBase {

    @Autowired
    UserService userService;


    @Autowired
    public PasswordEncoder passwordEncoder;

    @RequestMapping("list")
    public Object list(AppUserDetail userDetail) throws Exception {
        return success(userService.list(userDetail.getId()));
    }

    @RequestMapping("enable")
    public Object enable(@RequestParam Integer userId) throws Exception {
        userService.enable(userId);
        return success();
    }

    @RequestMapping("query")
    public Object query(@Valid UserPageQuery input) throws Exception {
        return success(userService.query(
                (input.getPage()-1)*input.getPageSize(),
                input.getPageSize(),
                input.getUserType(),
                input.getUsername(),
                input.getFullname()));
    }

    @RequestMapping("disable")
    public Object disable(@RequestParam Integer userId) throws Exception {
        userService.disable(userId);
        return success();
    }

    @RolesAllowed("USER")
    @RequestMapping("userType")
    public Object userType(AppUserDetail appUserDetail) throws Exception {
        return appUserDetail.getUserType();
    }

    @RequestMapping("addUsers")
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

    @RequestMapping("role/info")
    public Object roleInfo(@RequestParam Integer userId) throws Exception {
        return success(userService.userInfo(userId));
    }

    @RequestMapping("role/change")
    public Object roleChange(
            @RequestParam Integer userId,
            @RequestParam("roles[]")Integer[]roles,
            AppUserDetail userDetail
    ) throws Exception {
        userService.changeRoles(userDetail.getId(),userId,roles);
        return success();
    }

    @RequestMapping("menu")
    public Object menu(AppUserDetail userDetail) throws Exception {
        return success(userService.getMenu(userDetail.getId()));
    }

    @RequestMapping("updatePassword")
    public Object updatePassword(AppUserDetail userDetail,@RequestParam String oldPassword,@RequestParam String newPassword) throws Exception {
        if(passwordEncoder.encode(oldPassword).equals(userDetail.getPassword())) {
            userService.updatePassword(newPassword, userDetail.getId());
            return success();
        }
        return fail(null,"密码校验失败");
    }

}
