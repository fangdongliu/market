package cn.fdongl.market.upload.controller;

import cn.fdongl.market.upload.service.UploadService;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload")
public class UploadController extends ControllerBase {

    @Autowired
    UploadService uploadService;

    @PostMapping("/toCountry")
    public Object hh(@RequestParam Integer periodId) throws Exception {
        Integer status = uploadService.isPeriodEnd(periodId);
        if (status == null) {
            throw new Exception("目标调查期未结束，上报失败");
        }else if (status == 2) {
            throw new Exception("上报失败，原因:目标调查期已上报");
        }else if(status==1){
            throw new Exception("正在上报，请等待");
        }else{
            uploadService.upload(periodId);
        }
        return success();
    }

}
