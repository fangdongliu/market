package cn.fdongl.market.market.controller;


import cn.fdongl.market.market.entity.*;
import cn.fdongl.market.market.mapper.MarketMapper;
import cn.fdongl.market.market.service.MarketService;
import cn.fdongl.market.security.entity.AppUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")//指定接口的一级路径
public class MarketController {

    @Autowired
    MarketMapper marketMapper;
    @Autowired
    MarketService marketService;

    //新建备案
    @PostMapping("/record/insert")
    public Integer RecordInsert(AppUserDetail appUserDetail, Record record,Integer stateFlag) throws Exception{
        record.setRegionEmpId(appUserDetail.getId());
        record.setStateFlag(stateFlag);
        record.setCreateTime();
        record.setCreator(appUserDetail.getId());
        record.setReviseTIme(null);
        record.setReviser(null);
        Integer n=marketMapper.recordInsert(record);
        if(n<=0){
            return 1;
        }
        return 0;
    }

    //更新备案
    @PostMapping("/record/update")
    public Integer RecordUpdate(AppUserDetail appUserDetail,Record record,Integer stateFlag) throws Exception{
        record.setRegionEmpId(appUserDetail.getId());
        record.setStateFlag(stateFlag);
        record.setCreateTime();
        record.setCreator(appUserDetail.getId());
        record.setReviseTIme(null);
        record.setReviser(null);
        Integer n=marketMapper.recordUpdate(record);
        if(n<=0){
            return 1;
        }
        return 0;
    }

    //查询备案
    @PostMapping("/record/select")
    public Record RecordSelect(AppUserDetail appUserDetail) throws Exception{
        return marketService.recordSelect(appUserDetail.getId());
    }

    //新建数据上传
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
            Integer stateFlag) throws Exception {
        UploadInfo uploadInfo=new UploadInfo();
        uploadInfo.setStateFlag(stateFlag);
        uploadInfo.setCreator(appUserDetail.getId());
        uploadInfo.setCreateTime();
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
        if(n<=0){
            throw new Exception();
        }
        return 0;
    }
}
