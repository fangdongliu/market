package cn.fdongl.market.common.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {//通知，共8条数据

    Integer noticeId;//通知id
    String noticeTitle;//通知标题
    String noticeContent;//通知内容
    Date createTime;//创建时间
    Integer creator;//创建者
    Date reviseTime;//修改时间
    Integer reviser;//修改者
    Integer receiver;//发送用户（不填则为全局发送）
}
