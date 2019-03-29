package cn.fdongl.market.security.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserData {

    private Integer id;//用户ID
    private String username;//用户名
    private String password;//密码
    private List<String> rights;//用户权限
    private Integer userType;

}
