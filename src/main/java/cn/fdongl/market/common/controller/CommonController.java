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

    //发送全局通知
    @PostMapping("/message/send")
    public Object SendMessage(AppUserDetail appUserDetail, String title, String content) throws Exception {
        commonService.sendMessageGlobal(title,content,appUserDetail.getId());
        return success();
    }

    //修改通知
    @PostMapping("/message/update")
    public Object UpdateMessage(AppUserDetail appUserDetail,String title,String content,Integer noticeId) throws Exception {
        commonService.updateMessage(title,content,appUserDetail.getId(),noticeId);
        return success();
    }

    //查看自己发送的通知
    @PostMapping("/message/select")
    public Object SelectMessage(AppUserDetail appUserDetail) throws Exception {
        return success(commonService.selectMessage(appUserDetail.getId()));
    }

    //接收通知（用户自己应该收到的通知）
    @PostMapping("/message/receive")
    public Object receiveMessage(AppUserDetail appUserDetail) throws Exception {
        return success(commonService.receiveMessage(appUserDetail.getId()));
    }

    //删除通知
    @PostMapping("/message/delete")
    public Object UpdateMessage(AppUserDetail appUserDetail,Integer noticeId) throws Exception {
        commonService.deleteMessage(appUserDetail.getId(),noticeId);
        return success();
    }

    //查询单个监测点用户通过的上传信息
    @PostMapping("/data/selectUploadInfo")
    public Object selectUploadInfo(Integer tableId) throws Exception {
        return success(commonService.selectUploadInfo(tableId));
    }

    //查询单个监测点用户通过的供求总体人数
    @PostMapping("/data/selectTotalNum")
    public Object selectTotalNum(Integer tableId) throws Exception {
        return success(commonService.selectTotalNum(tableId));
    }

    //查询单个监测点用户通过的产业需求人数
    @PostMapping("/data/selectIndustryNum")
    public Object selectIndustryNum(Integer tableId) throws Exception {
        return success(commonService.selectIndustryNum(tableId));
    }

    //查询单个监测点用户通过的用人单位性质需求人数
    @PostMapping("/data/selectEmployerNum")
    public Object selectEmployerNum(Integer tableId) throws Exception {
        return success(commonService.selectEmployerNum(tableId));
    }

    //查询单个监测点用户通过的职业供求人数
    @PostMapping("/data/selectProfNum")
    public Object selectProfNum(Integer tableId) throws Exception {
        return success(commonService.selectProfNum(tableId));
    }

    //查询单个监测点用户通过的需求前十职业
    @PostMapping("/data/selectMostNeeded")
    public Object selectMostNeeded(Integer tableId) throws Exception {
        return success(commonService.selectMostNeeded(tableId));
    }

    //查询单个监测点用户通过的饱和前十职业
    @PostMapping("/data/selectLeastNeeded")
    public Object selectLeastNeeded(Integer tableId) throws Exception {
        return success(commonService.selectLeastNeeded(tableId));
    }

    //查询单个监测点用户通过的人员类别求职人数
    @PostMapping("/data/selectJobSeekerNum")
    public Object selectJobSeekerNum(Integer tableId) throws Exception {
        return success(commonService.selectJobSeekerNum(tableId));
    }

    //查询单个监测点用户通过的性别供求人数
    @PostMapping("/data/selectSexNum")
    public Object selectSexNum(Integer tableId) throws Exception {
        return success(commonService.selectSexNum(tableId));
    }

    //查询单个监测点用户通过的年龄供求人数
    @PostMapping("/data/selectAgeNum")
    public Object selectAgeNum(Integer tableId)throws Exception {
        return success(commonService.selectAgeNum(tableId));
    }

    //查询单个监测点用户通过的文化程度供求人数
    @PostMapping("/data/selectDegreeNum")
    public Object selectDegreeNum(Integer tableId) throws Exception {
        return success(commonService.selectDegreeNum(tableId));
    }

    //查询单个监测点用户通过的技术等级供求人数
    @PostMapping("/data/selectTechGradeNum")
    public Object selectTechGradeNum(Integer tableId) throws Exception {
        return success(commonService.selectTechGradeNum(tableId));
    }

    //查询当前简易调查期
    @PostMapping("/data/selectSimpleUploadPeriod")
    public Object selectSimpleUploadPeriod() throws Exception {
        return success(commonService.selectSimpleUploadPeriod(new Date()));
    }

    //按id查询调查期
    @PostMapping("/data/selectUploadPeriod")
    public Object selectUploadPeriod(Integer uploadPeriodId) throws Exception {
        return success(commonService.selectUploadPeriod(uploadPeriodId));
    }

    //按时间点查询调查期
    @PostMapping("/data/selectUploadPeriodByTime")
    public Object selectUploadPeriodByTime(String dateString) throws Exception {
        java.sql.Date date = new java.sql.Date(dateFormat.parse(dateString).getTime());
        return success(commonService.selectUploadPeriodByTime(date));
    }

    //按时间段查询调查期
    @PostMapping("/data/selectUploadPeriodByPeriod")
    public Object selectUploadPeriodByPeriod(String startDateString,String endDateString) throws Exception {
        java.sql.Date startDate = new java.sql.Date(dateFormat.parse(startDateString).getTime());
        java.sql.Date endDate = new java.sql.Date(dateFormat.parse(endDateString).getTime());
        return success(commonService.selectUploadPeriodByPeriod(startDate,endDate));
    }

    //查询所有上报时限，待移动
    @PostMapping("/data/selectAllUploadPeriod")
    public Object selectAllUploadPeriod() throws Exception {
        return success(commonService.selectAllUploadPeriod());
    }

    //取样分析
    @PostMapping("data/pieChart")
    public Object pieChart(AppUserDetail appUserDetail,Integer aimUserId,Integer uploadPeriodId)throws Exception{
        return success(commonService.pieChart(aimUserId,uploadPeriodId));
    }
}
