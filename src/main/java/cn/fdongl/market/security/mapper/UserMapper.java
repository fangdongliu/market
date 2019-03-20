package cn.fdongl.market.security.mapper;

import cn.fdongl.market.security.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Map;

@Mapper
@Order(1)
public interface UserMapper {

    @Select("select 1;")
    Integer test();

    @Insert("INSERT INTO t_user_right(right_id,user_id,create_time,creator,delete_flag,path)\n" +
            "VALUES(1,1,NOW(),1,0,'')")
    Integer test2();

    @Select("SELECT user_id as id,username,fullname,superior as father,state_flag as `status` FROM t_user WHERE t_user.user_id!=1&&t_user.user_id!=#{param1}")
    List<ListUserData>list(Integer userId);

    @Update("UPDATE t_user\n" +
            "SET state_flag = 0 WHERE user_id = #{param1};")
    int enable(Integer rightId);

    @Update("UPDATE t_user\n" +
            "SET state_flag = 1 WHERE user_id = #{param1};")
    int disable(Integer rightId);

    @Select("SELECT role_id as `id`," +
            "role_name as `name`," +
            "role_desc as `description` FROM t_role WHERE t_role.role_id IN (SELECT role_id from t_user_role WHERE t_user_role.user_id = #{param1});")
    List<Role>roleInfo(Integer userId);

    @Insert("<script>\n" +
            "DELETE FROM t_uesr_role WHERE t_user_role.user_id = #{param3};\n" +
            "INSERT INTO t_user_role (role_id,user_id,creator,create_time,delete_flag)VALUES\n" +
            "<foreach collection=\"param1\" item=\"item\" index=\"index\" separator=\",\">\n" +
            "(#{item},#{param3},#{param2},now(),0)\n" +
            "</foreach>\n" +
            "</script>")
    int setRoles(Integer[]roles,Integer currentUser,Integer userId);

    @Select("SELECT \n" +
            "\tuser_id AS id,\n" +
            "\tusername,\n" +
            "\t`password`,\n" +
            "\tstate_flag AS `status`\n" +
            "\t FROM t_user WHERE t_user.username = #{param1} AND `delete_flag`=0 limit 1;")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "rights",column = "id",many = @Many(select = "cn.fdongl.market.security.mapper.UserMapper.getUserRights"))
    })
    UserData getUserData(String username);

    @Select("SELECT t_right.right_name \n" +
            "            from t_right\n" +
            "            WHERE t_right.right_id\n" +
            "            IN(\n" +
            "            SELECT t_role_right.right_id from t_role_right \n" +
            "            where t_role_right.right_id IN \n" +
            "            (SELECT t_role.role_id FROM t_role WHERE t_role.role_id IN \n" +
            "            (SELECT t_user_role.role_id FROM t_user_role WHERE t_user_role.user_id = #{param1})\n" +
            "            AND t_role.delete_flag = 0)\n" +
            "            ) AND t_right.delete_flag=0")
    List<String> getUserRights(String userId);

    @Select("SELECT t_right.menu_name,menu_path,father_id,right_id\n" +
            "from t_right\n" +
            "WHERE t_right.right_id\n" +
            "IN(\n" +
            "SELECT t_role_right.right_id from t_role_right \n" +
            "where t_role_right.right_id IN \n" +
            "(SELECT t_role.role_id FROM t_role WHERE t_role.role_id IN \n" +
            "(SELECT t_user_role.role_id FROM t_user_role WHERE t_user_role.user_id = 1)\n" +
            "AND t_role.delete_flag = 0)\n" +
            ") AND t_right.delete_flag=0 AND menu_path != null")
    @MapKey("id")
    Map<Integer, Right>getMenu(Integer userId);

    @Select("SELECT t_right.menu_name,menu_path,father_id,right_id\n" +
            "from t_right where t_right.delete_flag=0 AND menu_path != null")
    @MapKey("id")
    Map<Integer, Right>getSysMenu();

    @Insert("<script>" +
            "insert into t_user(username,password,fullname,superior,state_flag,create_time,creator,delete_flag)" +
            "values" +
            "<foreach collection=\"param1\" item=\"item\" index=\"index\" separator=\",\">" +
            "(#{item.username},#{param3},#{item.fullname},#{param2},0,now(),#{param4},0)" +
            "</foreach></script>")
    int addUsers(List<UsernameAndFullname>array,Integer parent,String password,Integer currentUser);

}
