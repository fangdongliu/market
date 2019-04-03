package cn.fdongl.market.city.service;


import cn.fdongl.market.city.mapper.CityMapper;
import cn.fdongl.market.common.mapper.CommonMapper;
import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.market.entity.UploadInfoExtra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    CityMapper cityMapper;

    @Autowired
    CommonMapper commonMapper;

    //市级根据条件查询已通过的备案信息（只能查到下属），非事务
    public List<Record> recordConditionalQuery(Integer cityId,String condition) throws Exception {
        return cityMapper.recordConditionalQuery(cityId,condition);
    }

    //市级查询待审核的上传数据（只能查到下属），非事务
    public List<UploadInfoExtra> uploadExamineQuery(Integer cityId) throws Exception {
        return cityMapper.uploadExamineQuery(cityId);
    }

    //市级上传数据审核未通过，事务
    @Transactional
    public void uploadReject(Integer cityId,Integer aimId,String content) throws RuntimeException {
        if(content==null){
            content="";
        }
        int n=cityMapper.uploadUpdateReject(cityId,aimId);
        if(n!=1){
            throw new RuntimeException("修改上传数据失败");
        }
        n=commonMapper.sendMessage(
                "您的上传数据未通过审核",
                content,
                cityId,
                aimId);
        if(n!=1){
            throw new RuntimeException("通知发送失败");
        }
    }

    //市级上传数据审核通过，事务
    @Transactional
    public void uploadPass(Integer cityId,Integer aimId,String content) throws RuntimeException {
        if(content==null){
            content="";
        }
        int n=cityMapper.uploadUpdatePass(cityId,aimId);
        if(n!=1){
            throw new RuntimeException("修改上传数据失败");
        }
        n=commonMapper.sendMessage(
                "您的上传数据已通过审核",
                content,
                cityId,
                aimId);
        if(n!=1){
            throw new RuntimeException("通知发送失败");
        }
    }
}
