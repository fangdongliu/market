package cn.fdongl.market.market.entity;

import java.util.Date;
import lombok.Data;

@Data
public class UploadInfo {//上传数据信息表，共7条数据

    Integer tableId;//数据表id
    Integer uploadPeriodId;//所属上传期id
    Integer stateFlag;//状态标记
    Date createTime;//创建时间
    Integer creator;//创建者
    Date reviseTime;//修改时间
    Integer reviser;//修改者

    public UploadInfo(UploadDataSet uploadDataSet){
        this.tableId = uploadDataSet.getTableId();
        this.uploadPeriodId = uploadDataSet.getUploadPeriodId();
        this.stateFlag = uploadDataSet.getStateFlag();
        this.createTime = uploadDataSet.getCreateTime();
        this.creator = uploadDataSet.getCreator();
        this.reviseTime = uploadDataSet.getReviseTime();
        this.reviser = uploadDataSet.getReviser();
    }

    public UploadInfo() {
    }
}
