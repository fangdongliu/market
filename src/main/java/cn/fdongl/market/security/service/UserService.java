package cn.fdongl.market.security.service;

import cn.fdongl.market.security.entity.Right;
import cn.fdongl.market.security.entity.UsernameAndFullname;
import cn.fdongl.market.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Object list(Integer userId)throws Exception{
        return userMapper.list(userId);
    }

    public void enable(Integer userId)throws Exception{
        if(userId==1){
            throw new Exception("无法修改管理员");
        }
        if(userMapper.enable(userId)<=0){
            throw new Exception("无效的ID");
        }
    }

    public void disable(Integer userId)throws Exception{
        if(userId==1){
            throw new Exception("无法修改管理员");
        }
        if(userMapper.disable(userId)<=0){
            throw new Exception("无效的ID");
        }
    }

    public Object userInfo(Integer userId)throws Exception{
        return userMapper.roleInfo(userId);
    }

    public void changeRoles(Integer currentUser,Integer userId,Integer[]roles)throws Exception{
        userMapper.setRoles(roles, currentUser, userId);
    }

    public Object getMenu(Integer userId){

        Map<Integer, Right>menu=null;

        if(userId==1){
            menu = userMapper.getSysMenu();
        }else {
            menu = userMapper.getMenu(userId);
        }
        for (Iterator<Map.Entry<Integer, Right>> it = menu.entrySet().iterator(); it.hasNext();){
            Map.Entry<Integer, Right> item = it.next();
            if(item.getValue().getFather()!=null){
                Right r = menu.get(item.getValue().getFather());
                if(r!=null){
                    r.setChild(item.getValue());
                }
                it.remove();
            }
        }

        return menu.values().toArray();
    }

    public void addUsers(Integer currentUser,Integer parent,String prefix,String name,Integer startCount,Integer count) throws Exception {
        List<UsernameAndFullname>array = new ArrayList<>();

        String password = bCryptPasswordEncoder.encode("123456");

        for(int i=0;i<count;i++){
            array.add(new UsernameAndFullname(prefix+(startCount+i),name+(startCount+i)));
        }

        try {
            if (userMapper.addUsers(array, parent, password, currentUser) != count) {
                throw new Exception("创建用户时出错");
            }
        }
        catch (Exception e){
            throw new Exception("重复的用户名");
        }
    }

}
