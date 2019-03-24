package cn.fdongl.market.common.controller;


import cn.fdongl.market.common.service.CommonService;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/common")//指定接口的一级路径
public class CommonController extends ControllerBase {

    @Autowired
    CommonService commonService;

    //省级发送通知，全局发送
    @PostMapping("/message/send")
    public Object SendMessage(AppUserDetail appUserDetail, String title, String content) throws Exception {
        commonService.sendMessageGlobal(title,content,appUserDetail.getId());
        return success();
    }

    //省级修改通知
    @PostMapping("/message/update")
    public Object UpdateMessage(AppUserDetail appUserDetail,String title,String content,Integer noticeId) throws Exception {
        commonService.updateMessage(title,content,appUserDetail.getId(),noticeId);
        return success();
    }

    //省级查看自己发送的通知
    @PostMapping("message/select")
    public Object SelectMessage(AppUserDetail appUserDetail) throws Exception {
        return success(commonService.selectMessage(appUserDetail.getId()));
    }

    //省级删除通知
    @PostMapping("/message/delete")
    public Object UpdateMessage(AppUserDetail appUserDetail,Integer noticeId) throws Exception {
        commonService.deleteMessage(appUserDetail.getId(),noticeId);
        return success();
    }

    //查询当前简易上传期
    @PostMapping("selectSimpleUploadPeriod")
    public Object SelectSimpleUploadPeriod() throws Exception {
        return success(commonService.selectSimpleUploadPeriod(new Date()));
    }

    //查询单个监测点用户通过的上传信息
    @PostMapping("selectUploadInfo")
    public Object selectUploadInfo(Integer tableId) throws Exception {
        return success(commonService.selectUploadInfo(tableId));
    }

    //查询单个监测点用户通过的供求总体人数
    @PostMapping("selectTotalNum")
    public Object selectTotalNum(Integer tableId) throws Exception {
        return success(commonService.selectTotalNum(tableId));
    }

    //查询单个监测点用户通过的产业需求人数
    @PostMapping("selectIndustryNum")
    public Object selectIndustryNum(Integer tableId) throws Exception {
        return success(commonService.selectIndustryNum(tableId));
    }

    //查询单个监测点用户通过的用人单位性质需求人数
    @PostMapping("selectEmployerNum")
    public Object selectEmployerNum(Integer tableId) throws Exception {
        return success(commonService.selectEmployerNum(tableId));
    }

    //查询单个监测点用户通过的职业供求人数
    @PostMapping("selectProfNum")
    public Object selectProfNum(Integer tableId) throws Exception {
        return success(commonService.selectProfNum(tableId));
    }

    //查询单个监测点用户通过的需求前十职业
    @PostMapping("selectMostNeeded")
    public Object selectMostNeeded(Integer tableId) throws Exception {
        return success(commonService.selectMostNeeded(tableId));
    }

    //查询单个监测点用户通过的饱和前十职业
    @PostMapping("selectLeastNeeded")
    public Object selectLeastNeeded(Integer tableId) throws Exception {
        return success(commonService.selectLeastNeeded(tableId));
    }

    //查询单个监测点用户通过的人员类别求职人数
    @PostMapping("selectJobSeekerNum")
    public Object selectJobSeekerNum(Integer tableId) throws Exception {
        return success(commonService.selectJobSeekerNum(tableId));
    }

    //查询单个监测点用户通过的性别供求人数
    @PostMapping("selectSexNum")
    public Object selectSexNum(Integer tableId) throws Exception {
        return success(commonService.selectSexNum(tableId));
    }

    //查询单个监测点用户通过的年龄供求人数
    @PostMapping("selectAgeNum")
    public Object selectAgeNum(Integer tableId)throws Exception {
        return success(commonService.selectAgeNum(tableId));
    }

    //查询单个监测点用户通过的文化程度供求人数
    @PostMapping("selectDegreeNum")
    public Object selectDegreeNum(Integer tableId) throws Exception {
        return success(commonService.selectDegreeNum(tableId));
    }

    //查询单个监测点用户通过的技术等级供求人数
    @PostMapping("selectTechGradeNum")
    public Object selectTechGradeNum(Integer tableId) throws Exception {
        return success(commonService.selectTechGradeNum(tableId));
    }
}
