package cn.fdongl.market.data.service;

import cn.fdongl.market.data.mapper.DataMapper;
import cn.fdongl.market.market.entity.*;
import cn.fdongl.market.security.entity.AppUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DataService {

    @Autowired
    DataMapper dataMapper;

    //发送一条通知，单点发送
    public void sendMessage(String title,String content,Integer examineId,Integer aimId) throws Exception {
        int n=dataMapper.sendMessage(title,content,examineId,aimId);
        if(n!=1){
            throw new Exception("发送失败");
        }
    }

    //发送一条通知，全局发送
    public void sendMessageGlobal(String title,String content,Integer userId) throws Exception {
        int n=dataMapper.sendMessageGlobal(title,content,userId);
        if(n!=1){
            throw new Exception("发送失败");
        }
    }

    //更新一条通知
    public void updateMessage(String title,String content,Integer userId,Integer noticeId) throws Exception {
        int n=dataMapper.updateMessage(title,content,userId,noticeId);
        if(n!=1){
            throw new Exception("修改失败");
        }
    }

    //删除一条通知
    public void deleteMessage(Integer userId,Integer noticeId) throws Exception {
        int n=dataMapper.deleteMessage(userId,noticeId);
        if(n!=1){
            throw new Exception("删除失败");
        }
    }

    //根据时间点查询简易上传期
    public SimpleUploadPeriod selectSimpleUploadPeriod(Date date) throws Exception {
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        return dataMapper.selectSimpleUploadPeriod(sqlDate);
    }

    //根据用户id查询上传数据信息
    public List<UploadInfo> selectUploadInfoById(Integer userId) throws Exception {
        return dataMapper.selectUploadInfoById(userId);
    }

    //上传数据信息表查询
    public UploadInfo selectUploadInfo(Integer tableId) throws Exception {
        return dataMapper.selectUploadInfo(tableId);
    }

    //供求总体人数表查询
    public TotalNum selectTotalNum(Integer tableId) throws Exception {
        return dataMapper.selectTotalNum(tableId);
    }

    //产业需求人数表查询
    public IndustryNum selectIndustryNum(Integer tableId) throws Exception {
        return dataMapper.selectIndustryNum(tableId);
    }

    //用人单位性质需求人数表查询
    public EmployerNum selectEmployerNum(Integer tableId) throws Exception {
        return dataMapper.selectEmployerNum(tableId);
    }

    //职业供求人数表查询
    public ProfNum selectProfNum(Integer tableId) throws Exception {
        return dataMapper.selectProfNum(tableId);
    }

    //需求前十职业表查询
    public MostNeeded selectMostNeeded(Integer tableId) throws Exception {
        return dataMapper.selectMostNeeded(tableId);
    }

    //饱和前十职业表查询
    public LeastNeeded selectLeastNeeded(Integer tableId) throws Exception {
        return dataMapper.selectLeastNeeded(tableId);
    }

    //人员类别求职人数表查询
    public JobSeekerNum selectJobSeekerNum(Integer tableId) throws Exception {
        return dataMapper.selectJobSeekerNum(tableId);
    }

    //性别供求人数表查询
    public SexNum selectSexNum(Integer tableId) throws Exception {
        return dataMapper.selectSexNum(tableId);
    }

    //年龄供求人数表查询
    public AgeNum selectAgeNum(Integer tableId) throws Exception {
        return dataMapper.selectAgeNum(tableId);
    }

    //文化程度供求人数表查询
    public DegreeNum selectDegreeNum(Integer tableId) throws Exception {
        return dataMapper.selectDegreeNum(tableId);
    }

    //技术等级供求人数表查询
    public TechGradeNum selectTechGradeNum(Integer tableId) throws Exception {
        return dataMapper.selectTechGradeNum(tableId);
    }
}
