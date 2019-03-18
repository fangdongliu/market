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
    public Record recordSelect(Integer userId){
        Record record=marketMapper.recordSelectFinished(userId);
        if(record==null){
            return marketMapper.recordSelectUnfinished(userId);
        }
        else return record;
    }
    
    public Integer uploadInsert(){
    }
}


