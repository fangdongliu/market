package cn.fdongl.market.market.entity;

import java.util.Date;

public class Record {

    String regionEmpName;//人力资源市场名称
    String regionName;//所属地市名称
    String regionEmpContact;//联系人姓名
    String regionEmpContactMobi;//联系人手机
    String regionEmpContactNum;//联系人电话
    String regionEmpFax;//联系人传真
    Integer stateFlag;//状态标志，保存0，上传1，未通过2，通过3，过期4
    Date applyDate;//上传申请日期
    Date examineDate;//审核日期

    public void setApplyDate(){
        applyDate.getTime();
    }

    public void setExamineDate(){
        examineDate.getTime();
    }

}
