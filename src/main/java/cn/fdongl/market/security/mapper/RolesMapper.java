package cn.fdongl.market.security.mapper;

import cn.fdongl.market.security.entity.Right;
import cn.fdongl.market.security.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.core.annotation.Order;

import java.util.List;

@Mapper
@Order(1)
public interface RolesMapper {

    @Select("SELECT \n" +
            "\trole_id AS id,\n" +
            "\trole_name AS `name`,\n" +
            "\trole_desc AS description,\n" +
            "\tdelete_flag AS `status`\n" +
            "FROM t_role;")
    List<Role> list();

    @Select("SELECT \n" +
            "\trole_name AS `name`,\n" +
            "\trole_desc AS description,\n" +
            "\tdelete_flag AS `status`\n" +
            "FROM t_role where role_id=#{param1} limit 1")
    Role info(Integer roleId);

    @Insert("INSERT INTO t_role VALUES(NULL,#{name},#{description},NOW(),1,NULL,NULL,0);")
    int add(Role role);

    @Update("UPDATE t_role SET role_name = #{name},role_desc=#{description},revise_time=NOW(),reviser=1 WHERE role_id = #{id};")
    int modify(Role role);

    @Update("UPDATE t_role\n" +
            "SET delete_flag = 0 WHERE role_id = #{param1};")
    int enable(Integer rightId);

    @Update("UPDATE t_role\n" +
            "SET delete_flag = 1 WHERE role_id = #{param1};")
    int disable(Integer rightId);

    @Select("SELECT right_id FROM t_role_right WHERE role_id = #{param1}")
    List<Integer>rights(Integer roleId);


    @Insert("<script>\n" +
            "DELETE FROM t_role_right WHERE t_role_right.role_id = #{param2};\n" +
            "INSERT INTO t_role_right (role_id,creator,right_id,create_time,delete_flag)VALUES\n" +
            "<foreach collection=\"param1\" item=\"item\" index=\"index\" separator=\",\">\n" +
            "(#{param2},#{param3},#{item},now(),0)\n" +
            "</foreach>\n" +
            "</script>")
    int setRights(Integer[]rights,Integer roleId,Integer userId);


}
