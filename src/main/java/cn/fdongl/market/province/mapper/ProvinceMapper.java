package cn.fdongl.market.province.mapper;


import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.market.entity.UploadInfo;
import cn.fdongl.market.province.entity.UploadPeriod;
import cn.fdongl.market.province.entity.UserInfoDisplay;
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
            "from t_record_info where state_flag=2 and region_emp_name like CONCAT('%',#{param1},'%');")
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
            "from t_record_info where state_flag=2 and region_name like CONCAT('%',#{param1},'%');")
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
            "from t_record_info where state_flag=2 and region_emp_contact like CONCAT('%',#{param1},'%');")
    List<Record> recordRegionEmpContactQuery(String condition);

    //省级备案未通过时更新数据
    @Update("UPDATE t_record_info \n" +
            "set state_flag=0,revise_time=now(),reviser=#{param1} \n" +
            "where region_emp_id=#{param2} and state_flag=1;")
    Integer recordUpdateReject(Integer provinceId,Integer aimId);

    //省级备案未通过时删除数据
    @Delete("DELETE FROM t_record_info \n" +
            "where region_emp_id=#{param1} and state_flag=1;")
    Integer recordDeleteReject(Integer aimId);

    //省级备案通过时更新数据
    @Update("UPDATE t_record_info \n" +
            "set state_flag=2,revise_time=now(),reviser=#{param1} \n" +
            "where region_emp_id=#{param2} and state_flag=1;")
    Integer recordUpdatePass(Integer provinceId,Integer aimId);

    //省级备案通过时更新过期数据
    @Update("UPDATE t_record_info \n" +
            "set state_flag=3,revise_time=now(),reviser=#{param1} \n" +
            "where region_emp_id=#{param2} and state_flag=2;")
    Integer recordUpdateExpirePass(Integer provinceId,Integer aimId);

    //激活账号
    @Update("UPDATE t_user \n" +
            "set state_flag=1 \n" +
            "where user_id=#{param1};")
    Integer recordUpdateActivation(Integer provinceId,Integer aimId);

    //根据id查询已通过备案的个数
    @Select("SELECT count(1) \n" +
            "from t_record_info where region_emp_id=#{param1} and state_flag=2;")
    Integer recordSelectNum(Integer userId);

    //省级待审核上传数据查询
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "upload_period_id AS uploadPeriodId, \n" +
            "state_flag AS stateFlag, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTime, \n" +
            "reviser AS reviser \n" +
            "from t_upload_info where state_flag=2);")
    List<UploadInfo> uploadExamineQuery();

    //省级上传数据未通过时更新数据
    @Update("UPDATE t_upload_info \n" +
            "set state_flag=0,revise_time=now(),reviser=#{param1} \n" +
            "where creator=#{param2} and state_flag=2;")
    Integer uploadUpdateReject(Integer provinceId,Integer aimId);

    //省级上传数据通过时更新数据
    @Update("UPDATE t_upload_info \n" +
            "set state_flag=3,revise_time=now(),reviser=#{param1} \n" +
            "where creator=#{param2} and state_flag=2;")
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







    //查询某用户所有的下级用户
    @Select("SELECT \n" +
            "user_id AS userId,\n" +
            "username AS username,\n" +
            "fullname AS fullname \n" +
            "from t_user where superior = #{param1};")
    List<UserInfoDisplay> selectAllSubCity(Integer aimUserId);

    //返回所有监测点用户信息
    @Select("SELECT \n" +
            "user_id AS userId,\n" +
            "username AS username,\n" +
            "fullname AS fullname \n" +
            "from t_user \n" +
            "where usertype = 3;")
    List<UserInfoDisplay> selectAllMarket();

    //查询当前用户类型
    @Select("SELECT \n" +
            "usertype \n" +
            "from t_user \n" +
            "where user_id = #{param1};")
    Integer selectUsertype(Integer aimUserId);

    //条件查询所有监测点
    @Select("SELECT \n" +
            "user_id AS userId,\n" +
            "username AS username,\n" +
            "fullname AS fullname \n" +
            "from t_user \n" +
            "where username like CONCAT('%',#{param1},'%') or fullname like CONCAT('%',#{param1},'%');")
    List<UserInfoDisplay> userSearch(String input);

    //条件查询某用户直接下属的用户
    @Select("SELECT \n" +
            "user_id AS userId,\n" +
            "username AS username,\n" +
            "fullname AS fullname \n" +
            "from t_user \n" +
            "where (superior = #{param1}) and (username like CONCAT('%',#{param2},'%') or fullname like CONCAT('%',#{param2},'%'));")
    List<UserInfoDisplay> userSearchByuser(Integer userId,String input);
}