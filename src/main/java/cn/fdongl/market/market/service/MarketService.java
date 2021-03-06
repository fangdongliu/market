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

    //根据监测点id查询保存、上传的备案，非事务
    public Record recordSelect(Integer userId) throws Exception {
        Record record=marketMapper.recordSelectFinished(userId);
        if(record==null){
            return marketMapper.recordSelectUnfinished(userId);
        }
        else return record;
    }

    //监测点用户查询激活状态，非事务
    public Integer selectActivation(Integer userId) throws Exception {
        Integer activation=marketMapper.selectActivation(userId);
        if(activation==null){
            throw new Exception("查询失败");
        }
        return activation;
    }

    //监测点新建上传数据，事务
    @Transactional
    public void uploadInsert(UploadDataSet uploadDataSet) throws RuntimeException {
        Integer tableId=marketMapper.uploadSelectNextTableId();//在事务中获取下一个id，失败则回滚
        if(tableId==null||tableId<=0){
            throw new RuntimeException("数据错误");
        }
        uploadDataSet.setTableId(tableId);

        UploadInfo uploadInfo=new UploadInfo(uploadDataSet);
        TotalNum totalNum=new TotalNum(uploadDataSet);
        IndustryNum industryNum=new IndustryNum(uploadDataSet);
        EmployerNum employerNum=new EmployerNum(uploadDataSet);
        ProfNum profNum=new ProfNum(uploadDataSet);
        MostNeeded mostNeeded=new MostNeeded(uploadDataSet);
        LeastNeeded leastNeeded=new LeastNeeded(uploadDataSet);
        JobSeekerNum jobSeekerNum=new JobSeekerNum(uploadDataSet);
        SexNum sexNum=new SexNum(uploadDataSet);
        AgeNum ageNum=new AgeNum(uploadDataSet);
        DegreeNum degreeNum=new DegreeNum(uploadDataSet);
        TechGradeNum techGradeNum=new TechGradeNum(uploadDataSet);

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
        n=marketMapper.uploadInsertTechGradeNum(techGradeNum);
        if(n!=1){
            throw new RuntimeException("新建技术等级供求人数失败");
        }
    }

    //监测点更新上传数据，事务
    @Transactional
    public void uploadUpdate(UploadDataSet uploadDataSet) throws RuntimeException {
        Integer tableId=uploadDataSet.getTableId();
        if(tableId==null||tableId<=0){
            throw new RuntimeException("数据错误");
        }

        UploadInfo uploadInfo=new UploadInfo(uploadDataSet);
        TotalNum totalNum=new TotalNum(uploadDataSet);
        IndustryNum industryNum=new IndustryNum(uploadDataSet);
        EmployerNum employerNum=new EmployerNum(uploadDataSet);
        ProfNum profNum=new ProfNum(uploadDataSet);
        MostNeeded mostNeeded=new MostNeeded(uploadDataSet);
        LeastNeeded leastNeeded=new LeastNeeded(uploadDataSet);
        JobSeekerNum jobSeekerNum=new JobSeekerNum(uploadDataSet);
        SexNum sexNum=new SexNum(uploadDataSet);
        AgeNum ageNum=new AgeNum(uploadDataSet);
        DegreeNum degreeNum=new DegreeNum(uploadDataSet);
        TechGradeNum techGradeNum=new TechGradeNum(uploadDataSet);

        int n=marketMapper.uploadUpdateUploadInfo(uploadInfo);
        if(n!=1){
            throw new RuntimeException("更新上传数据信息失败");
        }
        n=marketMapper.uploadUpdateTotalNum(totalNum);
        if(n!=1){
            throw new RuntimeException("更新供求总体人数失败");
        }
        n=marketMapper.uploadUpdateIndustryNum(industryNum);
        if(n!=1){
            throw new RuntimeException("更新产业需求人数失败");
        }
        n=marketMapper.uploadUpdateEmployerNum(employerNum);
        if(n!=1){
            throw new RuntimeException("更新用人单位性质需求人数失败");
        }
        n=marketMapper.uploadUpdateProfNum(profNum);
        if(n!=1){
            throw new RuntimeException("更新职业供求人数失败");
        }
        n=marketMapper.uploadUpdateMostNeeded(mostNeeded);
        if(n!=1){
            throw new RuntimeException("更新需求前十职业失败");
        }
        n=marketMapper.uploadUpdateLeastNeeded(leastNeeded);
        if(n!=1){
            throw new RuntimeException("更新饱和前十职业失败");
        }
        n=marketMapper.uploadUpdateJobSeekerNum(jobSeekerNum);
        if(n!=1){
            throw new RuntimeException("更新人员类别求职人数失败");
        }
        n=marketMapper.uploadUpdateSexNum(sexNum);
        if(n!=1){
            throw new RuntimeException("更新性别供求人数失败");
        }
        n=marketMapper.uploadUpdateAgeNum(ageNum);
        if(n!=1){
            throw new RuntimeException("更新年龄供求人数失败");
        }
        n=marketMapper.uploadUpdateDegreeNum(degreeNum);
        if(n!=1){
            throw new RuntimeException("更新文化程度供求人数失败");
        }
        n=marketMapper.uploadUpdateTechGradeNum(techGradeNum);
        if(n!=1){
            throw new RuntimeException("更新技术等级供求人数失败");
        }
    }

    //根据监测点id查询保存、上传数据，非事务
    public UploadInfo uploadSelect(Integer userId) throws Exception {
        return marketMapper.uploadSelectUnfinished(userId);
    }
}
