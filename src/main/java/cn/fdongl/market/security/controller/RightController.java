package cn.fdongl.market.security.controller;

import cn.fdongl.market.security.entity.Right;
import cn.fdongl.market.security.service.RightService;
import cn.fdongl.market.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/right")
public class RightController extends ControllerBase {

    @Autowired
    RightService rightService;

    @RequestMapping("/list")
    public Object list(){
        return success(rightService.getRights());
    }

    @RequestMapping("info")
    public Object info(@Valid Integer rightId) throws Exception {
        return success(rightService.info(rightId));
    }

    @RequestMapping("/add")
    public Object add(@Valid Right right) throws Exception {

        rightService.addRight(right);
        return success();

    }

    @RequestMapping("/enable")
    public Object enable(@RequestParam Integer rightId){

        rightService.enable(rightId);
        return success();

    }

    @RequestMapping("/disable")
    public Object disable(@RequestParam Integer rightId){

        rightService.disable(rightId);
        return success();

    }

    @RequestMapping("/modify")
    public Object modify(@Valid Right right){
        rightService.modify(right);
        return success();
    }

}