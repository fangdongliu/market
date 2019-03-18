package cn.fdongl.market.province.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class uploadPeriod {
    Integer uploadPeriodId;//时限id
    java.sql.Date startDate;//开始日期
    java.sql.Date endDate;//结束日期
    java.util.Date creatTime;//创建日期
    Integer crateor;//创建用户id
    java.util.Date reviseTime;//最后修改日期
    Integer reviser;//最后修改用户id
    Integer deleteFlag;//删除标志位，弃用

    public boolean timeCheck(){
        if(startDate!=null && endDate!=null){
            if(startDate.compareTo(endDate)<0){
                float f=endDate.getTime()-startDate.getTime();
                f=f/86400000;
                if(f>30){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
