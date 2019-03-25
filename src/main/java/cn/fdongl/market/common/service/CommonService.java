package cn.fdongl.market.common.service;

import cn.fdongl.market.common.entity.Notice;
import cn.fdongl.market.common.mapper.CommonMapper;
import cn.fdongl.market.market.entity.*;
import cn.fdongl.market.province.entity.UploadPeriod;
import cn.fdongl.market.province.entity.UserInfoDisplay;
import cn.fdongl.market.province.mapper.ProvinceMapper;
import cn.fdongl.market.province.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

@Service
public class CommonService {

    @Autowired
    CommonMapper commonMapper;
    @Autowired
    ProvinceService provinceService;
    @Autowired
    ProvinceMapper provinceMapper;

    //发送一条全局通知
    public void sendMessageGlobal(String title,String content,Integer userId) throws Exception {
        int n= commonMapper.sendMessageGlobal(title,content,userId);
        if(n!=1){
            throw new Exception("发送失败");
        }
    }

    //更新一条通知
    public void updateMessage(String title,String content,Integer userId,Integer noticeId) throws Exception {
        int n= commonMapper.updateMessage(title,content,userId,noticeId);
        if(n!=1){
            throw new Exception("修改失败");
        }
    }

    //根据用户id查询他发送的通知
    public List<Notice> selectMessage(Integer userId) throws Exception {
        return commonMapper.selectMessage(userId);
    }

    //查询自己的通知
    public List<Notice> receiveMessage(Integer userId) throws Exception {
        return commonMapper.receiveMessage(userId);
    }

    //删除一条通知
    public void deleteMessage(Integer userId,Integer noticeId) throws Exception {
        int n= commonMapper.deleteMessage(userId,noticeId);
        if(n!=1){
            throw new Exception("删除失败");
        }
    }

    //上传数据信息表查询
    public UploadInfo selectUploadInfo(Integer tableId) throws Exception {
        return commonMapper.selectUploadInfo(tableId);
    }

    //供求总体人数表查询
    public TotalNum selectTotalNum(Integer tableId) throws Exception {
        return commonMapper.selectTotalNum(tableId);
    }

    //产业需求人数表查询
    public IndustryNum selectIndustryNum(Integer tableId) throws Exception {
        return commonMapper.selectIndustryNum(tableId);
    }

    //用人单位性质需求人数表查询
    public EmployerNum selectEmployerNum(Integer tableId) throws Exception {
        return commonMapper.selectEmployerNum(tableId);
    }

    //职业供求人数表查询
    public ProfNum selectProfNum(Integer tableId) throws Exception {
        return commonMapper.selectProfNum(tableId);
    }

    //需求前十职业表查询
    public MostNeeded selectMostNeeded(Integer tableId) throws Exception {
        return commonMapper.selectMostNeeded(tableId);
    }

    //饱和前十职业表查询
    public LeastNeeded selectLeastNeeded(Integer tableId) throws Exception {
        return commonMapper.selectLeastNeeded(tableId);
    }

    //人员类别求职人数表查询
    public JobSeekerNum selectJobSeekerNum(Integer tableId) throws Exception {
        return commonMapper.selectJobSeekerNum(tableId);
    }

    //性别供求人数表查询
    public SexNum selectSexNum(Integer tableId) throws Exception {
        return commonMapper.selectSexNum(tableId);
    }

    //年龄供求人数表查询
    public AgeNum selectAgeNum(Integer tableId) throws Exception {
        return commonMapper.selectAgeNum(tableId);
    }

    //文化程度供求人数表查询
    public DegreeNum selectDegreeNum(Integer tableId) throws Exception {
        return commonMapper.selectDegreeNum(tableId);
    }

    //技术等级供求人数表查询
    public TechGradeNum selectTechGradeNum(Integer tableId) throws Exception {
        return commonMapper.selectTechGradeNum(tableId);
    }

    //根据用户id查询上传数据信息
    public List<UploadInfo> selectUploadInfoById(Integer userId) throws Exception {
        return commonMapper.selectUploadInfoById(userId);
    }

    //根据时间点查询简易调查期
    public SimpleUploadPeriod selectSimpleUploadPeriod(Date date) throws Exception {
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        return commonMapper.selectSimpleUploadPeriod(sqlDate);
    }

    //按id查询调查期
    public UploadPeriod selectUploadPeriod(Integer uploadPeriodId) throws Exception {
        return commonMapper.selectUploadPeriod(uploadPeriodId);
    }

    //按时间点查询调查期
    public UploadPeriod selectUploadPeriodByTime(java.sql.Date date) throws Exception {
        return commonMapper.selectUploadPeriodByTime(date);
    }

    //按时间段查询调查期
    public List<UploadPeriod> selectUploadPeriodByPeriod(java.sql.Date startDate, java.sql.Date endDate) throws Exception {
        return commonMapper.selectUploadPeriodByPeriod(startDate,endDate);
    }

    //查询所有上报时限
    public List<UploadPeriod> selectAllUploadPeriod() throws Exception {
        return commonMapper.selectAllUploadPeriod();
    }





    //取样分析
    public IndustryNum pieChart(Integer aimUserId,Integer uploadPeriodId)throws Exception{
        int a=provinceMapper.selectUsertype(aimUserId);
        if(a==3){
            List<UploadInfo> uploadInfos=selectUploadInfoById(aimUserId);
            for(int i=0;i<uploadInfos.size();i++){
                if(uploadInfos.get(i).getUploadPeriodId()==uploadPeriodId){
                    int tableId=uploadInfos.get(i).getTableId();
                    return selectIndustryNum(tableId);
                }
            }
            throw new Exception("PeriodId Errorr");
        }
        else if(a==2){
            List<UserInfoDisplay> sub=provinceService.selectSub(aimUserId);
            IndustryNum output=new IndustryNum();
            for(int i=0;i<sub.size();i++){
                List<UploadInfo> uploadInfos=selectUploadInfoById(aimUserId);
                for(int j=0;j<uploadInfos.size();j++){
                    if(uploadInfos.get(j).getUploadPeriodId()==uploadPeriodId){
                        int tableId=uploadInfos.get(j).getTableId();
                        IndustryNum temp=selectIndustryNum(tableId);
                        output=(IndustryNum)objectadd(output,temp);
                        break;
                    }
                }
            }
            output.setTableId(0);
            return  output;
        }
        else{
            throw new Exception("Can not create pieChart for province");
        }
    }

    //对两个相同类中的int属性相加的函数,用于图表汇总
    public Object objectadd(Object a,Object b)throws Exception{
        Field[] fielda=a.getClass().getDeclaredFields();
        Field[] fieldb=b.getClass().getDeclaredFields();
        if(a.getClass()!=b.getClass()){
            throw new Exception("Class type must be same");
        }
        for(int i=0;i<fielda.length;i++){
            fielda[i].setAccessible(true);
            fieldb[i].setAccessible(true);
            Object valuea=fielda[i].get(a);
            Object valueb=fieldb[i].get(b);
            fielda[i].set(a,(Integer)valuea+(Integer)valueb);
        }
        return a;
    }
}
