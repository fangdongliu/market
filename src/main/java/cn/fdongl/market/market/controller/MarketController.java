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
        return success(marketService.recordSelect(appUserDetail.getId()));
    }

    //监测点新建数据上传
    @PostMapping("/upload/insert")
    public Object UploadInsert(AppUserDetail appUserDetail,UploadDataSet uploadDataSet) throws Exception {
        Integer activation=marketService.selectActivation(appUserDetail.getId());
        if(activation==0){
            throw new Exception("账号未激活，不能上传数据");
        }
        SimpleUploadPeriod simpleUploadPeriod= commonService.selectSimpleUploadPeriod(new Date());
        if(simpleUploadPeriod==null){
            throw new Exception("当前时间不在上传期内，无法上传数据");
        }
        uploadDataSet.setUploadPeriodId(simpleUploadPeriod.getUploadPeriodId());
        uploadDataSet.setCreateTime(new Date());
        uploadDataSet.setCreator(appUserDetail.getId());
        marketService.uploadInsert(uploadDataSet);
        return success();
    }

    //监测点更新数据上传
    @PostMapping("/upload/update")
    public Object UploadUpdate(AppUserDetail appUserDetail,UploadDataSet uploadDataSet) throws Exception {
        Integer activation=marketService.selectActivation(appUserDetail.getId());
        if(activation==0){
            throw new Exception("账号未激活，不能上传数据");
        }
        SimpleUploadPeriod simpleUploadPeriod=commonService.selectSimpleUploadPeriod(new Date());
        if(uploadDataSet.getUploadPeriodId()==null&&simpleUploadPeriod==null){
            throw new Exception("当前时间不在上传期内，无法上传数据");
        }
        uploadDataSet.setUploadPeriodId(simpleUploadPeriod.getUploadPeriodId());
        uploadDataSet.setCreateTime(new Date());
        uploadDataSet.setCreator(appUserDetail.getId());
        marketService.uploadUpdate(uploadDataSet);
        return success();
    }

    //监测点查询上传数据（默认流程）,只能查到保存和待审核状态
    @PostMapping("/upload/select")
    public Object UploadSelect(AppUserDetail appUserDetail) throws Exception {
        return success(marketService.uploadSelect(appUserDetail.getId()));
    }
}
