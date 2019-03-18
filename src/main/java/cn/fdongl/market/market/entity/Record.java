package cn.fdongl.market.market.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Record {

    Integer regionEmpId;//地区级编号,即监测点用户id
    String regionEmpName;//人力资源市场名称
    String regionName;//所属地市名称
    String regionEmpContact;//联系人姓名
    String regionEmpContactMobi;//联系人手机
    String regionEmpContactNum;//联系人电话
    String regionEmpFax;//联系人传真
    Integer stateFlag;//状态标志，保存0，上传1，通过2，过期3
    Date createTime;//创建时间
    Integer creator;//创建人id
    Date reviseTime;//审核时间
    Integer reviser;//审核人id
}
