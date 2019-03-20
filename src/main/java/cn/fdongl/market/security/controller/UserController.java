package cn.fdongl.market.security.controller;

import cn.fdongl.market.security.entity.AddUserInput;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.security.service.UserService;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController extends ControllerBase {

    @Autowired
    UserService userService;

    @RequestMapping("enable")
    public Object enable(@RequestParam Integer userId) throws Exception {
        userService.enable(userId);
        return success();
    }

    @RequestMapping("disable")
    public Object disable(@RequestParam Integer userId) throws Exception {
        userService.disable(userId);
        return success();
    }

    @RequestMapping("addUsers")
    public Object addUsers(
            AppUserDetail userDetail,
            @Valid AddUserInput input
            ) throws Exception {
        userService.addUsers(userDetail.getId(),
                input.getParent(),
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


}
