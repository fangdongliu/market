package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class ProfNum {//职业供求人数表，共16条数据

    Integer tableId;//数据表id
    Integer leaderNeed;//单位负责人需求人数
    Integer leaderJobseek;//单位负责人求职人数
    Integer profTechNeed;//专业技术人员需求人数
    Integer profTechJobseek;//专业技术人员求职人数
    Integer staffRelaNeed;//办事人员和有关人员需求人数
    Integer staffRelaJobseek;//办事人员和有关人员求职人数
    Integer busiServNeed;//商业和服务人员需求人数
    Integer busiServJobseek;//商业和服务人员求职人数
    Integer prodNeed;//农林牧渔水利生产人员需求人数
    Integer prodJobseek;//农林牧渔水利生产人员求职人数
    Integer operNeed;//生产运输设备操作工需求人数
    Integer operJobseek;//生产运输设备操作工求职人数
    Integer otherNeed;//其他需求人数
    Integer otherJobseek;//其他求职人数
    Integer noRequJobseek;//无要求求职人数

    public ProfNum(UploadDataSet uploadDataSet){
        this.tableId=uploadDataSet.getTableId();
        this.leaderNeed=uploadDataSet.getLeaderNeed();
        this.leaderJobseek=uploadDataSet.getLeaderJobseek();
        this.profTechNeed=uploadDataSet.getProfTechNeed();
        this.profTechJobseek=uploadDataSet.getProfTechJobseek();
        this.staffRelaNeed=uploadDataSet.getStaffRelaNeed();
        this.staffRelaJobseek=uploadDataSet.getStaffRelaJobseek();
        this.busiServNeed=uploadDataSet.getBusiServNeed();
        this.busiServJobseek=uploadDataSet.getBusiServJobseek();
        this.prodNeed=uploadDataSet.getProdNeed();
        this.prodJobseek=uploadDataSet.getProdJobseek();
        this.operNeed=uploadDataSet.getOperNeed();
        this.operJobseek=uploadDataSet.getOperJobseek();
        this.otherNeed=uploadDataSet.getOtherNeedProfNum();
        this.otherJobseek=uploadDataSet.getOtherJobseek();
        this.noRequJobseek=uploadDataSet.getNoRequJobseek();
    }

    public ProfNum() {
    }
}
