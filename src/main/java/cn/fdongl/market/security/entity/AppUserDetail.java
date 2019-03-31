package cn.fdongl.market.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Collection;

@Data
public class AppUserDetail implements UserDetails {

    public static final long serialVersionUID = 1L;

    private Integer id;//用户id
    private String username;//用户登录名
    private String fullname;//用户全称
    private String password;//密码
    private Integer userType;//用户类型，1表示省，2表示市，3表示监测点
    private Integer status;//用户激活状态

    private Collection<? extends GrantedAuthority>authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status==0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status==0;
    }

}
