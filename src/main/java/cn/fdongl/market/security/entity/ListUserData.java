package cn.fdongl.market.security.entity;

import lombok.Data;

@Data
public class ListUserData {

    String username;//用户名
    String fullname;//用户全称
    Integer id;//用户ID
    Integer status;//用户状态，0表示有效，1表示无效
    Integer father;//父级ID

}
