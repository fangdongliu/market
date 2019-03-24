package cn.fdongl.market.city.service;


import cn.fdongl.market.city.mapper.CityMapper;
import cn.fdongl.market.data.mapper.DataMapper;
import cn.fdongl.market.market.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    CityMapper cityMapper;

    @Autowired
    DataMapper dataMapper;

    //市级根据条件查询已通过的备案信息（只能查到下属），非事务
    public List<Record> recordConditionalQuery(Integer cityId,Integer state,String condition) throws Exception {
        if(state==0){
            return cityMapper.recordRegionEmpNameQuery(cityId,condition);
        }
        else if(state==1){
            return cityMapper.recordRegionNameQuery(cityId,condition);
        }
        else if(state==2){
            return cityMapper.recordRegionEmpContactQuery(cityId,condition);
        }
        else throw new Exception("状态参数错误");
    }

    //市级上传数据审核未通过，事务
    @Transactional
    public void uploadReject(Integer examineId,Integer aimId,String content) throws RuntimeException {
        int n=cityMapper.uploadUpdateReject(examineId,aimId);
        if(n!=1){
            throw new RuntimeException("修改上传数据失败");
        }
        n=dataMapper.sendMessage(
                "您的上传数据未通过审核",
                content,
                examineId,
                aimId);
        if(n!=1){
            throw new RuntimeException("通知发送失败");
        }
    }

    //市级上传数据审核通过，事务
    @Transactional
    public void uploadPass(Integer examineId,Integer aimId,String content) throws RuntimeException {
        int n=cityMapper.uploadUpdatePass(examineId,aimId);
        if(n!=1){
            throw new RuntimeException("修改上传数据失败");
        }
        n=dataMapper.sendMessage(
                "您的上传数据已通过审核",
                content,
                examineId,
                aimId);
        if(n!=1){
            throw new RuntimeException("通知发送失败");
        }
    }
}
