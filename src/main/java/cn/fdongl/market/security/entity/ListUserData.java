package cn.fdongl.market.security.entity;

import lombok.Data;

@Data
public class ListUserData {

    String username;//用户名
    String fullname;//用户全称
    Integer userType;//用户类型，1表示省，2表示市，3表示监测点
    Integer id;//用户ID
    Integer status;//用户备案状态，1表示有效，0表示无效
    Integer deleteFlag;//1表示删除
    Integer father;//父级ID
    Object child;

}
