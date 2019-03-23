package cn.fdongl.market.security.mapper;

import cn.fdongl.market.security.entity.Right;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
@Order(1)
public interface RightMapper {

    @Select("SELECT \n" +
            "\tright_id AS id,\n" +
            "\tright_name AS `label`,\n" +
            "\tright_desc AS description,\n" +
            "\tfather_id AS father,\n" +
            "\tdelete_flag AS `status`\n" +
            "\t FROM t_right;")
    @MapKey("id")
    Map<Integer,Right> list();

    @Select("SELECT \n" +
            "\tright_name AS `label`,\n" +
            "\tright_desc AS description,\n" +
            "\tdelete_flag AS `status`\n" +
            "\t FROM t_right where right_id=#{param1} limit 1;")
    Right info(Integer rightId);

    @Insert("INSERT INTO t_right\n" +
            "VALUES(NULL,#{label},#{description},#{father},#{menuName},#{menuPath},NOW(),1,NULL,NULL,0);")
    int add(Right right);

    @Update("UPDATE t_right SET right_name = #{label},right_desc=#{description},menu_name = #{menuName}" +
            ",menu_path = #{menuPath},revise_time=NOW(),reviser=1 WHERE right_id = #{id};")
    int modify(Right right);

    @Update("UPDATE t_right\n" +
            "SET delete_flag = 0 WHERE right_id = #{param1};")
    int enable(Integer rightId);

    @Update("UPDATE t_right\n" +
            "SET delete_flag = 1 WHERE right_id = #{param1};")
    int disable(Integer rightId);



}
