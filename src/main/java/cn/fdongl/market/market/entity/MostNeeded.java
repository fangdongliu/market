package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class MostNeeded {//需求前十职业表，共41条数据

    Integer tableId;//数据表id
    String mostProf1Name;//最需要排名第一的职业名称
    Integer mostProf1Num;//最需要排名第一的职业代码
    Integer mostProf1Need;//最需要排名第一的职业需求人数
    Integer mostProf1Jobseek;//最需要排名第一的职业求职人数
    String mostProf2Name;//最需要排名第二的职业名称
    Integer mostProf2Num;//最需要排名第二的职业代码
    Integer mostProf2Need;//最需要排名第二的职业需求人数
    Integer mostProf2Jobseek;//最需要排名第二的职业求职人数
    String mostProf3Name;//最需要排名第三的职业名称
    Integer mostProf3Num;//最需要排名第三的职业代码
    Integer mostProf3Need;//最需要排名第三的职业需求人数
    Integer mostProf3Jobseek;//最需要排名第三的职业求职人数
    String mostProf4Name;//最需要排名第四的职业名称
    Integer mostProf4Num;//最需要排名第四的职业代码
    Integer mostProf4Need;//最需要排名第四的职业需求人数
    Integer mostProf4Jobseek;//最需要排名第四的职业求职人数
    String mostProf5Name;//最需要排名第五的职业名称
    Integer mostProf5Num;//最需要排名第五的职业代码
    Integer mostProf5Need;//最需要排名第五的职业需求人数
    Integer mostProf5Jobseek;//最需要排名第五的职业求职人数
    String mostProf6Name;//最需要排名第六的职业名称
    Integer mostProf6Num;//最需要排名第六的职业代码
    Integer mostProf6Need;//最需要排名第六的职业需求人数
    Integer mostProf6Jobseek;//最需要排名第六的职业求职人数
    String mostProf7Name;//最需要排名第七的职业名称
    Integer mostProf7Num;//最需要排名第七的职业代码
    Integer mostProf7Need;//最需要排名第七的职业需求人数
    Integer mostProf7Jobseek;//最需要排名第七的职业求职人数
    String mostProf8Name;//最需要排名第八的职业名称
    Integer mostProf8Num;//最需要排名第八的职业代码
    Integer mostProf8Need;//最需要排名第八的职业需求人数
    Integer mostProf8Jobseek;//最需要排名第八的职业求职人数
    String mostProf9Name;//最需要排名第九的职业名称
    Integer mostProf9Num;//最需要排名第九的职业代码
    Integer mostProf9Need;//最需要排名第九的职业需求人数
    Integer mostProf9Jobseek;//最需要排名第九的职业求职人数
    String mostProf10Name;//最需要排名第十的职业名称
    Integer mostProf10Num;//最需要排名第十的职业代码
    Integer mostProf10Need;//最需要排名第十的职业需求人数
    Integer mostProf10Jobseek;//最需要排名第十的职业求职人数

    public MostNeeded(UploadDataSet uploadDataSet){
        this.tableId = uploadDataSet.getTableId();
        this.mostProf1Name = uploadDataSet.getMostProf1Name();
        this.mostProf1Num = uploadDataSet.getMostProf1Num();
        this.mostProf1Need = uploadDataSet.getMostProf1Need();
        this.mostProf1Jobseek = uploadDataSet.getMostProf1Jobseek();
        this.mostProf2Name = uploadDataSet.getMostProf2Name();
        this.mostProf2Num = uploadDataSet.getMostProf2Num();
        this.mostProf2Need = uploadDataSet.getMostProf2Need();
        this.mostProf2Jobseek = uploadDataSet.getMostProf2Jobseek();
        this.mostProf3Name = uploadDataSet.getMostProf3Name();
        this.mostProf3Num = uploadDataSet.getMostProf3Num();
        this.mostProf3Need = uploadDataSet.getMostProf3Need();
        this.mostProf3Jobseek = uploadDataSet.getMostProf3Jobseek();
        this.mostProf4Name = uploadDataSet.getMostProf4Name();
        this.mostProf4Num = uploadDataSet.getMostProf4Num();
        this.mostProf4Need = uploadDataSet.getMostProf4Need();
        this.mostProf4Jobseek = uploadDataSet.getMostProf4Jobseek();
        this.mostProf5Name = uploadDataSet.getMostProf5Name();
        this.mostProf5Num = uploadDataSet.getMostProf5Num();
        this.mostProf5Need = uploadDataSet.getMostProf5Need();
        this.mostProf5Jobseek = uploadDataSet.getMostProf5Jobseek();
        this.mostProf6Name = uploadDataSet.getMostProf6Name();
        this.mostProf6Num = uploadDataSet.getMostProf6Num();
        this.mostProf6Need = uploadDataSet.getMostProf6Need();
        this.mostProf6Jobseek = uploadDataSet.getMostProf6Jobseek();
        this.mostProf7Name = uploadDataSet.getMostProf7Name();
        this.mostProf7Num = uploadDataSet.getMostProf7Num();
        this.mostProf7Need = uploadDataSet.getMostProf7Need();
        this.mostProf7Jobseek = uploadDataSet.getMostProf7Jobseek();
        this.mostProf8Name = uploadDataSet.getMostProf8Name();
        this.mostProf8Num = uploadDataSet.getMostProf8Num();
        this.mostProf8Need = uploadDataSet.getMostProf8Need();
        this.mostProf8Jobseek = uploadDataSet.getMostProf8Jobseek();
        this.mostProf9Name = uploadDataSet.getMostProf9Name();
        this.mostProf9Num = uploadDataSet.getMostProf9Num();
        this.mostProf9Need = uploadDataSet.getMostProf9Need();
        this.mostProf9Jobseek = uploadDataSet.getMostProf9Jobseek();
        this.mostProf10Name = uploadDataSet.getMostProf10Name();
        this.mostProf10Num = uploadDataSet.getMostProf10Num();
        this.mostProf10Need = uploadDataSet.getMostProf10Need();
        this.mostProf10Jobseek = uploadDataSet.getMostProf10Jobseek();
    }

    public MostNeeded() {
    }
}
