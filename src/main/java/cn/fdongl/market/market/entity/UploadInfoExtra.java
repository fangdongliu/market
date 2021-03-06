package cn.fdongl.market.market.entity;

import java.util.Date;
import lombok.Data;

@Data
public class UploadInfoExtra {//上传数据信息表Ex，共8条数据

    Integer tableId;//数据表id
    Integer uploadPeriodId;//所属上传期id
    Integer stateFlag;//状态标记
    Date createTime;//创建时间
    Integer creator;//创建者
    Date reviseTime;//修改时间
    Integer reviser;//修改者

    String regionEmpName;//人力资源市场名称
}
