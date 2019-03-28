package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class TotalNum {//供求总体人数表，共3条数据

    Integer tableId;//数据表id
    Integer needPopu;//需求人数
    Integer jobseekPopu;//求职人数

    public TotalNum(UploadDataSet uploadDataSet){
        this.tableId = uploadDataSet.getTableId();
        this.needPopu = uploadDataSet.getNeedPopu();
        this.jobseekPopu = uploadDataSet.getJobseekPopu();
    }

    public TotalNum() {
    }
}
