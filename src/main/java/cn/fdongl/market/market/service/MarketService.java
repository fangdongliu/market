package cn.fdongl.market.market.service;

import cn.fdongl.market.market.entity.*;
import cn.fdongl.market.market.mapper.MarketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    //按时间点查询上报时限，非事务
    public SimpleUploadPeriod uploadSelectUploadPeriod(Date date){
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        return marketMapper.uploadSelectUploadPeriod(sqlDate);
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
                             TechGradeNum techGradeNum) throws RuntimeException {
        Integer tableId=marketMapper.uploadSelectNextTableId();//在事务中获取下一个id，失败则回滚
        if(tableId==null||tableId<=0){
            throw new RuntimeException("数据错误");
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
        techGradeNum.setTableId(tableId);
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
    public void uploadUpdate(UploadInfo uploadInfo,
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
                             TechGradeNum techGradeNum) throws RuntimeException {
        Integer tableId=uploadInfo.getTableId();
        if(tableId==null||tableId<=0){
            throw new RuntimeException("数据错误");
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
        techGradeNum.setTableId(tableId);
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

    //上传数据信息表查询
    public UploadInfo UploadInfoSelect(Integer tableId) throws Exception {
        UploadInfo output=marketMapper.uploadSelectUploadInfo(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    //供求总体人数表查询
    public TotalNum TotalNumSelect(Integer tableId) throws Exception {
        TotalNum output=marketMapper.uploadSelectTotalNum(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    //产业需求人数表查询
    public IndustryNum IndustryNumSelect(Integer tableId) throws Exception {
        IndustryNum output=marketMapper.uploadSelectIndustryNum(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    //用人单位性质需求人数表查询
    public EmployerNum EmployerNumSelect(Integer tableId) throws Exception {
        EmployerNum output=marketMapper.uploadSelectEmployNum(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    //职业供求人数表查询
    public ProfNum ProfNumSelect(Integer tableId) throws Exception {
        ProfNum output = marketMapper.uploadSelectProfNum(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    //需求前十职业表查询
    public MostNeeded MostNeededSelect(Integer tableId) throws Exception {
        MostNeeded output = marketMapper.uploadSelectMostNeeded(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    //饱和前十职业表查询
    public LeastNeeded LeastNeededSelect(Integer tableId) throws Exception {
        LeastNeeded output = marketMapper.uploadSelectLeastNeeded(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    //人员类别求职人数表查询
    public JobSeekerNum JobSeekerNumSelect(Integer tableId) throws Exception {
        JobSeekerNum output = marketMapper.uploadSelectJobSeekerNum(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    //性别供求人数表查询
    public SexNum SexNumSelect(Integer tableId) throws Exception {
        SexNum output = marketMapper.uploadSelectSexNum(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    //年龄供求人数表查询
    public AgeNum AgeNumSelect(Integer tableId) throws Exception {
        AgeNum output = marketMapper.uploadSelectAgeNum(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    //文化程度供求人数表查询
    public DegreeNum DegreeNumSelect(Integer tableId) throws Exception {
        DegreeNum output=marketMapper.uploadSelectDegreeNum(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }

    //技术等级供求人数表查询
    public TechGradeNum TechGradeNumSelect(Integer tableId) throws Exception {
        TechGradeNum output=marketMapper.uploadSelectTechGradeNum(tableId);
        if(output==null){
            throw new Exception("No result");
        }
        else{
            return output;
        }
    }
    //查询目标用户的报表
    public List<UploadInfo> UploadInfoSelectByUser(Integer userId) throws Exception{
        List<UploadInfo> output=marketMapper.uploadInfoSelectByUser(userId);
        if(output==null){
            throw new Exception("No result");
        }
        else {
            return output;
        }
    }

}
