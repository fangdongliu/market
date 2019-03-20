package cn.fdongl.market.province.service;

import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.province.entity.InnerUploadPeriod;
import cn.fdongl.market.province.entity.uploadPeriod;
import cn.fdongl.market.province.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ProvinceService {

    @Autowired
    ProvinceMapper provinceMapper;

    //查询所有待审核的备案,非事务
    public List<Record> recordExamineQuery() throws Exception {
        return provinceMapper.recordExamineQuery();
    }

    //根据条件查询已通过的备案，非事务
    public List<Record> recordConditionalQuery(Integer state,String condition) throws Exception {
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

    //审核未通过，事务
    @Transactional
    public Integer recordReject(Integer examineId,Integer aimId,String content) throws RuntimeException {
        int n=provinceMapper.recordSelectNum(aimId);
        if(n>0){
            n=provinceMapper.recordDeleteReject(aimId);
            if(n<=0){
                throw new RuntimeException();

            }
            n=provinceMapper.sendMessage(
                    "您的备案修改未通过审核",
                    content,
                    examineId,
                    aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            return 0;
        }
        else if(n==0){
            n=provinceMapper.recordUpdateReject(examineId,aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            n=provinceMapper.sendMessage(
                    "您的备案未通过审核",
                    content,
                    examineId,
                    aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            return 0;
        }
        else throw new RuntimeException();
    }

    //审核通过，事务
    @Transactional
    public Integer recordPass(Integer examineId, Integer aimId, String content) throws RuntimeException {
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
                    content,
                    examineId,
                    aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            return 0;
        }
        else if(n==0){
            n=provinceMapper.recordUpdateActivation(examineId,aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            n=provinceMapper.recordUpdatePass(examineId,aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            n=provinceMapper.sendMessage(
                    "您的备案已通过审核",
                    content,
                    examineId,
                    aimId);
            if(n<=0){
                throw new RuntimeException();
            }
            return 0;
        }
        else throw new RuntimeException();
    }

    //检查日期是否合法
    public boolean timeCheck(Date startDate, Date endDate){
        if(startDate!=null && endDate!=null){
            if(startDate.compareTo(endDate)<0){
                float f=endDate.getTime()-startDate.getTime();
                f=f/86400000;
                if(f>30){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    //创建新的上报时限
    public Integer periodInsert(InnerUploadPeriod innerUploadPeriod){
        innerUploadPeriod.setUploadPeriodId(provinceMapper.getPeriodNumber());
        return provinceMapper.periodInsert(innerUploadPeriod);
    }

    //更新上报时限
    public Integer periodUpdate(Date startDate, Date endDate, java.util.Date reviseDate, Integer reviser,Integer uploadPeriodId){
        return provinceMapper.periodUpdate(startDate,endDate,reviseDate,reviser,uploadPeriodId);
    }

    //按id查询上报时限
    public InnerUploadPeriod uploadPeriodSelectById(Integer periodId){
        return provinceMapper.selectById(periodId);
    }

    //按时间点查询上报时限
    public InnerUploadPeriod uploadPeriodSelectByTime(Date inputDate){
        return provinceMapper.selectByTime(inputDate);
    }

    //按时间段查询上报时限
    public List<InnerUploadPeriod> uploadPeriodSelectByPeriod(Date startDate,Date endDate){
        return provinceMapper.selectByPeriod(startDate,endDate);
    }

    //将InnerUploadPeriod对象转化为uploadPeriod对象
    public uploadPeriod InnerUploadPeriodTranform(InnerUploadPeriod input){
        try {
            if(input==null){
                return null;
            }
            uploadPeriod output = new uploadPeriod();
            output.setUploadPeriodId(input.getUploadPeriodId());
            output.setCreator(input.getCreator());
            output.setReviser(input.getReviser());
            output.setDeleteFlag(input.getDeleteFlag());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (input.getStartDate() == null) {
                output.setStartDate(null);
            } else {
                output.setStartDate(format.format(input.getStartDate()));
            }
            if (input.getEndDate() == null) {
                output.setEndDate(null);
            } else {
                output.setEndDate(format.format(input.getEndDate()));
            }
            if (input.getReviseTime() == null) {
                output.setReviseTime(null);
            } else {
                output.setReviseTime(format.format(input.getReviseTime()));
            }
            if (input.getCreatTime() == null) {
                output.setCreatTime(null);
            } else {
                output.setCreatTime(format.format(input.getCreatTime()));
            }
            return output;
        }
        catch (Exception e){
            return null;
        }
    }
}
