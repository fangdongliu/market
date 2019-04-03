package cn.fdongl.market.city.mapper;

import cn.fdongl.market.market.entity.Record;
import cn.fdongl.market.market.entity.UploadInfo;
import cn.fdongl.market.market.entity.UploadInfoExtra;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.core.annotation.Order;

import java.util.List;

@Mapper
@Order(1)
public interface CityMapper {

    //市级根据条件查询备案，监测点名称，地区名称，联系人名称，只能查到下属
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
            "and region_emp_id in (select user_id from t_user where superior=#{param1}) \n" +
            "and ((region_emp_name like CONCAT('%',#{param2},'%')) \n" +
            "or (region_name like CONCAT('%',#{param2},'%')) \n" +
            "or (region_emp_contact like CONCAT('%',#{param2},'%')));")
    List<Record> recordConditionalQuery(Integer cityId,String condition);

    //市级待审核上传数据查询，只能查到下属
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
            "and t_upload_info.state_flag=1 \n" +
            "and (t_upload_info.creator in (select user_id from t_user where superior=#{param1}));")
    List<UploadInfoExtra> uploadExamineQuery(Integer cityId);

    //市级上传数据未通过时更新数据
    @Update("UPDATE t_upload_info \n" +
            "set state_flag=0,revise_time=now(),reviser=#{param1} \n" +
            "where state_flag=1 and creator=#{param2};")
    Integer uploadUpdateReject(Integer cityId,Integer aimId);

    //市级上传数据通过时更新数据
    @Update("UPDATE t_upload_info \n" +
            "set state_flag=2,revise_time=now(),reviser=#{param1} \n" +
            "where state_flag=1 and creator=#{param2};")
    Integer uploadUpdatePass(Integer cityId,Integer aimId);
}
