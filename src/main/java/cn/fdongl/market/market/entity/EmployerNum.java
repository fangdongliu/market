package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class EmployerNum {//用人单位性质需求人数表，共15条数据

    Integer tableId;//数据表id
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
    Integer otherNeed;//其他
}
