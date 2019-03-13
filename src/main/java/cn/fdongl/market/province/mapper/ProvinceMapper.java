package cn.fdongl.market.province.mapper;


import cn.fdongl.market.market.entity.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProvinceMapper {
    //根据用户id审核通过或打回对应备案
    @Update("UPDATE t_record_info SET"+
            "state_flag=#{param2}"+
            "where region_emp_id=#{param1}")
    Integer stateUpdate(Integer userId,Integer stateFlag);

    //设置目标用户激活状态
    @Update("UPDATE t_user SET"+
            "state_flag=#{param2}"+
            "where user_id=#{param1}")
    Integer userStateUpdate(Integer userId,Integer stateFlag);

    @Select("")
    List<Record> examineQuery();

    @Select("")
    List<Record> conditionalQuery(Integer state,String condition);

    @Select("")
    Integer reject(Integer examineId,Integer aimId);

    @Select("")
    Integer pass(Integer examineId,Integer aimId);
}