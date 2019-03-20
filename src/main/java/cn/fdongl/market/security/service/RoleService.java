package cn.fdongl.market.security.service;

import cn.fdongl.market.security.entity.Role;
import cn.fdongl.market.security.mapper.RolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RolesMapper rolesMapper;

    public Object list(){
        return rolesMapper.list();
    }

    public Object info(Integer roleId) throws Exception {
        Role role = rolesMapper.info(roleId);
        if(role==null){
            throw new Exception("无效的角色ID");
        }
        return role;
    }

    public Object rights(Integer roleId)throws Exception{
        return rolesMapper.rights(roleId);
    }

    public void add(Role role)throws Exception{
        try {
            if(rolesMapper.add(role)<=0){
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception("重复的name");
        }
    }

    public void modify(Role role) throws Exception {
        if(rolesMapper.modify(role)<=0){
            throw new Exception("无效的角色ID");
        }
    }

    public void disable(Integer roleId)throws Exception{
        if(rolesMapper.disable(roleId)<=0){
            throw new Exception("无效的角色ID");
        }
    }

    public void enable(Integer roleId)throws Exception{
        if(rolesMapper.enable(roleId)<=0){
            throw new Exception("无效的角色ID");
        }
    }

    public void setRights(Integer roleId,Integer[]rights,Integer userId)throws Exception{
        try {
            rolesMapper.setRights(rights, roleId,userId);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
