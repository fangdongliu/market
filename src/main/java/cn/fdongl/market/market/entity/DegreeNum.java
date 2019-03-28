package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class DegreeNum {//文化程度供求人数表，共12条数据

    Integer tableId;//数据表id
    Integer belowJuniHighSchoNeed;//初中及以下需求人数
    Integer belowJuniHighSchoJobseek;//初中及以下求职人数
    Integer highSchoNeed;//高中需求人数
    Integer highSchoJobseek;//高中求职人数
    Integer otherHighSchoNeed;//职高技校中专需求人数
    Integer otherHighSchoJobseek;//职高技校中专求职人数
    Integer juniCollNeed;//大专需求人数
    Integer juniCollJobseek;//大专求职人数
    Integer univNeed;//大学需求人数
    Integer univJobseek;//大学求职人数
    Integer noRequNeed;//无要求需求人数

    public DegreeNum(UploadDataSet uploadDataSet) {
        this.tableId = uploadDataSet.getTableId();
        this.belowJuniHighSchoNeed = uploadDataSet.getBelowJuniHighSchoNeed();
        this.belowJuniHighSchoJobseek = uploadDataSet.getBelowJuniHighSchoJobseek();
        this.highSchoNeed = uploadDataSet.getHighSchoNeed();
        this.highSchoJobseek = uploadDataSet.getHighSchoJobseek();
        this.otherHighSchoNeed = uploadDataSet.getOtherHighSchoNeed();
        this.otherHighSchoJobseek = uploadDataSet.getOtherHighSchoJobseek();
        this.juniCollNeed = uploadDataSet.getJuniCollNeed();
        this.juniCollJobseek = uploadDataSet.getJuniCollJobseek();
        this.univNeed = uploadDataSet.getUnivNeed();
        this.univJobseek = uploadDataSet.getUnivJobseek();
        this.noRequNeed = uploadDataSet.getNoRequNeedDegreeNum();
    }

    public DegreeNum() {
    }
}
