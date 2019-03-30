package cn.fdongl.market.upload.mapper.from;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

@Mapper
public interface SelectMapper {

    @Select("select `status` from t_upload_period where upload_period_id = #{param1} and end_date<CURRENT_DATE()")
    Integer isPeriodEnd(int periodId);

    @Update("update t_upload_period set status = #{param2} where upload_period_id = #{param1}")
    int updatePeriodStatus(int periodId,int status);

    @Insert("insert into middle.t_age_num (select * from human_resources_sys.t_age_num b " +
            "where b.table_id in (select table_id from human_resources_sys.t_upload_info c where c.upload_period_id=#{param1}))")
    int insertAgeNum(int periodId);

    @Insert("INSERT INTO middle.t_record_info \n" +
            "\t(SELECT a.table_id,a.upload_period_id,c.* FROM \n" +
            "\t\t(SELECT b.table_id,b.upload_period_id,b.creator FROM human_resources_sys.t_upload_info b WHERE b.upload_period_id = #{param1})a \n" +
            "INner JOIN human_resources_sys.t_record_info c ON a.creator = c.region_emp_id AND c.state_flag = 3)")
    int insertPeriodTables(int periodId);

    @Insert("insert into middle.t_upload_period (select * from human_resources_sys.t_upload_period b where b.upload_period_id = #{param1})")
    int insertUploadPeriod(int periodId);

    @Insert("insert into middle.t_career_dic (select * from human_resources_sys.t_career_dic b) ON DUPLICATE KEY\n" +
            " UPDATE career_name = b.career_name,delete_flag = b.delete_flag")
    int updateDict();

    @Insert("insert into middle.t_degree_num (select * from human_resources_sys.t_degree_num " +
            "where human_resources_sys.t_degree_num.table_id in (select table_id from t_upload_info where upload_period_id=#{param1}))")
    int insertDegreeNum(int periodId);

    @Insert("insert into middle.t_employer_num (select * from human_resources_sys.t_employer_num " +
            "where human_resources_sys.t_employer_num.table_id in (select table_id from t_upload_info where upload_period_id=#{param1}))")
    int insertEmployerNum(int periodId);

    @Insert("insert into middle.t_industry_num (select * from human_resources_sys.t_industry_num " +
            "where human_resources_sys.t_industry_num.table_id in (select table_id from t_upload_info where upload_period_id=#{param1}))")
    int insertIndustryNum(int periodId);

    @Insert("insert into middle.t_job_seeker_num (select * from human_resources_sys.t_job_seeker_num " +
            "where human_resources_sys.t_job_seeker_num.table_id in (select table_id from t_upload_info where upload_period_id=#{param1}))")
    int insertJobSeekerNum(int periodId);

    @Insert("insert into middle.t_least_needed (select * from human_resources_sys.t_least_needed " +
            "where human_resources_sys.t_least_needed.table_id in (select table_id from t_upload_info where upload_period_id=#{param1}))")
    int insertJobLeastNeeded(int periodId);

    @Insert("insert into middle.t_most_needed (select * from human_resources_sys.t_most_needed " +
            "where human_resources_sys.t_most_needed.table_id in (select table_id from t_upload_info where upload_period_id=#{param1}))")
    int insertMostNeeded(int periodId);

    @Insert("insert into middle.t_prof_num (select * from human_resources_sys.t_prof_num " +
            "where human_resources_sys.t_prof_num.table_id in (select table_id from t_upload_info where upload_period_id=#{param1}))")
    int insertProfNum(int periodId);

    @Insert("insert into middle.t_sex_num (select * from human_resources_sys.t_sex_num " +
            "where human_resources_sys.t_sex_num.table_id in (select table_id from t_upload_info where upload_period_id=#{param1}))")
    int insertSexNum(int periodId);

    @Insert("insert into middle.t_tech_grade_num (select * from human_resources_sys.t_tech_grade_num " +
            "where human_resources_sys.t_tech_grade_num.table_id in (select table_id from t_upload_info where upload_period_id=#{param1}))")
    int insertTechGradeNum(int periodId);

    @Insert("insert into middle.t_total_num (select * from human_resources_sys.t_total_num " +
            "where human_resources_sys.t_total_num.table_id in (select table_id from t_upload_info where upload_period_id=#{param1}))")
    int insertTotalNum(int periodId);

}
