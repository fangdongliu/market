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

    //查询所有待审核的备案
    public List<Record> recordExamineQuery(){
        return provinceMapper.recordExamineQuery();
    }

    //根据条件查询已通过的备案
    public List<Record> conditionalQuery(Integer state, String condition){
        if(state==0){
            return provinceMapper.recordRegionEmpNameQuery(condition);
        }
        else if(state==1){
            return provinceMapper.recordRegionNameQuery(condition);
        }
        else if(state==2){
            return provinceMapper.recordRegionEmpContactQuery(condition);
        }
        else return null;
    }

    //审核未通过
    @Transactional
    public Integer reject(Integer examineId,Integer aimId,String feedback) throws RuntimeException{
        int n=provinceMapper.recordSelectNum(aimId);
        if(n>0){
            n=provinceMapper.recordDeleteReject(aimId);
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
            n=provinceMapper.recordUpdateReject(examineId,aimId);
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
        int n=provinceMapper.recordSelectNum(aimId);
        if(n>0){
            n=provinceMapper.recordUpdateExpirePass(examineId,aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            n=provinceMapper.recordUpdatePass(examineId, aimId);
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
            n=provinceMapper.recordUpdatePass(examineId,aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            n=provinceMapper.recordUpdateActivation(examineId,aimId);
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
