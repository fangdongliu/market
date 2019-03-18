package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class JobSeekerNum {

    Integer tableId;//数据表id
    Integer unempYouth;//新成长失业青年
    Integer graduate;//应届高校毕业生
    Integer empToUnemp;//就业转失业人员
    Integer othUnemp;//其他失业人员
    Integer emped;//在业人员
    Integer laidOff;//下岗职工
    Integer retiree;//退休人员
    Integer student;//在学人员
    Integer cityRural;//本市农村人员
    Integer fore;//外埠人员
}
