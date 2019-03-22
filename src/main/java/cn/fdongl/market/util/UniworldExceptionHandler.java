package cn.fdongl.market.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UniworldExceptionHandler {

    Result result = new Result();

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exception(Exception ex){
        result.setMessage(ex.getMessage());
        result.setCode(1);
        return result;
    }

}
