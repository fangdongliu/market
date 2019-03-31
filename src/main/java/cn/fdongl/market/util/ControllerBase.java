package cn.fdongl.market.util;

import org.springframework.context.annotation.Scope;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;

public class ControllerBase {


    protected ThreadLocal<Result> result = ThreadLocal.withInitial(Result::new);

    protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    protected SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    protected SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    protected Object fail(Object data,String message){
        Result rs = result.get();
        rs.setCode(1);
        rs.setData(data);
        rs.setMessage(message);
        return rs;
    }

    protected Object fail(Exception e){
        Result rs = result.get();
        rs.setCode(1);
        rs.setData(null);
        rs.setMessage(e.getMessage());
        return rs;
    }

    protected Object fail(Object data){
        Result rs = result.get();
        rs.setCode(1);
        rs.setData(data);
        rs.setMessage("unknown exception");
        return rs;
    }

    protected Object fail(){
        Result rs = result.get();
        rs.setCode(1);
        rs.setData(null);
        rs.setMessage("unknown exception");
        return rs;
    }

    protected Object success(Object data,String message){
        Result rs = result.get();
        rs.setCode(0);
        rs.setData(data);
        rs.setMessage(message);
        return rs;
    }

    protected Object success(Object data){
        Result rs = result.get();
        rs.setCode(0);
        rs.setData(data);
        rs.setMessage("");
        return rs;
    }

    protected Object success(){
        Result rs = result.get();
        rs.setCode(0);
        rs.setData(null);
        rs.setMessage("");
        return rs;
    }
}
