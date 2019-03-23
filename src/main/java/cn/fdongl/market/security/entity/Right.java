package cn.fdongl.market.security.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Right {

    Integer id;//权限ID

    @NotNull
    @Size(max=60)
    String label;//权限名称


    @Size(max=192)
    String description;//权限描述

    @Size(max=60)
    String menuName;//菜单名称

    @Size(max=115)
    String menuPath;//菜单路径

    Integer status;//权限状态，0表示启用，1表示禁用
    Integer father;//父权限ID
    Object children;//用于生成树

}
