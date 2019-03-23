package cn.fdongl.market.city.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.core.annotation.Order;

@Mapper
@Order(1)
public interface CityMapper {

    //市级上传数据未通过时更新数据
    @Update("UPDATE t_upload_info \n" +
            "set state_flag=0,revise_time=now(),reviser=#{param1} \n" +
            "where creator=#{param2} and state_flag=1;")
    Integer uploadUpdateReject(Integer examineId,Integer aimId);

    //市级上传数据通过时更新数据
    @Update("UPDATE t_upload_info \n" +
            "set state_flag=2,revise_time=now(),reviser=#{param1} \n" +
            "where creator=#{param2} and state_flag=1;")
    Integer uploadUpdatePass(Integer examineId,Integer aimId);
}
