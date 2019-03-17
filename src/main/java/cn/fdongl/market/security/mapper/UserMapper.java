package cn.fdongl.market.security.mapper;

import cn.fdongl.market.security.entity.AppUserDetail;
import cn.fdongl.market.security.entity.UserData;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select 1;")
    Integer test();

    @Insert("INSERT INTO t_user_right(right_id,user_id,create_time,creator,delete_flag,path)\n" +
            "VALUES(1,1,NOW(),1,0,'')")
    Integer test2();


    @Select("SELECT \n" +
            "\tuser_id AS id,\n" +
            "\tusername,\n" +
            "\t`password`,\n" +
            "\tstate_flag AS `status`\n" +
            "\t FROM t_user WHERE t_user.username = #{param1} AND `delete_flag`=0;")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "rights",column = "id",many = @Many(select = "cn.fdongl.market.security.mapper.UserMapper.getUserRights"))
    })
    UserData getUserData(String username);

    @Select("SELECT t_right.right_name \n" +
            "from t_right \n" +
            "WHERE t_right.right_id \n" +
            "IN\t(\n" +
            "\tSELECT t_role_right.right_id from t_role_right \n" +
            "\t\twhere t_role_right.right_id IN \n" +
            "\t\t(SELECT t_user_role.role_id FROM t_user_role WHERE t_user_role.user_id = #{param1})" +
            ") AND t_right.delete_flag=0")
    List<String> getUserRights(String userId);


}
