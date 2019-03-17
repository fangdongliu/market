package cn.fdongl.market.market.controller;


import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.market.mapper.MarketMapper;
import cn.fdongl.market.security.entity.AppUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/market/record")//指定接口的一级路径
public class MarketController {

    @Autowired
    MarketMapper marketMapper;

    //新建备案
    @PostMapping("/insert")
    public Integer insert(HttpServletRequest request, Record record,Integer stateFlag) throws Exception{
        AppUserDetail appUserDetail= AppUserDetail.fromRequest(request);
        record.setRegionEmpId(appUserDetail.getId());
        record.setStateFlag(stateFlag);
        record.setCreateTime();
        record.setCreater(appUserDetail.getId());
        record.setReviseTIme(null);
        record.setReviser(null);
        Integer n=marketMapper.insert(record);
        if(n<=0){
            return 1;
        }
        return 0;
    }

    //更新备案
    @PostMapping("/update")
    public Integer update(HttpServletRequest request,Record record,Integer stateFlag) throws Exception{
        AppUserDetail appUserDetail= AppUserDetail.fromRequest(request);
        record.setRegionEmpId(appUserDetail.getId());
        record.setStateFlag(stateFlag);
        record.setCreateTime();
        record.setCreater(appUserDetail.getId());
        record.setReviseTIme(null);
        record.setReviser(null);
        Integer n=marketMapper.update(record);
        if(n<=0){
            return 1;
        }
        return 0;
    }

    //查询备案
    @PostMapping("/select")
    public Record select(HttpServletRequest request) throws Exception{
        AppUserDetail appUserDetail= AppUserDetail.fromRequest(request);
        return marketMapper.select(appUserDetail.getId());
    }
}
