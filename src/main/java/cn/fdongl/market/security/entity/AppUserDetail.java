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

    private Integer id;
    private String username;
    private String password;
    private Integer status;
    private Timestamp createDate;
    private Timestamp modifyDate;

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


    public static AppUserDetail fromRequest(HttpServletRequest request) throws AuthenticationException {
        HttpSession session = request.getSession();
        AppUserDetail appUserDetail = (AppUserDetail) session.getAttribute("appUserDetail");
        if(appUserDetail==null){
            throw new AuthenticationException("need authorization");
        }
        return appUserDetail;
    }
}
