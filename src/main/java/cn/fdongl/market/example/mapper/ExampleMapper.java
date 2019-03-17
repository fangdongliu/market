package cn.fdongl.market.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * @author LiuFangdong
 */

@Mapper
@Order(1)
public interface ExampleMapper {

    /**
     *
     * @return 若返回结果只有一条纪录，若记录中只包含一个成员，则返回结果可以直接为目标类型，否则
     *  需要用Entity包装返回结果
     */
    @Select("select 1;")
    Integer getOne();

    //@Select("select 1,\"hh\"")
    //ExampleEntity getEntity();

    /**
     *
     * @return 若返回多条记录，则返回结果必须为List
     */
    @Select("select 1;")
    List<Integer>getListOne();


    /**
     * @return 返回结果为成功插入的条数
     */
    //@Insert("insert into table values(#{a},#{b})");
    //int insertSomething(ExampleEntity e);

    /*
     *  其他的包含@Delete,@Update
     *
     */

}
