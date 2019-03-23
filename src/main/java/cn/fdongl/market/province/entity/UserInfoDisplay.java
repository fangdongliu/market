package cn.fdongl.market.province.entity;

import lombok.Data;

@Data
//这个类用于向前端发送用户信息
public class UserInfoDisplay {
    Integer userId;//用户id
    String  username;//用户名
    String fullname;//用户全称
}

