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

    @PostMapping("/upload/selectUploadPeriod")
    public SimpleUploadPeriod UploadSelectUploadPeriod(){
        //TODO
        //InnerUploadPeriod innerUploadPeriod=marketService.UploadSelectUploadPeriod(new Date());
        return null;
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

        UploadInfo uploadInfo=new UploadInfo();
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
}
