package cn.fdongl.market.market.service;

import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.market.mapper.MarketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService {

    @Autowired
    MarketMapper marketMapper;

    //监测点默认查询
    public Record select(Integer userId){
        Record record=marketMapper.selectFinished(userId);
        if(record==null){
            return marketMapper.selectUnfinished(userId);
        }
        else return record;
    }
}
