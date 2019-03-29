package cn.fdongl.market.security.service;

import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.security.entity.UserData;
import cn.fdongl.market.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserData userData = userMapper.getUserData(username);

        if(userData==null){
            throw new UsernameNotFoundException(String.format("未发现目标用户：%s",username));
        }

        AppUserDetail detail = new AppUserDetail();
        detail.setId(userData.getId());
        detail.setUsername(username);
        detail.setPassword(userData.getPassword());
        detail.setStatus(0);
        detail.setUserType(userData.getUserType());

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        for(String s:userData.getRights()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+s));
        }

        detail.setAuthorities(authorities);
        return detail;
    }
}
