package cn.fdongl.market.common.controller;

import cn.fdongl.market.common.service.CommonService;
import cn.fdongl.market.common.entity.UploadPeriod;
import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/common")//通用接口
public class CommonController extends ControllerBase {

    @Autowired
    CommonService commonService;

    //发送全局通知
    @PostMapping("/message/send")
    public Object SendMessage(AppUserDetail appUserDetail,String title,String content) throws Exception {
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
    public Object ReceiveMessage(AppUserDetail appUserDetail) throws Exception {
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
    public Object SelectUploadInfo(Integer tableId) throws Exception {
        return success(commonService.selectUploadInfo(tableId));
    }

    //查询单个监测点用户通过的供求总体人数
    @PostMapping("/data/selectTotalNum")
    public Object SelectTotalNum(Integer tableId) throws Exception {
        return success(commonService.selectTotalNum(tableId));
    }

    //查询单个监测点用户通过的产业需求人数
    @PostMapping("/data/selectIndustryNum")
    public Object SelectIndustryNum(Integer tableId) throws Exception {
        return success(commonService.selectIndustryNum(tableId));
    }

    //查询单个监测点用户通过的用人单位性质需求人数
    @PostMapping("/data/selectEmployerNum")
    public Object SelectEmployerNum(Integer tableId) throws Exception {
        return success(commonService.selectEmployerNum(tableId));
    }

    //查询单个监测点用户通过的职业供求人数
    @PostMapping("/data/selectProfNum")
    public Object SelectProfNum(Integer tableId) throws Exception {
        return success(commonService.selectProfNum(tableId));
    }

    //查询单个监测点用户通过的需求前十职业
    @PostMapping("/data/selectMostNeeded")
    public Object SelectMostNeeded(Integer tableId) throws Exception {
        return success(commonService.selectMostNeeded(tableId));
    }

    //查询单个监测点用户通过的饱和前十职业
    @PostMapping("/data/selectLeastNeeded")
    public Object SelectLeastNeeded(Integer tableId) throws Exception {
        return success(commonService.selectLeastNeeded(tableId));
    }

    //查询单个监测点用户通过的人员类别求职人数
    @PostMapping("/data/selectJobSeekerNum")
    public Object SelectJobSeekerNum(Integer tableId) throws Exception {
        return success(commonService.selectJobSeekerNum(tableId));
    }

    //查询单个监测点用户通过的性别供求人数
    @PostMapping("/data/selectSexNum")
    public Object SelectSexNum(Integer tableId) throws Exception {
        return success(commonService.selectSexNum(tableId));
    }

    //查询单个监测点用户通过的年龄供求人数
    @PostMapping("/data/selectAgeNum")
    public Object SelectAgeNum(Integer tableId)throws Exception {
        return success(commonService.selectAgeNum(tableId));
    }

    //查询单个监测点用户通过的文化程度供求人数
    @PostMapping("/data/selectDegreeNum")
    public Object SelectDegreeNum(Integer tableId) throws Exception {
        return success(commonService.selectDegreeNum(tableId));
    }

    //查询单个监测点用户通过的技术等级供求人数
    @PostMapping("/data/selectTechGradeNum")
    public Object SelectTechGradeNum(Integer tableId) throws Exception {
        return success(commonService.selectTechGradeNum(tableId));
    }

    //按用户id和调查期id查询上传数据，只用户查询监测点用户
    @PostMapping("/data/selectUploadInfoById")
    public Object SelectUploadInfoById(Integer userId,Integer uploadPeriodId) throws Exception {
        return success(commonService.selectUploadInfoById(userId,uploadPeriodId));
    }

    //上传数据条件查询
    @PostMapping("/data/selectUploadInfoByCondition")
    public Object SelectUploadInfoByCondition(AppUserDetail appUserDetail,java.sql.Date startDate,java.sql.Date endDate,String condition) throws Exception {
        return success(commonService.selectUploadInfoByCondition(appUserDetail.getId(),startDate,endDate,condition));
    }

    //按调查期id、地点、市场名称查询上传数据（只能查到一条，@尚楠）
    @PostMapping("/data/selectUploadInfoBySpecificCondition")
    public Object SelectUploadInfoBySpecificCondition(AppUserDetail appUserDetail,Integer uploadPeriodId,String regionName,String regionEmpName) throws Exception {
        return success(commonService.selectUploadInfoBySpecificCondition(appUserDetail.getId(),uploadPeriodId,regionName,regionEmpName));
    }

    //按id查询调查期
    @PostMapping("/data/selectUploadPeriod")
    public Object SelectUploadPeriod(Integer uploadPeriodId) throws Exception {
        UploadPeriod uploadPeriod=commonService.selectUploadPeriod(uploadPeriodId);
        uploadPeriod.setStartDateString(dateFormat.format(uploadPeriod.getStartDate()));
        uploadPeriod.setEndDateString(dateFormat.format(uploadPeriod.getEndDate()));
        return success(uploadPeriod);
    }

    //查询当前调查期
    @PostMapping("/data/selectNowUploadPeriod")
    public Object SelectNowUploadPeriod() throws Exception {
        java.sql.Date sqlDate=new java.sql.Date(new Date().getTime());
        List<UploadPeriod> uploadPeriodList=commonService.selectUploadPeriodByTime(sqlDate,sqlDate);
        if(uploadPeriodList.size()==0){
            return success(null);
        }
        else if(uploadPeriodList.size()==1){
            UploadPeriod uploadPeriod=uploadPeriodList.get(0);
            uploadPeriod.setStartDateString(dateFormat.format(uploadPeriod.getStartDate()));
            uploadPeriod.setEndDateString(dateFormat.format(uploadPeriod.getEndDate()));
            return success(uploadPeriod);
        }
        else{
            throw new Exception("查询当前调查期发生错误");
        }
    }

    //按时间段查询调查期
    @PostMapping("/data/selectUploadPeriodByTime")
    public Object SelectUploadPeriodByTime(String startDateString,String endDateString) throws Exception {
        java.sql.Date startDate = new java.sql.Date(dateFormat.parse(startDateString).getTime());
        java.sql.Date endDate = new java.sql.Date(dateFormat.parse(endDateString).getTime());
        List<UploadPeriod> uploadPeriodList=commonService.selectUploadPeriodByTime(startDate,endDate);
        for(UploadPeriod uploadPeriod:uploadPeriodList) {
            uploadPeriod.setStartDateString(dateFormat.format(uploadPeriod.getStartDate()));
            uploadPeriod.setEndDateString(dateFormat.format(uploadPeriod.getEndDate()));
        }
        return success(uploadPeriodList);
    }

    //取样分析，生成产业需求人数信息饼图数据
    @PostMapping("/data/pieChartIndustryNum")
    public Object PieChartIndustryNum(AppUserDetail appUserDetail,Integer uploadPeriodId) throws Exception {
        return success(commonService.pieChartIndustryNum(appUserDetail.getId(),uploadPeriodId));
    }

    //取样分析，生成目标用户产业需求人数信息饼图数据
    @PostMapping("/data/aimUserPieChartIndustryNum")
    public Object AimUserPieChartIndustryNum(Integer aimUserId,Integer uploadPeriodId) throws Exception {
        return success(commonService.pieChartIndustryNum(aimUserId,uploadPeriodId));
    }

    //取样分析，生成供求总体人数折线图数据
    @PostMapping("/data/lineChartTotalNum")
    public Object LineChartTotalNum(AppUserDetail appUserDetail,String startDateString,String endDateString) throws Exception {
        java.sql.Date startDate = new java.sql.Date(dateFormat.parse(startDateString).getTime());
        java.sql.Date endDate = new java.sql.Date(dateFormat.parse(endDateString).getTime());
        return success(commonService.lineChartTotalNum(appUserDetail.getId(),startDate,endDate));
    }

    //取样分析，生成目标用户供求总体人数折线图数据
    @PostMapping("/data/aimUserLineChartTotalNum")
    public Object AimUserLineChartTotalNum(Integer aimUserId,String startDateString,String endDateString) throws Exception {
        java.sql.Date startDate = new java.sql.Date(dateFormat.parse(startDateString).getTime());
        java.sql.Date endDate = new java.sql.Date(dateFormat.parse(endDateString).getTime());
        return success(commonService.lineChartTotalNum(aimUserId,startDate,endDate));
    }

    //趋势分析，生成年龄供求人数折线图数据
    @PostMapping("/data/lineChartAgeNum")
    public Object LineChartAgeNum(AppUserDetail appUserDetail,String startDateString, String endDateString) throws Exception {
        java.sql.Date startDate = new java.sql.Date(dateFormat.parse(startDateString).getTime());
        java.sql.Date endDate = new java.sql.Date(dateFormat.parse(endDateString).getTime());
        return success(commonService.lineChartAgeNum(appUserDetail.getId(),startDate,endDate));
    }

    //趋势分析，生成目标用户年龄供求人数折线图数据
    @PostMapping("/data/aimUserLineChartAgeNum")
    public Object AimUserLineChartAgeNum(Integer aimUserId, String startDateString, String endDateString) throws Exception {
        java.sql.Date startDate = new java.sql.Date(dateFormat.parse(startDateString).getTime());
        java.sql.Date endDate = new java.sql.Date(dateFormat.parse(endDateString).getTime());
        return success(commonService.lineChartAgeNum(aimUserId,startDate,endDate));
    }
}
