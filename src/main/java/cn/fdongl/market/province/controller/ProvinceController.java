package cn.fdongl.market.province.controller;

import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.province.mapper.ProvinceMapper;
import cn.fdongl.market.province.service.ProvinceService;
import cn.fdongl.market.security.entity.AppUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController{

    @Autowired
    ProvinceMapper provinceMapper;
    @Autowired
    ProvinceService provinceService;

    //查询所有待审核的备案信息
    @PostMapping("/record/examineQuery")
    public List<Record> RecordExamineQuery(){
        return provinceMapper.recordExamineQuery();
    }

    //根据条件查询已通过的备案信息
    @PostMapping("/record/conditionalQuery")
    public List<Record> RecordConditionalQuery(Integer state,String condition){
        return provinceService.conditionalQuery(state, condition);
    }

    //审核拒绝通过
    @PostMapping("/record/reject")
    public Integer RecordReject(AppUserDetail appUserDetail,Integer aimId,String feedback) throws Exception{
        int n=provinceService.reject(appUserDetail.getId(),aimId,feedback);
        if(n==1){
            throw new Exception();
        }
        return 0;
    }

    //审核通过
    @PostMapping("/record/pass")
    public Integer RecordPass(AppUserDetail appUserDetail,Integer aimId,String feedback) throws Exception{
        int n=provinceService.pass(appUserDetail.getId(),aimId,feedback);
        if(n==1){
            throw new Exception();
        }
        return 0;
    }
}