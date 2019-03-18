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

    //新建一条备案信息
    @Insert("INSERT INTO \n" +
            "t_record_info(region_emp_id,region_emp_name,region_name,region_emp_contact,region_emp_contact_mobi,region_emp_contact_num,region_emp_fax,state_flag,create_time,creator,revise_time,reviser,delete_flag) \n" +
            "VALUES(#{regionEmpId},#{regionEmpName},#{regionName},#{regionEmpContact},#{regionEmpContactMobi},#{regionEmpContactNum},#{regionEmpFax},#{stateFlag},#{createTime},#{creator},#{reviseTime},#{reviser},0);")
    Integer recordInsert(Record record);

    //更新一条备案信息
    @Update("UPDATE t_record_info SET \n" +
            "region_emp_id=#{regionEmpId}, \n" +
            "region_emp_name=#{regionEmpName}, \n" +
            "region_name=#{regionName}, \n" +
            "region_emp_contact=#{regionEmpContact}, \n" +
            "region_emp_contact_mobi=#{regionEmpContactMobi}, \n" +
            "region_emp_contact_num=#{regionEmpContactNum}, \n" +
            "region_emp_fax=#{regionEmpFax}, \n" +
            "state_flag=#{stateFlag}, \n" +
            "create_time=#{createTime}, \n" +
            "creator=#{creator}, \n" +
            "revise_time=#{reviseTIme}, \n" +
            "reviser=#{reviser} \n" +
            "where region_emp_id=#{regionEmpId} and state_flag=0;")
    Integer recordUpdate(Record record);

    //根据用户id查已完成的备案信息
    @Select("SELECT \n" +
            "region_emp_id AS regionEmpId, \n" +
            "region_emp_name AS regionEmpName, \n" +
            "region_name AS regionName, \n" +
            "region_emp_contact AS regionEmpContact, \n" +
            "region_emp_contact_mobi AS regionEmpContactMobi, \n" +
            "region_emp_contact_num AS regionEmpContactNum, \n" +
            "region_emp_fax AS regionEmpFax, \n" +
            "state_flag AS stateFlag, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTIme, \n" +
            "reviser AS reviser \n" +
            "from t_record_info where state_flag=2;")
    Record recordSelectFinished(Integer userId);

    //根据用户id查询保存或上传的备案信息
    @Select("SELECT \n" +
            "region_emp_id AS regionEmpId, \n" +
            "region_emp_name AS regionEmpName, \n" +
            "region_name AS regionName, \n" +
            "region_emp_contact AS regionEmpContact, \n" +
            "region_emp_contact_mobi AS regionEmpContactMobi, \n" +
            "region_emp_contact_num AS regionEmpContactNum, \n" +
            "region_emp_fax AS regionEmpFax, \n" +
            "state_flag AS stateFlag, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTIme, \n" +
            "reviser AS reviser \n" +
            "from t_record_info where (state_flag=0 or state_flag=1);")
    Record recordSelectUnfinished(Integer userId);
}
