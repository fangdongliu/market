package cn.fdongl.market.market.entity;

import java.util.Date;
import lombok.Data;

@Data
public class UploadInfo {

    Integer stateFlag;//状态标记
    Date createTime;//创建时间
    Integer creator;//创建者
    Date reviseTime;//修改时间
    Integer reviser;//修改者
}
