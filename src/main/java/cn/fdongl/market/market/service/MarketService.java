package cn.fdongl.market.market.service;

import cn.fdongl.market.market.entity.*;
import cn.fdongl.market.market.mapper.MarketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarketService {

    @Autowired
    MarketMapper marketMapper;

    //监测点新建备案，非事务
    public Integer recordInsert(Record record) throws Exception {
        return marketMapper.recordInsert(record);
    }

    //监测点更新备案，非事务
    public Integer recordUpdate(Record record) throws Exception {
        return marketMapper.recordUpdate(record);
    }

    //监测点默认查询，非事务
    public Record recordSelect(Integer userId) throws Exception {
        Record record=marketMapper.recordSelectFinished(userId);
        if(record==null){
            return marketMapper.recordSelectUnfinished(userId);
        }
        else return record;
    }

    //监测点新建上传数据，事务
    @Transactional
    public Integer uploadInsert(UploadInfo uploadInfo,
                                TotalNum totalNum,
                                IndustryNum industryNum,
                                EmployerNum employerNum,
                                ProfNum profNum,
                                MostNeeded mostNeeded,
                                LeastNeeded leastNeeded,
                                JobSeekerNum jobSeekerNum,
                                SexNum sexNum,
                                AgeNum ageNum,
                                DegreeNum degreeNum,
                                TechGrageNum techGrageNum) throws RuntimeException {
        int tableId=marketMapper.uploadSelectNextTableId();
        if(tableId<=0){
            throw new RuntimeException();
        }
        int n=marketMapper.uploadInsertUploadInfo(uploadInfo);
        if(n<=0){
            throw new RuntimeException();
        }
        totalNum.setTableId(tableId);
        industryNum.setTableId(tableId);
        employerNum.setTableId(tableId);
        profNum.setTableId(tableId);
        mostNeeded.setTableId(tableId);
        leastNeeded.setTableId(tableId);
        jobSeekerNum.setTableId(tableId);
        sexNum.setTableId(tableId);
        ageNum.setTableId(tableId);
        degreeNum.setTableId(tableId);
        techGrageNum.setTableId(tableId);
        n=marketMapper.uploadInsertTotalNum(totalNum);
        if(n<=0){
            throw new RuntimeException();
        }
        n=marketMapper.uploadInsertIndustryNum(industryNum);
        if(n<=0){
            throw new RuntimeException();
        }
        n=marketMapper.uploadInsertEmployerNum(employerNum);
        if(n<=0){
            throw new RuntimeException();
        }
        n=marketMapper.uploadInsertProfNum(profNum);
        if(n<=0){
            throw new RuntimeException();
        }
        n=marketMapper.uploadInsertMostNeeded(mostNeeded);
        if(n<=0){
            throw new RuntimeException();
        }
        n=marketMapper.uploadInsertLeastNeeded(leastNeeded);
        if(n<=0){
            throw new RuntimeException();
        }
        n=marketMapper.uploadInsertJobSeekerNum(jobSeekerNum);
        if(n<=0){
            throw new RuntimeException();
        }
        n=marketMapper.uploadInsertSexNum(sexNum);
        if(n<=0){
            throw new RuntimeException();
        }
        n=marketMapper.uploadInsertAgeNum(ageNum);
        if(n<=0){
            throw new RuntimeException();
        }
        n=marketMapper.uploadInsertDegreeNum(degreeNum);
        if(n<=0){
            throw new RuntimeException();
        }
        n=marketMapper.uploadInsertTechGrageNum(techGrageNum);
        if(n<=0){
            throw new RuntimeException();
        }
        return 0;
    }
}
