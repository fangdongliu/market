package cn.fdongl.market.province.mapper;


import cn.fdongl.market.market.entity.Record;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;
import java.util.List;

@Mapper
@Order(1)
public interface ProvinceMapper {

    //查询所有待审核的备案
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
            "from t_record_info where state_flag=1;")
    List<Record> recordExamineQuery();

    //查询监测点名称
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
            "from t_record_info where state_flag=2 and region_emp_name like #{param1};")
    List<Record> recordRegionEmpNameQuery(String condition);

    //查询地区名称
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
            "from t_record_info where state_flag=2 and region_name like #{param1};")
    List<Record> recordRegionNameQuery(String condition);

    //查询联系人名称
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
            "from t_record_info where state_flag=2 and region_emp_contact like #{param1};")
    List<Record> recordRegionEmpContactQuery(String condition);

    //备案未通过时更新数据
    @Update("update t_record_info \n" +
            "set state_flag=0,revise_time=now(),reviser=#{param1} \n" +
            "where region_emp_id=#{param2} and state_flag=1;")
    Integer recordUpdateReject(Integer examineId,Integer aimId);

    //备案未通过时删除数据
    @Delete("delete from t_record_info \n" +
            "where region_emp_id=#{param1} and state_flag=1;")
    Integer recordDeleteReject(Integer aimId);

    //备案通过时更新数据
    @Update("update t_record_info \n" +
            "set state_flag=2,revise_time=now(),reviser=#{param1} \n" +
            "where region_emp_id=#{param2} and state_flag=1;")
    Integer recordUpdatePass(Integer examineId,Integer aimId);

    //备案通过时更新过期数据
    @Update("update t_record_info \n" +
            "set state_flag=3,revise_time=now(),reviser=#{param1} \n" +
            "where region_emp_id=#{param2} and state_flag=2;")
    Integer recordUpdateExpirePass(Integer examineId,Integer aimId);

    //激活账号
    @Update("update t_user set state_flag=1 \n" +
            "where user_id=#{param1};")
    Integer recordUpdateActivation(Integer examineId,Integer aimId);

    //根据id查询已通过备案的个数
    @Select("select count(1) from t_record_info where region_emp_id=#{param1} and state_flag=2;")
    Integer recordSelectNum(Integer userId);

    //发送一条通知
    @Insert("insert into t_notice(notice_title,notice_content,create_time,creator,reciver) \n" +
            "values(#{param1},#{param2},now(),#{param3},#{param4});")
    Integer sendMessage(String title,String content,Integer examineId,Integer aimId);
}