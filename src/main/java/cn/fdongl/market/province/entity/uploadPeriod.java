package cn.fdongl.market.province.entity;

import lombok.Data;

import java.sql.Date;

@Data
//这个类用于接收从前端传来的json文件
public class uploadPeriod {
    Integer uploadPeriodId;//时限id
    String  startDate;//开始日期,sql.date
    String endDate;//结束日期,sql.date
    String creatTime;//创建日期,util.date
    Integer creator;//创建用户id
    String reviseTime;//最后修改日期,util.date
    Integer reviser;//最后修改用户id
    Integer deleteFlag;//删除标志位，弃用

}
