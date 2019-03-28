package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class SexNum {//性别供求人数表，共6条数据

    Integer tableId;//数据表id
    Integer maleNeed;//男性需求人数
    Integer maleJobseek;//男性求职人数
    Integer femaleNeed;//女性需求人数
    Integer femaleJobseek;//女性求职人数
    Integer noRequNeed;//无要求需求人数

    public SexNum(UploadDataSet uploadDataSet) {
        this.tableId = uploadDataSet.getTableId();
        this.maleNeed = uploadDataSet.getMaleNeed();
        this.maleJobseek = uploadDataSet.getMaleJobseek();
        this.femaleNeed = uploadDataSet.getFemaleNeed();
        this.femaleJobseek = uploadDataSet.getFemaleJobseek();
        this.noRequNeed = uploadDataSet.getNoRequNeedSexNum();
    }

    public SexNum() {
    }
}
