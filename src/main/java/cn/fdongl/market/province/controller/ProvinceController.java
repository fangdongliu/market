package cn.fdongl.market.province.controller;

import cn.fdongl.market.province.entity.InnerUploadPeriod;
import cn.fdongl.market.province.entity.uploadPeriod;
import cn.fdongl.market.province.service.ProvinceService;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/province")
public class ProvinceController extends ControllerBase {

    @Autowired
    ProvinceService provinceService;

    //查询所有待审核的备案信息
    @PostMapping("/record/examineQuery")
    public Object RecordExamineQuery(){
        try {
            Object data=provinceService.recordExamineQuery();
            return success(data);
        }catch (Exception e){
            return fail();
        }
    }

    //根据条件查询已通过的备案信息
    @PostMapping("/record/conditionalQuery")
    public Object RecordConditionalQuery(Integer state,String condition){
        try {
            Object data=provinceService.recordConditionalQuery(state, condition);
            return success(data);
        }catch (Exception e){
            return fail();
        }
    }

    //审核拒绝通过
    @PostMapping("/record/reject")
    public Object RecordReject(AppUserDetail appUserDetail,Integer aimId,String feedback){
        try {
            int n=provinceService.recordReject(appUserDetail.getId(),aimId,feedback);
            if(n!=0){
                return fail();
            }
            return success();
        }catch (Exception e){
            return fail();
        }
    }

    //审核通过
    @PostMapping("/record/pass")
    public Object RecordPass(AppUserDetail appUserDetail,Integer aimId,String feedback){
        try {
            int n=provinceService.recordPass(appUserDetail.getId(),aimId,feedback);
            if(n!=0){
                return fail();
            }
            return success();
        }catch (Exception e){
            return fail();
        }
    }
    //新建上报时限
    @PostMapping("/investigatePeriod/insert")
    public Object uploadPeriodInsert(AppUserDetail appUserDetail, uploadPeriod period){
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start=new java.sql.Date(format.parse(period.getStartDate()).getTime());
            Date end=new java.sql.Date(format.parse(period.getEndDate()).getTime());//将日期从字符串转换为日期类
            if(!provinceService.timeCheck(start,end)){
                return fail(2);//日期不合法则返回fail
            }
            InnerUploadPeriod innerPeriod=new InnerUploadPeriod();
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
    public Object uploadPeriodUpdate(AppUserDetail appUserDetail,uploadPeriod period){
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start=new java.sql.Date(format.parse(period.getStartDate()).getTime());
            Date end=new java.sql.Date(format.parse(period.getEndDate()).getTime());//将日期从字符串转换为日期类
            if(!provinceService.timeCheck(start,end)){
                return fail(2);//日期不合法则返回fail
            }
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

    @PostMapping("/investigatePeriod/selectById")
    public Object uploadPeriodSelectById(AppUserDetail appUserDetail,Integer uploadPeriodId){
        try{
            InnerUploadPeriod innerUploadPeriod=provinceService.uploadPeriodSelectById(uploadPeriodId);
            if(innerUploadPeriod==null){
                return fail(null,"No result");
            }
            else{
                uploadPeriod output=provinceService.InnerUploadPeriodTranform(innerUploadPeriod);
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

}