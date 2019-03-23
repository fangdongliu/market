package cn.fdongl.market.province.controller;

import cn.fdongl.market.province.entity.InnerUploadPeriod;
import cn.fdongl.market.province.entity.UploadPeriod;
import cn.fdongl.market.province.service.ProvinceService;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.fdongl.market.market.service.MarketService;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController extends ControllerBase {

    @Autowired
    ProvinceService provinceService;

    @Autowired
    MarketService marketService;

    //查询所有待审核的备案信息
    @PostMapping("/record/examineQuery")
    public Object RecordExamineQuery() throws Exception {
        return success(provinceService.recordExamineQuery());
    }

    //根据条件查询已通过的备案信息
    @PostMapping("/record/conditionalQuery")
    public Object RecordConditionalQuery(Integer state,String condition) throws Exception {
        return success(provinceService.recordConditionalQuery(state, condition));
    }

    //备案审核拒绝通过
    @PostMapping("/record/reject")
    public Object RecordReject(AppUserDetail appUserDetail,Integer aimId,String content) throws Exception {
        provinceService.recordReject(appUserDetail.getId(),aimId,content);
        return success();
    }

    //备案审核通过
    @PostMapping("/record/pass")
    public Object RecordPass(AppUserDetail appUserDetail,Integer aimId,String content){
        provinceService.recordPass(appUserDetail.getId(),aimId,content);
        return success();
    }

    //上传数据审核拒绝通过，为完成
    @PostMapping("/upload/reject")
    public Object UploadReject(AppUserDetail appUserDetail,Integer aimId,String content) throws Exception {
        provinceService.uploadReject(appUserDetail.getId(),aimId,content);
        return success();
    }

//    //上传数据审核通过，未完成
//    @PostMapping("/upload/pass")
//    public Object UploadPass(AppUserDetail appUserDetail,Integer aimId,String content){
//        provinceService.uploadPass(appUserDetail.getId(),aimId,content);
//        return success();
//    }

    //新建上报时限
    @PostMapping("/investigatePeriod/insert")
    public Object uploadPeriodInsert(AppUserDetail appUserDetail, UploadPeriod period){
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start=new java.sql.Date(format.parse(period.getStartDate()).getTime());
            Date end=new java.sql.Date(format.parse(period.getEndDate()).getTime());//将日期从字符串转换为日期类
            provinceService.timeCheck(start,end);
            InnerUploadPeriod innerPeriod=new InnerUploadPeriod();
            innerPeriod.setReviser(appUserDetail.getId());
            innerPeriod.setReviseTime(new java.util.Date());
            innerPeriod.setStartDate(start);
            innerPeriod.setEndDate(end);
            innerPeriod.setCreator(appUserDetail.getId());
            innerPeriod.setCreatTime(new java.util.Date());
            innerPeriod.setDeleteFlag(0);
            if(provinceService.periodInsert(innerPeriod)<=0){
                return fail(1);//插入失败则返回fail
            }
            else{
                return success(0);
            }
        }
        catch (Exception e){
            return fail(1);
        }
    }

    //修改上报时限
    @PostMapping("/investigatePeriod/update")
    public Object uploadPeriodUpdate(AppUserDetail appUserDetail, UploadPeriod period){
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start=new java.sql.Date(format.parse(period.getStartDate()).getTime());
            Date end=new java.sql.Date(format.parse(period.getEndDate()).getTime());//将日期从字符串转换为日期类
            provinceService.timeCheck(start,end);
            if(provinceService.periodUpdate(start,end,new java.util.Date(),appUserDetail.getId(),period.getUploadPeriodId())<=0){
                return fail(1);//update影响条数小于0则返回fail
            }
            else {
                return success(0);
            }
        }
        catch (Exception e){
            return fail(1);
        }
    }

    //按id查询上报时限
    @PostMapping("/investigatePeriod/selectById")
    public Object uploadPeriodSelectById(AppUserDetail appUserDetail,Integer uploadPeriodId){
        try{
            InnerUploadPeriod innerUploadPeriod=provinceService.uploadPeriodSelectById(uploadPeriodId);
            if(innerUploadPeriod==null){
                return fail(null,"No result");
            }
            else{
                UploadPeriod output=provinceService.InnerUploadPeriodTranform(innerUploadPeriod);
                if(output==null){
                    return fail("Unknown Error");
                }
                else{
                    return success(output);
                }
            }
        }
        catch (Exception e){
            return fail(null,"Unknown Error");
        }
    }

    //按时间点查询上报时限
    @PostMapping("/investigatePeriod/selectByTime")
    public Object uploadPeriodSelectByTime(AppUserDetail appUserDetail,String inputDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date date = new java.sql.Date(format.parse(inputDate).getTime());
            InnerUploadPeriod innerUploadPeriod = provinceService.uploadPeriodSelectByTime(date);
            if(innerUploadPeriod==null){
                return fail(null,"No result");
            }
            else{
                UploadPeriod output=provinceService.InnerUploadPeriodTranform(innerUploadPeriod);
                if(output==null){
                    return fail(null,"Unknown Error");
                }
                else{
                    return success(output);
                }
            }
        }
        catch (Exception e){
            return fail(null,"Unknown Error");
        }
    }

    //按时间段查询上报时限
    @PostMapping("/investigatePeriod/selectByPeriod")
    public Object uploadPeriodSelectByPeriod(AppUserDetail appUserDetail, UploadPeriod period){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date startDate = new java.sql.Date(format.parse(period.getStartDate()).getTime());
            java.sql.Date endDate = new java.sql.Date(format.parse(period.getEndDate()).getTime());
            List<InnerUploadPeriod> outPutList=provinceService.uploadPeriodSelectByPeriod(startDate,endDate);
            if(outPutList==null){
                return fail(null,"No Result");
            }
            else{
                List<UploadPeriod> output=new ArrayList<UploadPeriod>();
                for (InnerUploadPeriod innerUploadPeriod : outPutList) {
                    output.add(provinceService.InnerUploadPeriodTranform(innerUploadPeriod));
                }
                return success(output);
            }
        }
        catch (Exception e){
            return fail(null,"Unknown Error");
        }
    }

    //查询所有上报时限
    @PostMapping("/investigatePeriod/selectAllPeriod")
    public Object uploadPeriodSelectAll(AppUserDetail appUserDetail)throws Exception{
        return success(provinceService.uploadPeriodsSelectAll());
    }

    //查询目标用户报表
    @PostMapping("/data/selectMarketData")
    public Object SelectNowUserUploadInfo(AppUserDetail appUserDetail,Integer aimUserId)throws Exception{
        return success(marketService.UploadInfoSelectByUser(aimUserId));
    }

    //当前用户是省级用户时，查询用户下属的市级用户
    @PostMapping("/selectCityUser")
    public Object SelectCityUser(AppUserDetail appUserDetail)throws Exception{
        return success(provinceService.selectSub(appUserDetail.getId()));
    }

    //当前用户是市级用户时，返回所有下级监测点用户信息；当前用户是省级用户时，返回所有监测点用户信息
    //权限部分有待更改
    @PostMapping("/selectAllSub")
    public Object SelectAllSub(AppUserDetail appUserDetail)throws Exception{
        Integer type = provinceService.selectUsertype(appUserDetail.getId());
        if(type==1){
            return success(provinceService.selectAllMarket());
        }
        else if(type==2){
            return success(provinceService.selectSub(appUserDetail.getId()));
        }
        else {
            throw new Exception("Authority Error");
        }
    }

    //查询目标市级用户下属的监测点
    @PostMapping("/selectCitySub")
    public Object SelectCitySub(AppUserDetail appUserDetail,Integer aimUserId)throws Exception{
        return success(provinceService.selectSub(aimUserId));
    }


    //条件查询操作用户范围内的所有监测点,流程需要优化
    @PostMapping("/userSearch")
    public Object userSearch(AppUserDetail appUserDetail,String input)throws Exception{
        Integer type = provinceService.selectUsertype(appUserDetail.getId());
        if(type==1){
            return success(provinceService.userSearch(input));
        }
        else if(type==2){
            return success(provinceService.userSearchByuser(appUserDetail.getId(),input));
        }
        else {
            throw new Exception("Authority Error");
        }
    }

}