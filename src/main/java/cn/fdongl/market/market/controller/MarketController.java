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
    public Object RecordInsert(AppUserDetail appUserDetail, Record record){
        record.setRegionEmpId(appUserDetail.getId());
        record.setCreateTime(new Date());
        record.setCreator(appUserDetail.getId());
        try {
            Integer n=marketService.recordInsert(record);
            if(n<=0){
                return fail();
            }
            return success();
        }catch (Exception e){
            return fail();
        }
    }

    //更新备案
    @PostMapping("/record/update")
    public Object RecordUpdate(AppUserDetail appUserDetail,Record record){
        record.setRegionEmpId(appUserDetail.getId());
        record.setCreateTime(new Date());
        record.setCreator(appUserDetail.getId());
        try {
            Integer n=marketService.recordUpdate(record);
            if(n<=0){
                return fail();
            }
            return success();
        }catch (Exception e){
            return fail();
        }
    }

    //查询个人备案
    @PostMapping("/record/select")
    public Object RecordSelect(AppUserDetail appUserDetail){
        try {
            Object data=marketService.recordSelect(appUserDetail.getId());
            return success(data);
        }catch (Exception e){
            return fail();
        }
    }

    //新建数据上传,未完成
    @PostMapping("/upload/insert")
    public Integer UploadInsert(
            AppUserDetail appUserDetail,
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
            TechGrageNum techGrageNum,
            Integer stateFlag){
        UploadInfo uploadInfo=new UploadInfo();
        uploadInfo.setStateFlag(stateFlag);
        uploadInfo.setCreator(appUserDetail.getId());
        uploadInfo.setCreateTime(new Date());
        int n=marketService.uploadInsert(
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
        return 0;
    }
}
