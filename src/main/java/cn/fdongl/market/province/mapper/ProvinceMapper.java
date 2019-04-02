package cn.fdongl.market.province.mapper;


import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.market.entity.UploadInfo;
import cn.fdongl.market.common.entity.UploadPeriod;
import cn.fdongl.market.market.entity.UploadInfoExtra;
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
            "from t_record_info where \n" +
            "delete_flag=0 \n" +
            "and state_flag=1;")
    List<Record> recordExamineQuery();

    //省级根据条件查询备案，监测点名称，地区名称，联系人名称
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
            "from t_record_info where \n" +
            "delete_flag=0 \n" +
            "and state_flag=2 \n" +
            "and ((region_emp_name like CONCAT('%',#{param1},'%')) \n" +
            "or (region_name like CONCAT('%',#{param1},'%')) \n" +
            "or (region_emp_contact like CONCAT('%',#{param1},'%')));")
    List<Record> recordConditionalQuery(String condition);

    //省级备案未通过时更新数据
    @Update("UPDATE t_record_info \n" +
            "set state_flag=0,revise_time=now(),reviser=#{param1} \n" +
            "where state_flag=1 and region_emp_id=#{param2};")
    Integer recordUpdateReject(Integer provinceId,Integer aimId);

    //省级备案未通过时删除数据
    @Delete("DELETE FROM t_record_info \n" +
            "where state_flag=1 and region_emp_id=#{param1};")
    Integer recordDeleteReject(Integer aimId);

    //省级备案通过时更新数据
    @Update("UPDATE t_record_info \n" +
            "set state_flag=2,revise_time=now(),reviser=#{param1} \n" +
            "where state_flag=1 and region_emp_id=#{param2};")
    Integer recordUpdatePass(Integer provinceId,Integer aimId);

    //省级备案通过时更新过期数据
    @Update("UPDATE t_record_info \n" +
            "set state_flag=3,revise_time=now(),reviser=#{param1} \n" +
            "where state_flag=2 and region_emp_id=#{param2};")
    Integer recordUpdateExpirePass(Integer provinceId,Integer aimId);

    //激活账号
    @Update("UPDATE t_user \n" +
            "set state_flag=1,revise_time=now(),reviser=#{param1} \n" +
            "where user_id=#{param2};")
    Integer recordUpdateActivation(Integer provinceId,Integer aimId);

    //根据id查询已通过备案的个数，结果为0或1
    @Select("SELECT count(1) \n" +
            "from t_record_info where \n" +
            "delete_flag=0 \n" +
            "and state_flag=2 and region_emp_id=#{param1};")
    Integer recordSelectNum(Integer userId);

    //省级查询待审核上传数据
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "upload_period_id AS uploadPeriodId, \n" +
            "t_upload_info.state_flag AS stateFlag, \n" +
            "t_upload_info.create_time AS createTime, \n" +
            "t_upload_info.creator AS creator, \n" +
            "t_upload_info.revise_time AS reviseTime, \n" +
            "t_upload_info.reviser AS reviser, \n" +
            "region_emp_name AS regionEmpName \n" +
            "from t_upload_info inner join t_record_info on t_upload_info.creator=t_record_info.region_emp_id where \n" +
            "t_upload_info.delete_flag=0 \n" +
            "and t_upload_info.state_flag=2;")
    List<UploadInfoExtra> uploadExamineQuery();

    //省级上传数据未通过时更新数据
    @Update("UPDATE t_upload_info \n" +
            "set state_flag=0,revise_time=now(),reviser=#{param1} \n" +
            "where state_flag=2 and creator=#{param2};")
    Integer uploadUpdateReject(Integer provinceId,Integer aimId);

    //省级上传数据通过时更新数据
    @Update("UPDATE t_upload_info \n" +
            "set state_flag=3,revise_time=now(),reviser=#{param1} \n" +
            "where state_flag=2 and creator=#{param2};")
    Integer uploadUpdatePass(Integer provinceId,Integer aimId);

    //省级新增调查期
    @Insert("INSERT INTO t_upload_period \n" +
            "(start_date,end_date,create_time,creator,delete_flag) \n" +
            "values(#{startDate},#{endDate},#{creatTime},#{creator},0);")
    Integer periodInsert(UploadPeriod uploadPeriod);

    //省级修改调查期
    @Update("UPDATE t_upload_period \n" +
            "set start_date=#{startDate},end_date=#{endDate},revise_time=#{reviseTime},reviser=#{reviser} \n" +
            "where upload_period_id=#{uploadPeriodId};")
    Integer periodUpdate(UploadPeriod uploadPeriod);

}