package cn.fdongl.market.province.mapper;


import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.province.entity.InnerUploadPeriod;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;

import java.sql.Date;
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

    //查询备案，根据监测点名称
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

    //查询备案，根据地区名称
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

    //查询备案，根据联系人名称
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
    @Update("UPDATE t_record_info \n" +
            "set state_flag=0,revise_time=now(),reviser=#{param1} \n" +
            "where region_emp_id=#{param2} and state_flag=1;")
    Integer recordUpdateReject(Integer examineId,Integer aimId);

    //备案未通过时删除数据
    @Delete("DELETE FROM t_record_info \n" +
            "where region_emp_id=#{param1} and state_flag=1;")
    Integer recordDeleteReject(Integer aimId);

    //备案通过时更新数据
    @Update("UPDATE t_record_info \n" +
            "set state_flag=2,revise_time=now(),reviser=#{param1} \n" +
            "where region_emp_id=#{param2} and state_flag=1;")
    Integer recordUpdatePass(Integer examineId,Integer aimId);

    //备案通过时更新过期数据
    @Update("UPDATE t_record_info \n" +
            "set state_flag=3,revise_time=now(),reviser=#{param1} \n" +
            "where region_emp_id=#{param2} and state_flag=2;")
    Integer recordUpdateExpirePass(Integer examineId,Integer aimId);

    //激活账号
    @Update("UPDATE t_user \n" +
            "set state_flag=1 \n" +
            "where user_id=#{param1};")
    Integer recordUpdateActivation(Integer examineId,Integer aimId);

    //根据id查询已通过备案的个数
    @Select("SELECT count(1) \n" +
            "from t_record_info where region_emp_id=#{param1} and state_flag=2;")
    Integer recordSelectNum(Integer userId);

    //发送一条通知
    @Insert("INSERT INTO t_notice \n" +
            "(notice_title,notice_content,create_time,creator,receiver,delete_flag) \n" +
            "values(#{param1},#{param2},now(),#{param3},#{param4},0);")
    Integer sendMessage(String title,String content,Integer examineId,Integer aimId);

    //新增调查期
    @Insert("INSERT INTO t_upload_period " +
            "(start_date,end_date,create_time,creator,revise_time,reviser,delete_flag) \n" +
            "values(#{startDate},#{endDate},#{creatTime},#{creator},#{reviseTime},#{reviser},#{deleteFlag});")
    Integer periodInsert(InnerUploadPeriod period);

    //修改调查期
    @Update("UPDATE t_upload_period \n" +
            "set start_date=#{param1},end_date=#{param2},revise_time=#{param3},reviser=#{param4} \n" +
            "where upload_period_id=#{param5};")
    Integer periodUpdate(java.sql.Date startDate, java.sql.Date endDate, java.util.Date reviseDate, Integer reviser, Integer uploadPeriodID);

    //时间点查询调查期
    @Select("SELECT \n" +
            "upload_period_id AS uploadPeriodId, \n" +
            "start_date AS startDate, \n" +
            "end_date AS endDate, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTime, \n" +
            "reviser AS reviser, \n" +
            "delete_flag AS deleteFlag \n" +
            "from t_upload_period \n" +
            "where #{param1} >= start_date and #{param1} < end_date limit 1;")
    InnerUploadPeriod selectByTime(Date aimDate);

    //时间段查询调查期
    @Select("SELECT \n" +
            "upload_period_id AS uploadPeriodId, \n" +
            "start_date AS startDate, \n" +
            "end_date AS endDate, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTime, \n" +
            "reviser AS reviser, \n" +
            "delete_flag AS deleteFlag \n" +
            "from t_upload_period \n" +
            "where #{param1}<end_date and start_date<=#{param2};")
    List<InnerUploadPeriod> selectByPeriod(java.sql.Date startDate,java.sql.Date endDate);

    //按id查询调查期
    @Select("SELECT \n" +
            "upload_period_id AS uploadPeriodId, \n" +
            "start_date AS startDate, \n" +
            "end_date AS endDate, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTime, \n" +
            "reviser AS reviser, \n" +
            "delete_flag AS deleteFlag \n" +
            "from t_upload_period \n" +
            "where upload_period_id=#{param1} limit 1;")
    InnerUploadPeriod selectById(Integer uploadPeriodID);

    //获取目前调查期数据条数
    @Select("SELECT \n" +
            "count(upload_period_id) \n" +
            "from t_upload_period limit 1;")
    Integer getPeriodNumber();

    //查询所有调查期
    @Select("SELECT \n" +
            "upload_period_id AS uploadPeriodId, \n" +
            "start_date AS startDate, \n" +
            "end_date AS endDate, \n" +
            "create_time AS createTime, \n" +
            "revise_time AS reviseTime" +
            "creator AS creator, \n" +
            "reviser AS reviser, \n" +
            "delete_flag AS deleteFlag \n" +
            "from t_upload_period;")
    List<InnerUploadPeriod> selectAllPeriod();
}