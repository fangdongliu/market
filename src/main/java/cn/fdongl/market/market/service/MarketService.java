package cn.fdongl.market.market.service;

import cn.fdongl.market.market.entity.*;
import cn.fdongl.market.market.mapper.MarketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class MarketService {

    @Autowired
    MarketMapper marketMapper;

    //监测点新建备案，非事务
    public void recordInsert(Record record) throws Exception {
        int n=marketMapper.recordInsert(record);
        if(n!=1){
            throw new Exception("新建备案失败");
        }
    }

    //监测点更新备案，非事务
    public void recordUpdate(Record record) throws Exception {
        int n=marketMapper.recordUpdate(record);
        if(n!=1){
            throw new Exception("新建备案失败");
        }
    }

    //监测点默认查询，非事务
    public Record recordSelect(Integer userId) throws Exception {
        Record record=marketMapper.recordSelectFinished(userId);
        if(record==null){
            return marketMapper.recordSelectUnfinished(userId);
        }
        else return record;
    }

    //按时间点查询上报时限
    public SimpleUploadPeriod UploadSelectUploadPeriod(Date date){
        //TODO
        return null;
    }

    //监测点新建上传数据，事务
    @Transactional
    public void uploadInsert(UploadInfo uploadInfo,
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
            throw new RuntimeException("数据库错误");
        }
        uploadInfo.setTableId(tableId);
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
        int n=marketMapper.uploadInsertUploadInfo(uploadInfo);
        if(n!=1){
            throw new RuntimeException("新建上传数据信息失败");
        }
        n=marketMapper.uploadInsertTotalNum(totalNum);
        if(n!=1){
            throw new RuntimeException("新建供求总体人数失败");
        }
        n=marketMapper.uploadInsertIndustryNum(industryNum);
        if(n!=1){
            throw new RuntimeException("新建产业需求人数失败");
        }
        n=marketMapper.uploadInsertEmployerNum(employerNum);
        if(n!=1){
            throw new RuntimeException("新建用人单位性质需求人数失败");
        }
        n=marketMapper.uploadInsertProfNum(profNum);
        if(n!=1){
            throw new RuntimeException("新建职业供求人数失败");
        }
        n=marketMapper.uploadInsertMostNeeded(mostNeeded);
        if(n!=1){
            throw new RuntimeException("新建需求前十职业失败");
        }
        n=marketMapper.uploadInsertLeastNeeded(leastNeeded);
        if(n!=1){
            throw new RuntimeException("新建饱和前十职业失败");
        }
        n=marketMapper.uploadInsertJobSeekerNum(jobSeekerNum);
        if(n!=1){
            throw new RuntimeException("新建人员类别求职人数失败");
        }
        n=marketMapper.uploadInsertSexNum(sexNum);
        if(n!=1){
            throw new RuntimeException("新建性别供求人数失败");
        }
        n=marketMapper.uploadInsertAgeNum(ageNum);
        if(n!=1){
            throw new RuntimeException("新建年龄供求人数失败");
        }
        n=marketMapper.uploadInsertDegreeNum(degreeNum);
        if(n!=1){
            throw new RuntimeException("新建文化程度供求人数失败");
        }
        n=marketMapper.uploadInsertTechGrageNum(techGrageNum);
        if(n!=1){
            throw new RuntimeException("新建技术等级供求人数失败");
        }
    }

//    //监测点更新上传数据，事务
//    @Transactional
//    public void uploadUpdate(UploadInfo uploadInfo,
//                             TotalNum totalNum,
//                             IndustryNum industryNum,
//                             EmployerNum employerNum,
//                             ProfNum profNum,
//                             MostNeeded mostNeeded,
//                             LeastNeeded leastNeeded,
//                             JobSeekerNum jobSeekerNum,
//                             SexNum sexNum,
//                             AgeNum ageNum,
//                             DegreeNum degreeNum,
//                             TechGrageNum techGrageNum) throws RuntimeException {
//        int tableId=marketMapper.uploadSelectNextTableId();
//        if(tableId<=0){
//            throw new RuntimeException("数据库错误");
//        }
//        uploadInfo.setTableId(tableId);
//        totalNum.setTableId(tableId);
//        industryNum.setTableId(tableId);
//        employerNum.setTableId(tableId);
//        profNum.setTableId(tableId);
//        mostNeeded.setTableId(tableId);
//        leastNeeded.setTableId(tableId);
//        jobSeekerNum.setTableId(tableId);
//        sexNum.setTableId(tableId);
//        ageNum.setTableId(tableId);
//        degreeNum.setTableId(tableId);
//        techGrageNum.setTableId(tableId);
//        int n=marketMapper.uploadInsertUploadInfo(uploadInfo);
//        if(n!=1){
//            throw new RuntimeException("新建上传数据信息失败");
//        }
//        n=marketMapper.uploadInsertTotalNum(totalNum);
//        if(n!=1){
//            throw new RuntimeException("新建供求总体人数失败");
//        }
//        n=marketMapper.uploadInsertIndustryNum(industryNum);
//        if(n!=1){
//            throw new RuntimeException("新建产业需求人数失败");
//        }
//        n=marketMapper.uploadInsertEmployerNum(employerNum);
//        if(n!=1){
//            throw new RuntimeException("新建用人单位性质需求人数失败");
//        }
//        n=marketMapper.uploadInsertProfNum(profNum);
//        if(n!=1){
//            throw new RuntimeException("新建职业供求人数失败");
//        }
//        n=marketMapper.uploadInsertMostNeeded(mostNeeded);
//        if(n!=1){
//            throw new RuntimeException("新建需求前十职业失败");
//        }
//        n=marketMapper.uploadInsertLeastNeeded(leastNeeded);
//        if(n!=1){
//            throw new RuntimeException("新建饱和前十职业失败");
//        }
//        n=marketMapper.uploadInsertJobSeekerNum(jobSeekerNum);
//        if(n!=1){
//            throw new RuntimeException("新建人员类别求职人数失败");
//        }
//        n=marketMapper.uploadInsertSexNum(sexNum);
//        if(n!=1){
//            throw new RuntimeException("新建性别供求人数失败");
//        }
//        n=marketMapper.uploadInsertAgeNum(ageNum);
//        if(n!=1){
//            throw new RuntimeException("新建年龄供求人数失败");
//        }
//        n=marketMapper.uploadInsertDegreeNum(degreeNum);
//        if(n!=1){
//            throw new RuntimeException("新建文化程度供求人数失败");
//        }
//        n=marketMapper.uploadInsertTechGrageNum(techGrageNum);
//        if(n!=1){
//            throw new RuntimeException("新建技术等级供求人数失败");
//        }
//    }
    public AgeNum AgeNumSelect(Integer tableId) throws Exception{
        AgeNum output = marketMapper.uploadSelectAgeNum(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    public DegreeNum DegreeNumSelect(Integer tableId) throws Exception{
        DegreeNum output=marketMapper.uploadSelectDegreeNum(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    public EmployerNum EmployerNumSelect(Integer tableId) throws Exception{
        EmployerNum output=marketMapper.uploadSelectEmployNum(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }
}
