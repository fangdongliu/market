package cn.fdongl.market.security.controller;

import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.security.entity.Role;
import cn.fdongl.market.security.service.RoleService;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("role")
public class RoleController extends ControllerBase {

    @Autowired
    RoleService roleService;

    @RequestMapping("list")
    public Object list(){
        return success(roleService.list());
    }

    @RequestMapping("info")
    public Object info(@RequestParam Integer roleId) throws Exception {
        return success(roleService.info(roleId));
    }

    @RequestMapping("rights")
    public Object rights(@RequestParam Integer roleId) throws Exception {
        return success(roleService.rights(roleId));
    }

    @RequestMapping("add")
    public Object add(@Valid Role role) throws Exception {
        roleService.add(role);
        return success();
    }

    @RequestMapping("modify")
    public Object modify(@Valid Role role) throws Exception {
        roleService.modify(role);
        return success();
    }

    @RequestMapping("enable")
    public Object enable(@RequestParam Integer roleId) throws Exception {
        roleService.enable(roleId);
        return success();
    }

    @RequestMapping("disable")
    public Object disable(@RequestParam Integer roleId) throws Exception {
        roleService.disable(roleId);
        return success();
    }

    @RequestMapping("setRights")
    public Object setRights(@RequestParam Integer roleId,
                            @RequestParam("rights[]")Integer[]rights,
                            AppUserDetail userDetail) throws Exception {
        roleService.setRights(roleId, rights,userDetail.getId());
        return success();
    }

}
