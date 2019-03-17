package cn.fdongl.market.security.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserData {

    private Integer id;
    private String username;
    private String password;
    private List<String> rights;

}
