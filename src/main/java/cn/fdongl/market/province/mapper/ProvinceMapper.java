package cn.fdongl.market.province.mapper;


import cn.fdongl.market.market.entity.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.Update;
import org.springframework.core.annotation.Order;

import java.util.List;

@Mapper
@Order(1)
public interface ProvinceMapper {

    //查询所有待审核的备案
    @Select("SELECT" +
            "region_emp_id AS regionEmpId" +
            "region_emp_name AS regionEmpName," +
            "region_name AS regionName," +
            "region_emp_contact AS regionEmpContact," +
            "region_emp_contact_mobi AS regionEmpContactMobi," +
            "region_emp_contact_num AS regionEmpContactNum," +
            "region_emp_fax AS regionEmpFax," +
            "state_flag AS stateFlag," +
            "create_time AS createTime," +
            "creater AS creater" +
            "revise_time AS reviseTIme," +
            "reviser AS reviser" +
            "from t_record_info where state_flag=1")
    List<Record> examineQuery();

    //根据条件查询已通过的备案，存储过程
    @Select("")
    List<Record> conditionalQuery(Integer state,String condition);

    //审核拒绝通过，存储过程
    @Select("")
    Integer reject(Integer examineId,Integer aimId);

    //审核通过，存储过程
    @Select("")
    Integer pass(Integer examineId,Integer aimId);
}