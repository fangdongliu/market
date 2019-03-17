package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class ProfNum {
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
}
