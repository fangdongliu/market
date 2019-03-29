package cn.fdongl.market.province.entity;

import lombok.Data;

import java.sql.Date;

@Data
//包含查询到的账号的部分信息
public class AccountData {
    String username;//用户名
    String fullname;//用户全称
    Integer superior;//上级管理部门ID
    java.util.Date createTime;//创建日期
    Integer creator;//创建用户id
}