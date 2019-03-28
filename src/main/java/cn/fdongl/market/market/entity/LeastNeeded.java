package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class LeastNeeded {//饱和前十职业表，共41条数据

    Integer tableId;//数据表id
    String leastProf1Name;//最饱和排名第一的职业名称
    Integer leastProf1Num;//最饱和排名第一的职业代码
    Integer leastProf1Need;//最饱和排名第一的职业需求人数
    Integer leastProf1Jobseek;//最饱和排名第一的职业求职人数
    String leastProf2Name;//最饱和排名第二的职业名称
    Integer leastProf2Num;//最饱和排名第二的职业代码
    Integer leastProf2Need;//最饱和排名第二的职业需求人数
    Integer leastProf2Jobseek;//最饱和排名第二的职业求职人数
    String leastProf3Name;//最饱和排名第三的职业名称
    Integer leastProf3Num;//最饱和排名第三的职业代码
    Integer leastProf3Need;//最饱和排名第三的职业需求人数
    Integer leastProf3Jobseek;//最饱和排名第三的职业求职人数
    String leastProf4Name;//最饱和排名第四的职业名称
    Integer leastProf4Num;//最饱和排名第四的职业代码
    Integer leastProf4Need;//最饱和排名第四的职业需求人数
    Integer leastProf4Jobseek;//最饱和排名第四的职业求职人数
    String leastProf5Name;//最饱和排名第五的职业名称
    Integer leastProf5Num;//最饱和排名第五的职业代码
    Integer leastProf5Need;//最饱和排名第五的职业需求人数
    Integer leastProf5Jobseek;//最饱和排名第五的职业求职人数
    String leastProf6Name;//最饱和排名第六的职业名称
    Integer leastProf6Num;//最饱和排名第六的职业代码
    Integer leastProf6Need;//最饱和排名第六的职业需求人数
    Integer leastProf6Jobseek;//最饱和排名第六的职业求职人数
    String leastProf7Name;//最饱和排名第七的职业名称
    Integer leastProf7Num;//最饱和排名第七的职业代码
    Integer leastProf7Need;//最饱和排名第七的职业需求人数
    Integer leastProf7Jobseek;//最饱和排名第七的职业求职人数
    String leastProf8Name;//最饱和排名第八的职业名称
    Integer leastProf8Num;//最饱和排名第八的职业代码
    Integer leastProf8Need;//最饱和排名第八的职业需求人数
    Integer leastProf8Jobseek;//最饱和排名第八的职业求职人数
    String leastProf9Name;//最饱和排名第九的职业名称
    Integer leastProf9Num;//最饱和排名第九的职业代码
    Integer leastProf9Need;//最饱和排名第九的职业需求人数
    Integer leastProf9Jobseek;//最饱和排名第九的职业求职人数
    String leastProf10Name;//最饱和排名第十的职业名称
    Integer leastProf10Num;//最饱和排名第十的职业代码
    Integer leastProf10Need;//最饱和排名第十的职业需求人数
    Integer leastProf10Jobseek;//最饱和排名第十的职业求职人数

    public LeastNeeded(UploadDataSet uploadDataSet) {
        this.tableId = uploadDataSet.getTableId();
        this.leastProf1Name = uploadDataSet.getLeastProf1Name();
        this.leastProf1Num = uploadDataSet.getLeastProf1Num();
        this.leastProf1Need = uploadDataSet.getLeastProf1Need();
        this.leastProf1Jobseek = uploadDataSet.getLeastProf1Jobseek();
        this.leastProf2Name = uploadDataSet.getLeastProf2Name();
        this.leastProf2Num = uploadDataSet.getLeastProf2Num();
        this.leastProf2Need = uploadDataSet.getLeastProf2Need();
        this.leastProf2Jobseek = uploadDataSet.getLeastProf2Jobseek();
        this.leastProf3Name = uploadDataSet.getLeastProf3Name();
        this.leastProf3Num = uploadDataSet.getLeastProf3Num();
        this.leastProf3Need = uploadDataSet.getLeastProf3Need();
        this.leastProf3Jobseek = uploadDataSet.getLeastProf3Jobseek();
        this.leastProf4Name = uploadDataSet.getLeastProf4Name();
        this.leastProf4Num = uploadDataSet.getLeastProf4Num();
        this.leastProf4Need = uploadDataSet.getLeastProf4Need();
        this.leastProf4Jobseek = uploadDataSet.getLeastProf4Jobseek();
        this.leastProf5Name = uploadDataSet.getLeastProf5Name();
        this.leastProf5Num = uploadDataSet.getLeastProf5Num();
        this.leastProf5Need = uploadDataSet.getLeastProf5Need();
        this.leastProf5Jobseek = uploadDataSet.getLeastProf5Jobseek();
        this.leastProf6Name = uploadDataSet.getLeastProf6Name();
        this.leastProf6Num = uploadDataSet.getLeastProf6Num();
        this.leastProf6Need = uploadDataSet.getLeastProf6Need();
        this.leastProf6Jobseek = uploadDataSet.getLeastProf6Jobseek();
        this.leastProf7Name = uploadDataSet.getLeastProf7Name();
        this.leastProf7Num = uploadDataSet.getLeastProf7Num();
        this.leastProf7Need = uploadDataSet.getLeastProf7Need();
        this.leastProf7Jobseek = uploadDataSet.getLeastProf7Jobseek();
        this.leastProf8Name = uploadDataSet.getLeastProf8Name();
        this.leastProf8Num = uploadDataSet.getLeastProf8Num();
        this.leastProf8Need = uploadDataSet.getLeastProf8Need();
        this.leastProf8Jobseek = uploadDataSet.getLeastProf8Jobseek();
        this.leastProf9Name = uploadDataSet.getLeastProf9Name();
        this.leastProf9Num = uploadDataSet.getLeastProf9Num();
        this.leastProf9Need = uploadDataSet.getLeastProf9Need();
        this.leastProf9Jobseek = uploadDataSet.getLeastProf9Jobseek();
        this.leastProf10Name = uploadDataSet.getLeastProf10Name();
        this.leastProf10Num = uploadDataSet.getLeastProf10Num();
        this.leastProf10Need = uploadDataSet.getLeastProf10Need();
        this.leastProf10Jobseek = uploadDataSet.getLeastProf10Jobseek();
    }

    public LeastNeeded() {
    }
}
