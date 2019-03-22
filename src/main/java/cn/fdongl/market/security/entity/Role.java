package cn.fdongl.market.security.entity;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Role {

    Integer id;//角色ID
    @Size(max=60)
    String name;//角色名称
    @Size(max=192)
    String description;//角色描述
    Integer status;//角色状态，0启用，1禁用
    List<Right>rights;//角色拥有的权限

}
