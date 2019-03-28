package cn.fdongl.market.market.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UploadDataSet {//总的数据集，共193条数据

    //上传数据信息表，共7条数据
    Integer tableId;//数据表id
    Integer uploadPeriodId;//所属上传期id
    Integer stateFlag;//状态标记
    Date createTime;//创建时间
    Integer creator;//创建者
    Date reviseTime;//修改时间
    Integer reviser;//修改者

    //供求总体人数表，共2条数据
    Integer needPopu;//需求人数
    Integer jobseekPopu;//求职人数

    //产业需求人数表，共22条数据
    Integer industry1Need;//第一产业需求人数
    Integer industry2Need;//第二产业需求人数
    Integer industry3Need;//第三产业需求人数
    Integer mineNeed;//采矿业，第二产业
    Integer manuNeed;//制造业，第二产业
    Integer elecGasWaterNeed;//电力、燃气及水的生产和供应业，第二产业
    Integer archNeed;//建筑业，第二产业
    Integer tranStorPostNeed;//交通运输、仓储和邮政业，第三产业
    Integer infoCompSoftNeed;//信息传输、计算机服务和软件业，第三产业
    Integer retailNeed;//批发和零售业，第三产业
    Integer accoCaterNeed;//住宿和餐饮业，第三产业
    Integer financeNeed;//金融业，第三产业
    Integer estateNeed;//房地产业，第三产业
    Integer leaseBusiServNeed;//租赁和商务服务业，第三产业
    Integer reseTechAddrNeed;//科学研究、技术服务和地址勘查业，第三产业
    Integer waterEnviFaciNeed;//水利、环境和公共设施管理业，第三产业
    Integer resiServNeed;//居民服务和其他服务业，第三产业
    Integer eduNeed;//教育，第三产业
    Integer healSecuWelfNeed;//卫生、社会保障和社会福利业，第三产业
    Integer cultSportEnteNeed;//文化、体育和娱乐业，第三产业
    Integer manaOrgaNeed;//公共管理与社会组织，第三产业
    Integer inteOrgaNeed;//国际组织，第三产业

    //用人单位性质需求人数表，共14条数据
    Integer staOwnNeed;//国有企业，内资企业，企业
    Integer collNeed;//集体企业，内资企业，企业
    Integer coopStockNeed;//股份合作企业，内资企业，企业
    Integer jointNeed;//联营企业，内资企业，企业
    Integer limiLiabNeed;//有限责任公司，内资企业，企业
    Integer limitedNeed;//股份有限公司，内资企业，企业
    Integer privNeed;//私营企业，内资企业，企业
    Integer otherEnteNeed;//其他企业，内资企业，企业
    Integer hmtInveNeed;//港、澳、台投资企业，企业
    Integer foreInveEnte;//外商投资企业，企业
    Integer indiNeed;//个体经营，企业
    Integer instNeed;//事业
    Integer orgaNeed;//机关
    Integer otherNeedEmployerNum;//其他

    //职业供求人数表，共15条数据
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
    Integer otherNeedProfNum;//其他需求人数
    Integer otherJobseek;//其他求职人数
    Integer noRequJobseek;//无要求求职人数

    //需求前十职业表，共40条数据
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

    //饱和前十职业表，共40条数据
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

    //人员类别求职人数表，共10条数据
    Integer unempYouth;//新成长失业青年
    Integer graduate;//应届高校毕业生
    Integer empToUnemp;//就业转失业人员
    Integer othUnemp;//其他失业人员
    Integer emped;//在业人员
    Integer laidOff;//下岗职工
    Integer retiree;//退休人员
    Integer student;//在学人员
    Integer cityRural;//本市农村人员
    Integer fore;//外埠人员

    //性别供求人数表，共5条数据
    Integer maleNeed;//男性需求人数
    Integer maleJobseek;//男性求职人数
    Integer femaleNeed;//女性需求人数
    Integer femaleJobseek;//女性求职人数
    Integer noRequNeedSexNum;//无要求需求人数

    //年龄供求人数表，共9条数据
    Integer sixteenTwentyfourNeed;//16-24岁需求人数
    Integer sixteenTwentyfourJobseek;//16-24岁求职人数
    Integer twentyfiveThirtyfourNeed;//25-34岁需求人数
    Integer twentyfiveThirtyfourJobseek;//25-34岁求职人数
    Integer thirtyfiveFortyfourNeed;//35-44岁需求人数
    Integer thirtyfiveFortyfourJobseek;//35-44岁求职人数
    Integer overFortyfourNeed;//45岁以上需求人数
    Integer overFortyfourJobseek;//45岁以上求职人数
    Integer noRequNeedAgeNum;//无要求需求人数

    //文化程度供求人数表，共11条数据
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
    Integer noRequNeedDegreeNum;//无要求需求人数

    //技术等级供求人数表，共18条数据
    Integer profLevel5Need;//职业资格五级（初级技能）需求人数
    Integer profLevel5Jobseek;//职业资格五级（初级技能）求职人数
    Integer profLevel4Need;//职业资格四级（中级技能）需求人数
    Integer profLevel4Jobseek;//职业资格四级（中级技能）求职人数
    Integer profLevel3Need;//职业资格三级（高级技能）需求人数
    Integer profLevel3Jobseek;//职业资格三级（高级技能）求职人数
    Integer profLevel2Need;//职业资格二级（技师）需求人数
    Integer profLevel2Jobseek;//职业资格二级（技师）求职人数
    Integer profLevel1Need;//职业资格一级（高级技师）需求人数
    Integer profLevel1Jobseek;//职业资格一级（高级技师）求职人数
    Integer primProfNeed;//初级专业技术职务需求人数
    Integer primProfJobseek;//初级专业技术职务求职人数
    Integer mediProfNeed;//中级专业技术职务需求人数
    Integer mediProfJobseek;//中级专业技术职务求职人数
    Integer seniProfNeed;//高级专业技术职务需求人数
    Integer seniProfJobseek;//高级专业技术职务求职人数
    Integer noTechJobseek;//无技术等级或职务求职人数
    Integer noRequNeedTechGradeNum;//无要求需求人数

}
