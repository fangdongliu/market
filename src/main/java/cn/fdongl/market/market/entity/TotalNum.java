package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class TotalNum {//供求总体人数表，共3条数据

    Integer tableId;//数据表id
    Integer needPopu;//需求人数
    Integer jobseekPopu;//求职人数
}
