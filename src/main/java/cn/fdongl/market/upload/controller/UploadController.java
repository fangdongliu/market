package cn.fdongl.market.upload.controller;

import cn.fdongl.market.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @RequestMapping("/hh")
    public Object hh() throws Exception {
        uploadService.upload();
        return 1;
    }

}
