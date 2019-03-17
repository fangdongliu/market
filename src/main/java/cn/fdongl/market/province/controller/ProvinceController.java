package cn.fdongl.market.province.controller;

import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.province.mapper.ProvinceMapper;
import cn.fdongl.market.security.entity.AppUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/province/record")
public class ProvinceController{

    @Autowired
    ProvinceMapper provinceMapper;

    //查询所有待审核的备案信息
    @PostMapping("/examineQuery")
    public List<Record> ExamineQuery(){
        return provinceMapper.examineQuery();
    }

    //根据条件查询已通过的备案信息
    @PostMapping("conditionalQuery")
    public List<Record> ConditionalQuery(Integer state,String condition){
        if(state!=0&&state!=1&&state!=2){
            return null;
        }
        return provinceMapper.conditionalQuery(state,condition);
    }

    //审核拒绝通过
    @PostMapping("/reject")
    public Integer Reject(HttpServletRequest request,Integer aimId) throws Exception{
        AppUserDetail appUserDetail= AppUserDetail.fromRequest(request);
        int n=provinceMapper.reject(appUserDetail.getId(),aimId);
        if(n==1){
            throw new Exception();
        }
        return 0;
    }

    //审核通过
    @PostMapping("/pass")
    public Integer Pass(HttpServletRequest request,Integer aimId) throws Exception{
        AppUserDetail appUserDetail= AppUserDetail.fromRequest(request);
        int n=provinceMapper.pass(appUserDetail.getId(),aimId);
        if(n==1){
            throw new Exception();
        }
        return 0;
    }

}