package cn.fdongl.market.security.service;

import cn.fdongl.market.security.entity.Right;
import cn.fdongl.market.security.mapper.RightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class RightService {

    @Autowired
    RightMapper rightMapper;

    public Object getRights(){
        Map<Integer,Right> rights =  rightMapper.list();

        for (Iterator<Map.Entry<Integer, Right>> it = rights.entrySet().iterator(); it.hasNext();){
            Map.Entry<Integer, Right> item = it.next();
            if(item.getValue().getFather()!=null){
                Right r = rights.get(item.getValue().getFather());
                if(r!=null){
                    if(r.getChildren()==null){
                        r.setChildren(new ArrayList<Right>());
                    }
                    ((List)r.getChildren()).add(item.getValue());
                }
            }
        }

        for (Iterator<Map.Entry<Integer, Right>> it = rights.entrySet().iterator(); it.hasNext();){
            Map.Entry<Integer, Right> item = it.next();
            if(item.getValue().getFather()!=null){
                it.remove();
            }
        }

        return rights.values().toArray();

    }

    public void addRight(Right right) throws Exception {
        try{
            if(rightMapper.add(right)<=0){
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception("重复的name");
        }
    }

    public void enable(Integer rightId){

        rightMapper.enable(rightId);

    }

    public void disable(Integer rightId){

        rightMapper.disable(rightId);

    }

    public void modify(Right right){

        rightMapper.modify(right);

    }

    public Object info(Integer rightId)throws Exception{
        Right right = rightMapper.info(rightId);
        if(right==null){
            throw new Exception("无效的权限ID");
        }
        return right;
    }

}
