package cn.fdongl.market.province.controller;

import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.province.mapper.ProvinceMapper;
import cn.fdongl.market.security.entity.AppUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/province/record")
public class ProvinceController{

    @Autowired
    ProvinceMapper provinceMapper;

    @PostMapping("/examineQuery")
    public List<Record> ExamineQuery(){
        return provinceMapper.examineQuery();
    }

    @PostMapping("conditionalQuery")
    public List<Record> ConditionalQuery(Integer state,String condition){
        return provinceMapper.conditionalQuery(state,condition);
    }

    @PostMapping("/reject")
    public Integer Reject(HttpServletRequest request,Integer aimId) throws Exception{
        AppUserDetail appUserDetail= AppUserDetail.fromRequest(request);
        int n=provinceMapper.reject(appUserDetail.getId(),aimId);
        if(n==1){
            throw new Exception();
        }
        return 0;
    }

    @PostMapping("/pass")
    public Integer Pass(HttpServletRequest request,Integer aimId) throws Exception{
        AppUserDetail appUserDetail= AppUserDetail.fromRequest(request);
        int n=provinceMapper.pass(appUserDetail.getId(),aimId);
//        if(record==null){
//            throw new Exception();
//        }//检查目标用户是否存在待审核的备案，无则抛出异常
//        Integer result=provinceMapper.stateUpdate(aimUserId,3);
//        if(result<=0){
//            throw new Exception();
//        }
//        Integer result2=provinceMapper.userStateUpdate(aimUserId,1);
//        if(result2 <=0){
//            throw new Exception();
//        }
        return 0;
    }
}