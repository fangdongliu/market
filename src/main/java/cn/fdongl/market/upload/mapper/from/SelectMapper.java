package cn.fdongl.market.upload.mapper.from;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface SelectMapper {

    @Insert("insert into middle.t_age_num (select * from human_resources_sys.t_age_num b " +
            "where b.table_id in (select table_id from human_resources_sys.t_upload_info c where c.upload_period_id=#{param1}))")
    int insertAgeNum(int periodId);

    @Insert("insert into middle.t_career_dic (select * from human_resources_sys.t_career_dic b " +
            "where b.table_id in (select table_id from t_upload_info c where c.upload_period_id=#{param1}))")
    int insertCareerDic(int periodId);

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
