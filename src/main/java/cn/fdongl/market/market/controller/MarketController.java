package cn.fdongl.market.market.controller;

import cn.fdongl.market.common.service.CommonService;
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
@RequestMapping("/market")//检测点接口
public class MarketController extends ControllerBase {

    @Autowired
    MarketService marketService;

    @Autowired
    CommonService commonService;

    //监测点新建备案
    @PostMapping("/record/insert")
    public Object RecordInsert(AppUserDetail appUserDetail, Record record) throws Exception {
        record.setRegionEmpId(appUserDetail.getId());
        record.setCreateTime(new Date());
        record.setCreator(appUserDetail.getId());
        marketService.recordInsert(record);
        return success();
    }

    //监测点更新备案
    @PostMapping("/record/update")
    public Object RecordUpdate(AppUserDetail appUserDetail,Record record) throws Exception {
        record.setRegionEmpId(appUserDetail.getId());
        record.setCreateTime(new Date());
        record.setCreator(appUserDetail.getId());
        marketService.recordUpdate(record);
        return success();
    }

    //监测点查询备案（默认查询流程）
    @PostMapping("/record/select")
    public Object RecordSelect(AppUserDetail appUserDetail) throws Exception {
        Object data=marketService.recordSelect(appUserDetail.getId());
        return success(data);
    }

    //监测点新建数据上传
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
            TechGradeNum techGradeNum) throws Exception {
        SimpleUploadPeriod simpleUploadPeriod= commonService.selectSimpleUploadPeriod(new Date());
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
                techGradeNum);
        return success();
    }

    //监测点更新数据上传
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
            TechGradeNum techGradeNum) throws Exception {
        SimpleUploadPeriod simpleUploadPeriod= commonService.selectSimpleUploadPeriod(new Date());
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
                techGradeNum);
        return success();
    }
}
