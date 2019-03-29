package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class AgeNum {//年龄供求人数表，共10条数据

    Integer tableId;//数据表id
    Integer sixteenTwentyfourNeed;//16-24岁需求人数
    Integer sixteenTwentyfourJobseek;//16-24岁求职人数
    Integer twentyfiveThirtyfourNeed;//25-34岁需求人数
    Integer twentyfiveThirtyfourJobseek;//25-34岁求职人数
    Integer thirtyfiveFortyfourNeed;//35-44岁需求人数
    Integer thirtyfiveFortyfourJobseek;//35-44岁求职人数
    Integer overFortyfourNeed;//45岁以上需求人数
    Integer overFortyfourJobseek;//45岁以上求职人数
    Integer noRequNeed;//无要求需求人数

    public AgeNum(UploadDataSet uploadDataSet){
        this.tableId = uploadDataSet.getTableId();
        this.sixteenTwentyfourNeed = uploadDataSet.getSixteenTwentyfourNeed();
        this.sixteenTwentyfourJobseek = uploadDataSet.getSixteenTwentyfourJobseek();
        this.twentyfiveThirtyfourNeed = uploadDataSet.getTwentyfiveThirtyfourNeed();
        this.twentyfiveThirtyfourJobseek = uploadDataSet.getTwentyfiveThirtyfourJobseek();
        this.thirtyfiveFortyfourNeed = uploadDataSet.getThirtyfiveFortyfourNeed();
        this.thirtyfiveFortyfourJobseek = uploadDataSet.getThirtyfiveFortyfourJobseek();
        this.overFortyfourNeed = uploadDataSet.getOverFortyfourNeed();
        this.overFortyfourJobseek = uploadDataSet.getOverFortyfourJobseek();
        this.noRequNeed = uploadDataSet.getNoRequNeedAgeNum();
    }

    public AgeNum() {
    }
}
