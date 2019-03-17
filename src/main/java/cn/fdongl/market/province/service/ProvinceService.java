package cn.fdongl.market.province.service;

import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.province.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProvinceService {

    @Autowired
    ProvinceMapper provinceMapper;

    //根据条件查询已通过的备案
    public List<Record> conditionalQuery(Integer state, String condition){
        if(state==0){
            return provinceMapper.regionEmpNameQuery(condition);
        }
        else if(state==1){
            return provinceMapper.regionNameQuery(condition);
        }
        else if(state==2){
            return provinceMapper.regionEmpContactQuery(condition);
        }
        else return null;
    }

    //审核未通过
    @Transactional
    public Integer reject(Integer examineId,Integer aimId,String feedback) throws RuntimeException{
        int n=provinceMapper.selectNum(aimId);
        if(n>0){
            n=provinceMapper.deleteReject(aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            n=provinceMapper.sendMessage(
                    "您的备案修改未通过审核",
                    feedback,
                    examineId,
                    aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            return 0;
        }
        else{
            n=provinceMapper.updateReject(examineId,aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            n=provinceMapper.sendMessage(
                    "您的备案未通过审核",
                    feedback,
                    examineId,
                    aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            return 0;
        }
    }

    //审核通过
    @Transactional
    public Integer pass(Integer examineId,Integer aimId,String feedback) throws RuntimeException{
        int n=provinceMapper.selectNum(aimId);
        if(n>0){
            n=provinceMapper.updateExpirePass(examineId,aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            n=provinceMapper.updatePass(examineId, aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            n=provinceMapper.sendMessage(
                    "您的备案修改已通过审核",
                    feedback,
                    examineId,
                    aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            return 0;
        }
        else{
            n=provinceMapper.updatePass(examineId,aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            n=provinceMapper.updateActivation(examineId,aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            n=provinceMapper.sendMessage(
                    "您的备案已通过审核",
                    feedback,
                    examineId,
                    aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            return 0;
        }
    }
}
