package cn.fdongl.market.market.mapper;


import cn.fdongl.market.market.entity.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.core.annotation.Order;

@Mapper
@Order(1)
public interface MarketMapper {

    //用户新建一条备案信息
    @Insert("INSERT INTO" +
            "t_record_info(region_emp_id,region_emp_name,region_name,region_emp_contact,region_emp_contact_mobi,region_emp_contact_num,region_emp_fax,state_flag,create_time,creator,revise_time,reviser)" +
            "VALUES(#{regionEmpId},#{regionEmpName},#{regionName},#{regionEmpContact},#{regionEmpContactMobi},#{regionEmpContactNum},#{regionEmpFax},#{stateFlag},#{createTime},#{creator},#{reviseTime},#{reviser})")
    Integer insert(Record record);

    //用户更新一条备案信息
    @Update("UPDATE t_record_info SET" +
            "region_emp_id=#{regionEmpId}," +
            "region_emp_name=#{regionEmpName}," +
            "region_name=#{regionName}," +
            "region_emp_contact=#{regionEmpContact}," +
            "region_emp_contact_mobi=#{regionEmpContactMobi}," +
            "region_emp_contact_num=#{regionEmpContactNum}," +
            "region_emp_fax=#{regionEmpFax}," +
            "state_flag=#{stateFlag}," +
            "create_time=#{createTime}," +
            "creator=#{creator}," +
            "revise_time=#{reviseTIme}," +
            "reviser=#{reviser}" +
            "where region_emp_id=#{regionEmpId} and state_flag=0;")
    Integer update(Record record);

    //根据用户id查询备案信息,存储过程
    @Select("")
    Record select(Integer userId);
}
