package cn.fdongl.market.data.controller;


import cn.fdongl.market.data.service.DataService;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/data")//指定接口的一级路径
public class DataController extends ControllerBase {

    @Autowired
    DataService dataService;

    //查询当前简易上传期
    @PostMapping("selectSimpleUploadPeriod")
    public Object SelectSimpleUploadPeriod() throws Exception {
        return success(dataService.selectSimpleUploadPeriod(new Date()));
    }

    //查询单个监测点用户通过的上传信息
    @PostMapping("selectUploadInfo")
    public Object selectUploadInfo(Integer tableId) throws Exception {
        return success(dataService.selectUploadInfo(tableId));
    }

    //查询单个监测点用户通过的供求总体人数
    @PostMapping("selectTotalNum")
    public Object selectTotalNum(Integer tableId) throws Exception {
        return success(dataService.selectTotalNum(tableId));
    }

    //查询单个监测点用户通过的产业需求人数
    @PostMapping("selectIndustryNum")
    public Object selectIndustryNum(Integer tableId) throws Exception {
        return success(dataService.selectIndustryNum(tableId));
    }

    //查询单个监测点用户通过的用人单位性质需求人数
    @PostMapping("selectEmployerNum")
    public Object selectEmployerNum(Integer tableId) throws Exception {
        return success(dataService.selectEmployerNum(tableId));
    }

    //查询单个监测点用户通过的职业供求人数
    @PostMapping("selectProfNum")
    public Object selectProfNum(Integer tableId) throws Exception {
        return success(dataService.selectProfNum(tableId));
    }

    //查询单个监测点用户通过的需求前十职业
    @PostMapping("selectMostNeeded")
    public Object selectMostNeeded(Integer tableId) throws Exception {
        return success(dataService.selectMostNeeded(tableId));
    }

    //查询单个监测点用户通过的饱和前十职业
    @PostMapping("selectLeastNeeded")
    public Object selectLeastNeeded(Integer tableId) throws Exception {
        return success(dataService.selectLeastNeeded(tableId));
    }

    //查询单个监测点用户通过的人员类别求职人数
    @PostMapping("selectJobSeekerNum")
    public Object selectJobSeekerNum(Integer tableId) throws Exception {
        return success(dataService.selectJobSeekerNum(tableId));
    }

    //查询单个监测点用户通过的性别供求人数
    @PostMapping("selectSexNum")
    public Object selectSexNum(Integer tableId) throws Exception {
        return success(dataService.selectSexNum(tableId));
    }

    //查询单个监测点用户通过的年龄供求人数
    @PostMapping("selectAgeNum")
    public Object selectAgeNum(Integer tableId)throws Exception {
        return success(dataService.selectAgeNum(tableId));
    }

    //查询单个监测点用户通过的文化程度供求人数
    @PostMapping("selectDegreeNum")
    public Object selectDegreeNum(Integer tableId) throws Exception {
        return success(dataService.selectDegreeNum(tableId));
    }

    //查询单个监测点用户通过的技术等级供求人数
    @PostMapping("selectTechGradeNum")
    public Object selectTechGradeNum(Integer tableId) throws Exception {
        return success(dataService.selectTechGradeNum(tableId));
    }
}
