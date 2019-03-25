package cn.fdongl.market.province.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UploadPeriod {//调查期，共9条数据，多了两条日期的String

    Integer uploadPeriodId;//时限id
    java.sql.Date startDate;//开始日期
    String  startDateString;//开始日期,sql.date
    java.sql.Date endDate;//结束日期
    String endDateString;//结束日期,sql.date
    Date creatTime;//创建日期,util.date
    Integer creator;//创建用户id
    Date reviseTime;//最后修改日期,util.date
    Integer reviser;//最后修改用户id
}
