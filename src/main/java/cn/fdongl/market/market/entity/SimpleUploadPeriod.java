package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class SimpleUploadPeriod {//简单的调查期，只包含id和开始结束时间

    Integer uploadPeriodId;//上传期id
    java.sql.Date startDate;//开始日期
    java.sql.Date endDate;//结束日期
}
