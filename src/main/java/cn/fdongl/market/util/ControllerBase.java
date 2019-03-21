package cn.fdongl.market.util;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;

public class ControllerBase {

    Result result = new Result();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    protected Object fail(Object data,String message){
        result.setCode(1);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    protected Object fail(Exception e){
        result.setCode(1);
        result.setData(null);
        result.setMessage(e.getMessage());
        return result;
    }

    protected Object fail(Object data){
        result.setCode(1);
        result.setData(data);
        result.setMessage("unknown exception");
        return result;
    }

    protected Object fail(){
        result.setCode(1);
        result.setData(null);
        result.setMessage("unknown exception");
        return result;
    }

    protected Object success(Object data,String message){
        result.setCode(0);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    protected Object success(Object data){
        result.setCode(0);
        result.setData(data);
        result.setMessage("");
        return result;
    }

    protected Object success(){
        result.setCode(0);
        result.setData(null);
        result.setMessage("");
        return result;
    }
}
