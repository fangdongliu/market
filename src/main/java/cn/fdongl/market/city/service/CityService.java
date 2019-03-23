package cn.fdongl.market.city.service;


import cn.fdongl.market.city.mapper.CityMapper;
import cn.fdongl.market.province.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {

    @Autowired
    CityMapper cityMapper;

    @Autowired
    ProvinceMapper provinceMapper;

    //市级上传数据审核未通过，事务
    @Transactional
    public void uploadReject(Integer examineId,Integer aimId,String content) throws RuntimeException {
        int n=cityMapper.uploadUpdateReject(examineId,aimId);
        if(n!=1){
            throw new RuntimeException("修改上传数据失败");
        }
        n=provinceMapper.sendMessage(
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
        n=provinceMapper.sendMessage(
                "您的上传数据已通过审核",
                content,
                examineId,
                aimId);
        if(n!=1){
            throw new RuntimeException("通知发送失败");
        }
    }
}
