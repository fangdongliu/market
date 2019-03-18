package cn.fdongl.market.province.controller;

import cn.fdongl.market.province.service.ProvinceService;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}