package cn.fdongl.market.province.controller;

import cn.fdongl.market.common.service.CommonService;
import cn.fdongl.market.common.entity.UploadPeriod;
import cn.fdongl.market.province.service.ProvinceService;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/province")//省级接口
public class ProvinceController extends ControllerBase {

    @Autowired
    ProvinceService provinceService;

    @Autowired
    CommonService commonService;

    //省级查询待审核的备案信息
    @PostMapping("/record/examineQuery")
    public Object RecordExamineQuery() throws Exception {
        return success(provinceService.recordExamineQuery());
    }

    //省级根据条件查询已通过的备案信息
    @PostMapping("/record/conditionalQuery")
    public Object RecordConditionalQuery(String condition) throws Exception {
        return success(provinceService.recordConditionalQuery(condition));
    }

    //省级备案审核拒绝通过
    @PostMapping("/record/reject")
    public Object RecordReject(AppUserDetail appUserDetail,Integer aimId,String content) throws Exception {
        provinceService.recordReject(appUserDetail.getId(),aimId,content);
        return success();
    }

    //省级备案审核通过
    @PostMapping("/record/pass")
    public Object RecordPass(AppUserDetail appUserDetail,Integer aimId,String content) throws Exception {
        provinceService.recordPass(appUserDetail.getId(),aimId,content);
        return success();
    }

    //省级查询待审核的上传数据
    @PostMapping("/upload/examineQuery")
    public Object UploadExamineQuery() throws Exception {
        return success(provinceService.uploadExamineQuery());
    }

    //省级上传数据审核拒绝通过
    @PostMapping("/upload/reject")
    public Object UploadReject(AppUserDetail appUserDetail,Integer aimId,String content) throws Exception {
        provinceService.uploadReject(appUserDetail.getId(),aimId,content);
        return success();
    }

    //省级上传数据审核通过
    @PostMapping("/upload/pass")
    public Object UploadPass(AppUserDetail appUserDetail,Integer aimId,String content) throws Exception {
        provinceService.uploadPass(appUserDetail.getId(),aimId,content);
        return success();
    }

    //省级新建上报时限
    @PostMapping("/uploadPeriod/insert")
    public Object UploadPeriodInsert(AppUserDetail appUserDetail, UploadPeriod uploadPeriod) throws Exception {
        uploadPeriod.setStartDate(new java.sql.Date(dateFormat.parse(uploadPeriod.getStartDateString()).getTime()));
        uploadPeriod.setEndDate(new java.sql.Date(dateFormat.parse(uploadPeriod.getEndDateString()).getTime()));
        uploadPeriod.setCreatTime(new java.util.Date());
        uploadPeriod.setCreator(appUserDetail.getId());
        provinceService.uploadPeriodInsert(uploadPeriod);
        return success();
    }

    //省级修改上报时限
    @PostMapping("/uploadPeriod/update")
    public Object UploadPeriodUpdate(AppUserDetail appUserDetail, UploadPeriod uploadPeriod) throws Exception {
        uploadPeriod.setStartDate(new java.sql.Date(dateFormat.parse(uploadPeriod.getStartDateString()).getTime()));
        uploadPeriod.setEndDate(new java.sql.Date(dateFormat.parse(uploadPeriod.getEndDateString()).getTime()));
        uploadPeriod.setReviseTime(new java.util.Date());
        uploadPeriod.setReviser(appUserDetail.getId());
        provinceService.uploadPeriodUpdate(uploadPeriod);
        return success();
    }

}