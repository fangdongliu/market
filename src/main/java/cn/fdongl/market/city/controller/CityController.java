package cn.fdongl.market.city.controller;


import cn.fdongl.market.city.service.CityService;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")//市级接口
public class CityController extends ControllerBase {

    @Autowired
    CityService cityService;

    //市级根据条件查询已通过的备案信息（只能查到下属）
    @PostMapping("/record/conditionalQuery")
    public Object RecordConditionalQuery(Integer state,String condition) throws Exception {
        return success(cityService.recordConditionalQuery(6,state, condition));
    }

    //市级上传数据审核拒绝通过
    @PostMapping("/upload/reject")
    public Object UploadReject(AppUserDetail appUserDetail, Integer aimId, String content) throws Exception {
        cityService.uploadReject(appUserDetail.getId(),aimId,content);
        return success();
    }

    //市级上传数据审核通过
    @PostMapping("/upload/pass")
    public Object UploadPass(AppUserDetail appUserDetail,Integer aimId,String content) throws Exception {
        cityService.uploadPass(appUserDetail.getId(),aimId,content);
        return success();
    }
}
