package cn.fdongl.market.security.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class AddUserInput {

    @Size(max=50)
    String prefix;//用户名前缀，用于登录
    @Size(max=50)
    String name;//用户全称前缀，可用于表示身份
    Integer parent;//直接父级用户ID
    @Max(1000)
    @Min(0)
    Integer count;//要创建的个数
    @Min(0)
    Integer startCount;//开始的下标，影响用户名与全称，prefix(startCount--count+startCount)

}
