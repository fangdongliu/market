package cn.fdongl.market.security.mapper;

import cn.fdongl.market.security.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;
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

    @Select("(SELECT user_id as id,username,fullname,usertype as userType,superior as father,state_flag as `status`,delete_flag as deleteFlag FROM t_user T1 WHERE T1.superior = #{param1})\n" +
            "UNION\n" +
            "(SELECT user_id as id,username,fullname,usertype as userType,superior as father,state_flag as `status`,delete_flag as deleteFlag FROM t_user WHERE superior IN (SELECT T1.user_id FROM t_user T1 WHERE T1.superior = #{param1}))\n")
    @MapKey("id")
    Map<Integer,ListUserData>userList(Integer userId);

    @Select("SELECT user_id as id,username,fullname,usertype as userType,superior as father,state_flag as `status`,delete_flag as deleteFlag FROM t_user")
    List<ListUserData>list();

    @Select("<script>" +
            "select user_id as id,username,fullname,usertype as userType,superior as father,state_flag as `status`,delete_flag as deleteFlag " +
            "from t_user where 1=1 " +
            "<if test='param3!=null'>and usertype = #{param3} </if>" +
            "<if test='param4!=null'>and username like #{param4} </if>" +
            "<if test='param5!=null'>and fullname like #{param5} </if>"+
            "limit #{param1},#{param2}"+
            "</script>")
    List<ListUserData>page(int begin,int count,Integer userType,String username,String fullname);

    @Select("<script>" +
            "select count(1)" +
            "from t_user where 1=1 " +
            "<if test='param1!=null'>and usertype = #{param1} </if>" +
            "<if test='param2!=null'>and username like #{param2} </if>" +
            "<if test='param3!=null'>and fullname like #{param3} </if>"+
            "</script>")
    Integer userCount(Integer userType,String username,String fullname);

    @Update("UPDATE t_user\n" +
            "SET delete_flag = 0 WHERE user_id = #{param1};")
    int enable(Integer rightId);

    @Update("UPDATE t_user\n" +
            "SET delete_flag = 1 WHERE user_id = #{param1};")
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
            "\tfullname,\n" +
            "\tusertype as userType,\n" +
            "\t`password`,\n" +
            "\tstate_flag AS `status`,\n" +
            "delete_flag as deleteFlag "+
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
            "            where t_role_right.role_id IN \n" +
            "            (SELECT t_role.role_id FROM t_role WHERE t_role.role_id IN \n" +
            "            (SELECT t_user_role.role_id FROM t_user_role WHERE t_user_role.user_id = #{param1})\n" +
            "            AND t_role.delete_flag = 0)\n" +
            "            ) AND t_right.delete_flag=0")
    List<String> getUserRights(String userId);

    @Select("SELECT t_right.menu_name as menuName,menu_path as menuPath,father_id as father,right_id as id\n" +
            "from t_right\n" +
            "WHERE t_right.right_id\n" +
            "IN(\n" +
            "SELECT t_role_right.right_id from t_role_right \n" +
            "where t_role_right.role_id IN \n" +
            "(SELECT t_role.role_id FROM t_role WHERE t_role.role_id IN \n" +
            "(SELECT t_user_role.role_id FROM t_user_role WHERE t_user_role.user_id = #{param1})\n" +
            "AND t_role.delete_flag = 0)\n" +
            ") AND t_right.delete_flag=0 AND menu_name is not null")
    @MapKey("id")
    Map<Integer, Right>getMenu(Integer userId);

    @Select("SELECT t_right.menu_name as menuName,menu_path as menuPath,father_id as father,right_id as id\n" +
            "from t_right where t_right.delete_flag=0 AND menu_name is not null")
    @MapKey("id")
    Map<Integer, Right>getSysMenu();

    @Insert("<script>" +
            "insert into t_user(username,password,usertype,fullname,superior,state_flag,create_time,creator,delete_flag)" +
            "values" +
            "<foreach collection=\"param1\" item=\"item\" index=\"index\" separator=\",\">" +
            "(#{item.username},#{param3},#{param5},#{item.fullname},#{param2},0,now(),#{param4},0)" +
            "</foreach>;" +
            "INSERT INTO t_user_role(role_id,user_id,creator,create_time,delete_flag) (\n" +
            "\tSELECT #{param6},user_id,#{param4},NOW(),0 FROM t_user WHERE superior = #{param2}\n" +
            ") ON DUPLICATE KEY UPDATE  creator=#{param4};" +

            "</script>")
    int addUsers(List<UsernameAndFullname>array,Integer parent,String password,Integer currentUser,Integer userType,Integer userRole);





    @Update("update t_user set password=#{param1} where user_id = #{param2}")
    int updatePassword(String password,int userId);

}
