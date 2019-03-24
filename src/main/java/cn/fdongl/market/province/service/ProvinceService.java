package cn.fdongl.market.province.service;

import cn.fdongl.market.common.mapper.CommonMapper;
import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.market.entity.UploadInfo;
import cn.fdongl.market.province.entity.InnerUploadPeriod;
import cn.fdongl.market.province.entity.UploadPeriod;
import cn.fdongl.market.province.entity.UserInfoDisplay;
import cn.fdongl.market.province.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceService {

    @Autowired
    ProvinceMapper provinceMapper;

    @Autowired
    CommonMapper commonMapper;

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
        else throw new Exception("状态参数错误");
    }

    //审核未通过，事务
    @Transactional
    public void recordReject(Integer provinceId,Integer aimId,String content) throws RuntimeException {
        if(content==null){
            content="";
        }
        int n=provinceMapper.recordSelectNum(aimId);
        if(n==1){
            n=provinceMapper.recordDeleteReject(aimId);
            if(n!=1){
                throw new RuntimeException("删除备案信息失败");
            }
            n= commonMapper.sendMessage(
                    "您的备案修改未通过审核",
                    content,
                    provinceId,
                    aimId);
            if(n!=1){
                throw new RuntimeException("通知发送失败");
            }
        }
        else if(n==0){
            n=provinceMapper.recordUpdateReject(provinceId,aimId);
            if(n!=1){
                throw new RuntimeException("修改备案信息失败");
            }
            n= commonMapper.sendMessage(
                    "您的备案未通过审核",
                    content,
                    provinceId,
                    aimId);
            if(n!=1){
                throw new RuntimeException("通知发送失败");
            }
        }
        else throw new RuntimeException("查询失败");
    }

    //审核通过，事务
    @Transactional
    public void recordPass(Integer provinceId, Integer aimId, String content) throws RuntimeException {
        if(content==null){
            content="";
        }
        int n=provinceMapper.recordSelectNum(aimId);
        if(n==1){
            n=provinceMapper.recordUpdateExpirePass(provinceId,aimId);
            if(n!=1){
                throw new RuntimeException("更新过期备案信息失败");
            }
            n=provinceMapper.recordUpdatePass(provinceId, aimId);
            if(n!=1){
                throw new RuntimeException("修改备案信息失败");
            }
            n= commonMapper.sendMessage(
                    "您的备案修改已通过审核",
                    content,
                    provinceId,
                    aimId);
            if(n!=1){
                throw new RuntimeException("通知发送失败");
            }
        }
        else if(n==0){
            n=provinceMapper.recordUpdateActivation(provinceId,aimId);
            if(n!=1){
                throw new RuntimeException("修改激活状态失败");
            }
            n=provinceMapper.recordUpdatePass(provinceId,aimId);
            if(n!=1){
                throw new RuntimeException("修改备案信息失败");
            }
            n= commonMapper.sendMessage(
                    "您的备案已通过审核",
                    content,
                    provinceId,
                    aimId);
            if(n!=1){
                throw new RuntimeException("通知发送失败");
            }
        }
        else throw new RuntimeException("查询失败");
    }

    //省级查询待审核的上传数据，非事务
    public List<UploadInfo> uploadExamineQuery() throws Exception {
        return provinceMapper.uploadExamineQuery();
    }

    //上传数据审核未通过，事务
    @Transactional
    public void uploadReject(Integer provinceId,Integer aimId,String content) throws RuntimeException {
        int n=provinceMapper.uploadUpdateReject(provinceId,aimId);
        if(n!=1){
            throw new RuntimeException("修改上传数据失败");
        }
        n= commonMapper.sendMessage(
                "您的上传数据未通过审核",
                content,
                provinceId,
                aimId);
        if(n!=1){
            throw new RuntimeException("通知发送失败");
        }
    }

    //上传数据审核通过，事务
    @Transactional
    public void uploadPass(Integer provinceId,Integer aimId,String content) throws RuntimeException {
        int n=provinceMapper.uploadUpdatePass(provinceId,aimId);
        if(n!=1){
            throw new RuntimeException("修改上传数据失败");
        }
        n= commonMapper.sendMessage(
                "您的上传数据已通过审核",
                content,
                provinceId,
                aimId);
        if(n!=1){
            throw new RuntimeException("通知发送失败");
        }
    }

    //检查日期是否合法，不含数据库操作
    public void timeCheck(java.sql.Date startDate,java.sql.Date endDate) throws Exception {
        if(startDate==null||endDate==null){
            throw new Exception("日期不能为空");
        }
        if(startDate.compareTo(new java.sql.Date(System.currentTimeMillis()-86400000))<0){
            throw new Exception("日期至少要大于等于当前日期");
        }
        long difference=(endDate.getTime()-startDate.getTime())/86400000;
        if(difference<7||difference>30){
            throw new Exception("一个调查期应该在7到30天之间");
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

    //查询所有上报时限
    public List<UploadPeriod> uploadPeriodsSelectAll() throws Exception{
        List<InnerUploadPeriod> preOutput=provinceMapper.selectAllPeriod();
        if(preOutput==null||preOutput.size()==0){
            throw new Exception("No result");
        }
        else{
            List<UploadPeriod> output = new ArrayList<UploadPeriod>();
            for(int i=0;i<preOutput.size();i++){
                output.add(InnerUploadPeriodTranform(preOutput.get(i)));
            }
            return  output;
        }
    }

    //将InnerUploadPeriod对象转化为uploadPeriod对象
    public UploadPeriod InnerUploadPeriodTranform(InnerUploadPeriod input){
        try {
            if(input==null){
                return null;
            }
            UploadPeriod output = new UploadPeriod();
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
    //查询目标用户的直接下级
    public List<UserInfoDisplay> selectSub (Integer userId) throws Exception{
        List<UserInfoDisplay> output=provinceMapper.selectAllSubCity(userId);
        if(output==null){
            throw new Exception("No Result");
        }
        else{
            return output;
        }
    }
    //查询所有监测点用户信息
    public List<UserInfoDisplay> selectAllMarket()throws Exception{
        List<UserInfoDisplay> output=provinceMapper.selectAllMarket();
        if(output==null){
            throw new Exception("No Result");
        }
        else{
            return output;
        }
    }

    //查询目标用户类型，1省2市3监测点
    public Integer selectUsertype(Integer userId) throws Exception{
        Integer output=provinceMapper.selectUsertype(userId);
        if(output==null){
            throw new Exception("No usertype common");
        }
        else{
            return output;
        }
    }

    //条件查询所有用户
    public List<UserInfoDisplay> userSearch(String input) throws Exception{
        List<UserInfoDisplay> output=provinceMapper.userSearch(input);
        if(output==null){
            throw new Exception("No Result");
        }
        else{
            return output;
        }
    }
    //条件查询当前用户下属的监测点
    public List<UserInfoDisplay> userSearchByuser(Integer userId,String input) throws Exception{
        List<UserInfoDisplay> output=provinceMapper.userSearchByuser(userId,input);
        if(output==null){
            throw new Exception("No Result");
        }
        else{
            return output;
        }
    }
}
