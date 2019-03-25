package cn.fdongl.market.province.entity;

import lombok.Data;

@Data
//这个类用于向前端发送用户信息
public class JobData {

    Integer uploadPeriodId;//调查期id
    Integer  needNum;//需求人数
    Integer seekNum;//求职人数
}