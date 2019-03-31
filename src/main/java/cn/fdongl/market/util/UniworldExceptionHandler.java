package cn.fdongl.market.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Log4j2
public class UniworldExceptionHandler {

    ThreadLocal<Result> result = ThreadLocal.withInitial(Result::new);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exception(Exception ex){
        Result rs = result.get();
        rs.setMessage(ex.getMessage());
        rs.setCode(1);
        return rs;
    }
}
