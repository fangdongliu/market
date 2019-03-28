package cn.fdongl.market.security.entity;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserPageQuery {

    @NotNull
    @Min(1)
    Integer page;
    @NotNull
    @Min(1)
    Integer pageSize;
    Integer userType;
    String username;
    String fullname;

}
