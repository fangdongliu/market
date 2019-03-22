package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class IndustryNum {//产业需求人数表，共23条数据

    Integer tableId;//数据表id
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
}
