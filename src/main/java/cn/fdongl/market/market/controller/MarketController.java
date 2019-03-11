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
@RequestMapping("/market")//指定接口的一级路径
public class MarketController {

    @Autowired
    MarketMapper marketMapper;

    @PostMapping("/save1")
    public void save1(HttpServletRequest request, Record record) throws Exception{

        AppUserDetail appUserDetail= AppUserDetail.fromRequest(request);
        record.setApplyDate();
        Integer n=marketMapper.save1(appUserDetail.getId(),record);
        if(n<=0){
            throw new Exception();
        }
    }

    @PostMapping("/save2")
    public void save2(HttpServletRequest request,Record record) throws Exception{
        AppUserDetail appUserDetail= AppUserDetail.fromRequest(request);
        record.setApplyDate();
        Integer n=marketMapper.save2(appUserDetail.getId(),record);
        if(n<=0){
            throw new Exception();
        }
    }

    @PostMapping("/download")
    public Record download(HttpServletRequest request,Integer stateFlag) throws Exception{
        AppUserDetail appUserDetail= AppUserDetail.fromRequest(request);
        Record record=marketMapper.download(appUserDetail.getId(),stateFlag);
        if(record==null){
            throw new Exception();
        }
        return record;
    }
}
