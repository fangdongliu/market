package cn.fdongl.market.market.mapper;


import cn.fdongl.market.market.entity.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MarketMapper {

    //用户首次保存，新建一条备案信息
    @Insert("INSERT INTO" +
            "t_record_info(region_emp_id,region_emp_name,region_name,region_emp_contact,region_emp_contact_mobi,region_emp_contact_num,region_emp_fax,state_flag,apply_date,examine_date)" +
            "VALUES(#{param1},#{regionEmpName},#{regionName},#{regionEmpContact},#{regionEmpContactMobi},#{regionEmpContactNum},#{regionEmpFax},#{stateFlag},#{applyDate},#{examineDate})")
    Integer insert(Integer userId,Record record,Integer stateFlag);

    //用户修改保存信息,或者上报备案信息
    @Update("UPDATE t_record_info SET" +
            "region_emp_name=#{regionEmpName}" +
            "region_name=#{regionName}" +
            "region_emp_contact=#{regionEmpContact}" +
            "region_emp_contact_mobi=#{regionEmpContactMobi}" +
            "region_emp_contact_num=#{regionEmpContactNum}" +
            "region_emp_fax=#{regionEmpFax}" +
            "state_flag=#{stateFlag}" +
            "apply_date=#{applyDate}" +
            "examine_date=#{examineDate}" +
            "where region_emp_id=#{param1} and state_flag=0")
    Integer update(Integer userId,Record record,Integer stateFlag);

    //根据用户id和state查询备案信息
    @Select("SELECT" +
            "region_emp_name AS regionEmpName," +
            "region_name AS regionName," +
            "region_emp_contact AS regionEmpContact," +
            "region_emp_contact_mobi AS regionEmpContactMobi," +
            "region_emp_contact_num AS regionEmpContactNum," +
            "region_emp_fax AS regionEmpFax," +
            "state_flag AS stateFlag," +
            "apply_date AS applyDate," +
            "examine_date AS examineDate" +
            "from t_record_info where region_emp_id=#{param1} and state_flag=#{param2}")
    Record select(Integer userId);
}
