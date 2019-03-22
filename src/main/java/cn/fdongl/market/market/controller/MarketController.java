package cn.fdongl.market.market.controller;


import cn.fdongl.market.market.entity.*;
import cn.fdongl.market.market.service.MarketService;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/market")//指定接口的一级路径
public class MarketController extends ControllerBase {

    @Autowired
    MarketService marketService;

    //新建备案
    @PostMapping("/record/insert")
    public Object RecordInsert(AppUserDetail appUserDetail, Record record) throws Exception {
        record.setRegionEmpId(appUserDetail.getId());
        record.setCreateTime(new Date());
        record.setCreator(appUserDetail.getId());
        marketService.recordInsert(record);
        return success();
    }

    //更新备案
    @PostMapping("/record/update")
    public Object RecordUpdate(AppUserDetail appUserDetail,Record record) throws Exception {
        record.setRegionEmpId(appUserDetail.getId());
        record.setCreateTime(new Date());
        record.setCreator(appUserDetail.getId());
        marketService.recordUpdate(record);
        return success();
    }

    //查询个人备案
    @PostMapping("/record/select")
    public Object RecordSelect(AppUserDetail appUserDetail) throws Exception {
        Object data=marketService.recordSelect(appUserDetail.getId());
        return success(data);
    }

    //新建数据上传
    @PostMapping("/upload/insert")
    public Object UploadInsert(
            AppUserDetail appUserDetail,
            Integer stateFlag,
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
            TechGrageNum techGrageNum) throws Exception {
        SimpleUploadPeriod simpleUploadPeriod=marketService.uploadSelectUploadPeriod(new Date());
        if(simpleUploadPeriod==null){
            throw new Exception("当前时间不在上传期内，无法上传数据");
        }
        UploadInfo uploadInfo=new UploadInfo();
        uploadInfo.setUploadPeriodId(simpleUploadPeriod.getUploadPeriodId());
        uploadInfo.setStateFlag(stateFlag);
        uploadInfo.setCreator(appUserDetail.getId());
        uploadInfo.setCreateTime(new Date());
        marketService.uploadInsert(
                uploadInfo,
                totalNum,
                industryNum,
                employerNum,
                profNum,
                mostNeeded,
                leastNeeded,
                jobSeekerNum,
                sexNum,
                ageNum,
                degreeNum,
                techGrageNum);
        return success();
    }

    //更新数据上传
    @PostMapping("/upload/update")
    public Object UploadUpdate(
            UploadInfo uploadInfo,
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
            TechGrageNum techGrageNum) throws Exception {
        SimpleUploadPeriod simpleUploadPeriod=marketService.uploadSelectUploadPeriod(new Date());
        if(uploadInfo.getUploadPeriodId()==null&&simpleUploadPeriod==null){
            throw new Exception("当前时间不在上传期内，无法上传数据");
        }
        uploadInfo.setUploadPeriodId(simpleUploadPeriod.getUploadPeriodId());
        uploadInfo.setCreateTime(new Date());
        marketService.uploadUpdate(
                uploadInfo,
                totalNum,
                industryNum,
                employerNum,
                profNum,
                mostNeeded,
                leastNeeded,
                jobSeekerNum,
                sexNum,
                ageNum,
                degreeNum,
                techGrageNum);
        return success();
    }

    //查询当前上传期
    @PostMapping("/data/uploadPeriodSelect")
    public SimpleUploadPeriod UploadSelectUploadPeriod(){
        return marketService.uploadSelectUploadPeriod(new Date());
    }

    //查询上传信息
    @PostMapping("data/uploadInfoSelect")
    public Object UploadInfoSelect(Integer tableId) throws Exception {
        return success(marketService.UploadInfoSelect(tableId));
    }

    //查询供求总体人数
    @PostMapping("/data/totalNumSelect")
    public Object TotalNumSelect(Integer tableId) throws Exception {
        return success(marketService.TotalNumSelect(tableId));
    }

    //查询产业需求人数
    @PostMapping("/data/industryNumSelect")
    public Object IndustryNumSelect(Integer tableId) throws Exception {
        return success(marketService.IndustryNumSelect(tableId));
    }

    //查询用人单位性质需求人数
    @PostMapping("/data/employerNum")
    public Object EmployerNumSelect(Integer tableId) throws Exception {
        return success(marketService.EmployerNumSelect(tableId));
    }

    //查询职业供求人数
    @PostMapping("/data/profNumSelect")
    public Object ProfNumSelect(Integer tableId) throws Exception {
        return success(marketService.ProfNumSelect(tableId));
    }

    //查询需求前十职业
    @PostMapping("/data/mostNeededSelect")
    public Object MostNeededSelect(Integer tableId) throws Exception {
        return success(marketService.MostNeededSelect(tableId));
    }

    //查询饱和前十职业
    @PostMapping("/data/leastNeededSelect")
    public Object LeastNeededSelect(Integer tableId) throws Exception {
        return success(marketService.LeastNeededSelect(tableId));
    }

    //查询人员类别求职人数
    @PostMapping("/data/jobSeekerNumSelect")
    public Object JobSeekerNumSelect(Integer tableId) throws Exception {
        return success(marketService.JobSeekerNumSelect(tableId));
    }

    //查询性别供求人数
    @PostMapping("/data/sexNumSelect")
    public Object SexNumSelect(Integer tableId) throws Exception {
        return success(marketService.SexNumSelect(tableId));
    }

    //查询年龄供求人数
    @PostMapping("/data/ageNumSelect")
    public Object AgeNumSelect(Integer tableId)throws Exception {
        return success(marketService.AgeNumSelect(tableId));
    }

    //查询文化程度供求人数
    @PostMapping("/data/degreeNumSelect")
    public Object DegreeNumSelect(Integer tableId) throws Exception {
        return success(marketService.DegreeNumSelect(tableId));
    }

    //查询技术等级供求人数
    @PostMapping("/data/techGrageNumSelect")
    public Object TechGrageNumSelect(Integer tableId) throws Exception {
        return success(marketService.TechGrageNumSelect(tableId));
    }
}
