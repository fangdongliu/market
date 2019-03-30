package cn.fdongl.market.common.mapper;

import cn.fdongl.market.common.entity.Notice;
import cn.fdongl.market.market.entity.*;
import cn.fdongl.market.province.entity.UploadPeriod;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.core.annotation.Order;

import java.util.List;

@Mapper
@Order(1)
public interface CommonMapper {

    //省市审核时发送一条通知，单点发送
    @Insert("INSERT INTO t_notice \n" +
            "(notice_title,notice_content,create_time,creator,receiver,delete_flag) \n" +
            "values(#{param1},#{param2},now(),#{param3},#{param4},0);")
    Integer sendMessage(String title,String content,Integer examineId,Integer aimId);

    //发送一条通知，全局发送
    @Insert("INSERT INTO t_notice \n" +
            "(notice_title,notice_content,create_time,creator,delete_flag) \n" +
            "values(#{param1},#{param2},now(),#{param3},0);")
    Integer sendMessageGlobal(String title,String content,Integer userId);

    //更新一条通知
    @Update("UPDATE t_notice SET \n" +
            "notice_title=#{param1},notice_content=#{param2},reviser=#{param3},revise_time=now() \n" +
            "where notice_id=#{param4};")
    Integer updateMessage(String title,String content,Integer userId,Integer notice_id);

    //根据用户id查询他发送的通知
    @Select("SELECT \n" +
            "notice_id AS noticeId, \n" +
            "notice_title AS noticeTitle, \n" +
            "notice_content AS noticeContent, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTime, \n" +
            "reviser AS reviser, \n" +
            "receiver AS receiver \n" +
            "from t_notice where \n" +
            "delete_flag=0 \n" +
            "and creator=#{param1};")
    List<Notice> selectMessage(Integer userId);

    //查询自己的通知
    @Select("SELECT \n" +
            "notice_id AS noticeId, \n" +
            "notice_title AS noticeTitle, \n" +
            "notice_content AS noticeContent, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTime, \n" +
            "reviser AS reviser, \n" +
            "receiver AS receiver \n" +
            "from t_notice where \n" +
            "delete_flag=0 \n" +
            "and (((select @tmp:=usertype from t_user where t_user.user_id=t_notice.creator limit 1)=1) \n" +
            "or (@tmp=2 and #{param1} in (select user_id from t_user where t_user.superior=t_notice.creator)) \n" +
            "or (@tmp=3 and #{param1}=receiver));")
    List<Notice> receiveMessage(Integer userId);

    //删除一条通知
    @Update("UPDATE t_notice SET \n" +
            "delete_flag=1,reviser=#{param1},revise_time=now() \n" +
            "where notice_id=#{param2};")
    Integer deleteMessage(Integer userId,Integer noticeId);

    //查询上传数据信息
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "upload_period_id AS uploadPeriodId, \n" +
            "state_flag AS stateFlag, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTime, \n" +
            "reviser AS reviser \n" +
            "from t_upload_info where \n" +
            "delete_flag=0 \n" +
            "and table_id=#{param1} limit 1;")
    UploadInfo selectUploadInfo(Integer tableId);

    //查询供求总体人数信息
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "need_popu AS needPopu, \n" +
            "jobseek_popu AS jobseekPopu \n" +
            "from t_total_num where \n" +
            "(select delete_flag from t_upload_info where t_upload_info.table_id=t_total_num.table_id limit 1)=0 \n" +
            "and table_id=#{param1} limit 1;")
    TotalNum selectTotalNum(Integer tableId);

    //查询产业需求人数表
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "industry1_need AS industry1Need, \n" +
            "industry2_need AS industry2Need, \n" +
            "industry3_need AS industry3Need, \n" +
            "mine_need AS mineNeed, \n" +
            "manu_need AS manuNeed, \n" +
            "elec_gas_water_need AS elecGasWaterNeed, \n" +
            "arch_need AS archNeed, \n" +
            "tran_stor_post_need AS tranStorPostNeed, \n" +
            "info_comp_soft_need AS infoCompSoftNeed, \n" +
            "retail_need AS retailNeed, \n" +
            "acco_cater_need AS accoCaterNeed, \n" +
            "finance_need AS financeNeed, \n" +
            "estate_need AS estateNeed, \n" +
            "lease_busi_serv_need AS leaseBusiservNeed, \n" +
            "rese_tech_addr_need AS reseTechAddrNeed, \n" +
            "water_envi_faci_need AS waterEnviFaciNeed, \n" +
            "resi_serv_need AS resiServNeed, \n" +
            "edu_need AS eduNeed,\n" +
            "heal_secu_welf_need AS healSecuWelfNeed, \n" +
            "cult_sport_ente_need AS cultSportEnteNeed, \n" +
            "mana_orga_need AS manaOrgaNeed, \n" +
            "inte_orga_need AS inteOrgaNeed \n" +
            "from t_industry_num where \n" +
            "(select delete_flag from t_upload_info where t_upload_info.table_id=t_industry_num.table_id limit 1)=0 \n" +
            "and table_id=#{param1} limit 1;")
    IndustryNum selectIndustryNum(Integer tableId);

    //查询用人单位单性质需求人数表
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "sta_own_need AS staOwnNeed, \n" +
            "coll_need AS collNeed, \n" +
            "coop_stock_need AS coopStockNeed, \n" +
            "joint_need AS jointNeed, \n" +
            "limi_liab_need AS limiLiabNeed, \n" +
            "limited_need AS limitedNeed, \n" +
            "priv_need AS privNeed, \n" +
            "other_ente_need AS otherEnteNeed, \n" +
            "hmt_inve_need AS hmtInveNeed, \n" +
            "fore_inve_ente AS foreInveEnte, \n" +
            "indi_need AS indiNeed, \n" +
            "inst_need AS instNeed, \n" +
            "orga_need AS orgaNeed, \n" +
            "other_need AS otherNeed \n" +
            "from t_employer_num where \n" +
            "(select delete_flag from t_upload_info where t_upload_info.table_id=t_employer_num.table_id limit 1)=0 \n" +
            "and table_id=#{param1} limit 1;")
    EmployerNum selectEmployerNum(Integer tableId);

    //查询职业供求人数表
    @Select("SELECT \n" +
            "table_id AS tableID, \n" +
            "leader_need AS leaderNeed, \n" +
            "leader_jobseek AS leaderJobseek, \n" +
            "prof_tech_need AS profTechNeed, \n" +
            "prof_tech_jobseek AS profTechJobseek, \n" +
            "staff_rela_need AS staffRelaNeed, \n" +
            "staff_rela_jobseek AS staffRelaJobseek, \n" +
            "busi_serv_need AS busiServNeed, \n" +
            "busi_serv_jobseek AS busiServJobseek, \n" +
            "prod_need AS prodNeed, \n" +
            "prod_jobseek AS prodJobseek, \n" +
            "oper_need AS operNeed, \n" +
            "oper_jobseek AS operJobseek, \n" +
            "other_need AS otherNeed, \n" +
            "other_jobseek AS otherJobseek, \n" +
            "no_requ_jobseek AS noRequJobseek \n" +
            "from t_prof_num where \n" +
            "(select delete_flag from t_upload_info where t_upload_info.table_id=t_prof_num.table_id limit 1)=0 \n" +
            "and table_id=#{param1} limit 1;")
    ProfNum selectProfNum(Integer tableId);

    //查询需求前十职业表
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "most_prof1_name AS mostProf1Name, \n" +
            "most_prof1_num AS mostProf1Num, \n" +
            "most_prof1_need AS mostProf1Need, \n" +
            "most_prof1_jobseek AS mostProf1Jobseek, \n" +
            "most_prof2_name AS mostProf2Name, \n" +
            "most_prof2_num AS mostProf2Num, \n" +
            "most_prof2_need AS mostProf2Need, \n" +
            "most_prof2_jobseek AS mostProf2Jobseek, \n" +
            "most_prof3_name AS mostProf3Name, \n" +
            "most_prof3_num AS mostProf3Num, \n" +
            "most_prof3_need AS mostProf3Need, \n" +
            "most_prof3_jobseek AS mostProf3Jobseek, \n" +
            "most_prof4_name AS mostProf4Name, \n" +
            "most_prof4_num AS mostProf4Num, \n" +
            "most_prof4_need AS mostProf4Need, \n" +
            "most_prof4_jobseek AS mostProf4Jobseek, \n" +
            "most_prof5_name AS mostProf5Name, \n" +
            "most_prof5_num AS mostProf5Num, \n" +
            "most_prof5_need AS mostProf5Need, \n" +
            "most_prof5_jobseek AS mostProf5Jobseek, \n" +
            "most_prof6_name AS mostProf6Name, \n" +
            "most_prof6_num AS mostProf6Num, \n" +
            "most_prof6_need AS mostProf6Need, \n" +
            "most_prof6_jobseek AS mostProf6Jobseek, \n" +
            "most_prof7_name AS mostProf7Name, \n" +
            "most_prof7_num AS mostProf7Num, \n" +
            "most_prof7_need AS mostProf7Need, \n" +
            "most_prof7_jobseek AS mostProf7Jobseek, \n" +
            "most_prof8_name AS mostProf8Name, \n" +
            "most_prof8_num AS mostProf8Num, \n" +
            "most_prof8_need AS mostProf8Need, \n" +
            "most_prof8_jobseek AS mostProf8Jobseek, \n" +
            "most_prof9_name AS mostProf9Name, \n" +
            "most_prof9_num AS mostProf9Num, \n" +
            "most_prof9_need AS mostProf9Need, \n" +
            "most_prof9_jobseek AS mostProf9Jobseek, \n" +
            "most_prof10_name AS mostProf10Name, \n" +
            "most_prof10_num AS mostProf10Num, \n" +
            "most_prof10_need AS mostProf10Need, \n" +
            "most_prof10_jobseek AS mostProf10Jobseek \n" +
            "from t_most_needed where \n" +
            "(select delete_flag from t_upload_info where t_upload_info.table_id=t_most_needed.table_id limit 1)=0 \n" +
            "and table_id=#{param1} limit 1;")
    MostNeeded selectMostNeeded(Integer tableId);

    //查询饱和前十职业表
    @Select("SELECT \n" +
            "least_prof1_name AS leastProf1Name, \n" +
            "least_prof1_num AS leastProf1Num, \n" +
            "least_prof1_need AS leastProf1Need, \n" +
            "least_prof1_jobseek AS leastProf1Jobseek, \n" +
            "least_prof2_name AS leastProf2Name, \n" +
            "least_prof2_num AS leastProf2Num, \n" +
            "least_prof2_need AS leastProf2Need, \n" +
            "least_prof2_jobseek AS leastProf2Jobseek, \n" +
            "least_prof3_name AS leastProf3Name, \n" +
            "least_prof3_num AS leastProf3Num, \n" +
            "least_prof3_need AS leastProf3Need, \n" +
            "least_prof3_jobseek AS leastProf3Jobseek, \n" +
            "least_prof4_name AS leastProf4Name, \n" +
            "least_prof4_num AS leastProf4Num, \n" +
            "least_prof4_need AS leastProf4Need, \n" +
            "least_prof4_jobseek AS leastProf4Jobseek, \n" +
            "least_prof5_name AS leastProf5Name, \n" +
            "least_prof5_num AS leastProf5Num, \n" +
            "least_prof5_need AS leastProf5Need, \n" +
            "least_prof5_jobseek AS leastProf5Jobseek, \n" +
            "least_prof6_name AS leastProf6Name, \n" +
            "least_prof6_num AS leastProf6Num, \n" +
            "least_prof6_need AS leastProf6Need, \n" +
            "least_prof6_jobseek AS leastProf6Jobseek, \n" +
            "least_prof7_name AS leastProf7Name, \n" +
            "least_prof7_num AS leastProf7Num, \n" +
            "least_prof7_need AS leastProf7Need, \n" +
            "least_prof7_jobseek AS leastProf7Jobseek, \n" +
            "least_prof8_name AS leastProf8Name, \n" +
            "least_prof8_num AS leastProf8Num, \n" +
            "least_prof8_need AS leastProf8Need, \n" +
            "least_prof8_jobseek AS leastProf8Jobseek, \n" +
            "least_prof9_name AS leastProf9Name, \n" +
            "least_prof9_num AS leastProf9Num, \n" +
            "least_prof9_need AS leastProf9Need, \n" +
            "least_prof9_jobseek AS leastProf9Jobseek, \n" +
            "least_prof10_name AS leastProf10Name, \n" +
            "least_prof10_num AS leastProf10Num, \n" +
            "least_prof10_need AS leastProf10Need, \n" +
            "least_prof10_jobseek AS leastProf10Jobseek \n" +
            "from t_least_needed where \n" +
            "(select delete_flag from t_upload_info where t_upload_info.table_id=t_least_needed.table_id limit 1)=0 \n" +
            "and table_id=#{param1} limit 1;")
    LeastNeeded selectLeastNeeded(Integer tableId);

    //查询人员类别求职人数表
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "unemp_youth AS unempYouth, \n" +
            "graduate AS graduate, \n" +
            "emp_to_unemp AS empToUnemp, \n" +
            "oth_unemp AS othUnemp, \n" +
            "emped AS emped, \n" +
            "laid_off AS laidOff, \n" +
            "retiree AS retiree, \n" +
            "student AS student, \n" +
            "city_rural AS cityRural, \n" +
            "fore AS fore \n" +
            "from t_job_seeker_num where \n" +
            "(select delete_flag from t_upload_info where t_upload_info.table_id=t_job_seeker_num.table_id limit 1)=0 \n" +
            "and table_id=#{param1} limit 1;")
    JobSeekerNum selectJobSeekerNum(Integer tableId);

    //查询性别供求人数表
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "male_need AS maleNeed, \n" +
            "male_jobseek AS maleJobseek, \n" +
            "female_need AS femaleNeed, \n" +
            "female_jobseek AS femaleJobseek, \n" +
            "no_requ_need AS noRequNeed \n" +
            "from t_sex_num where \n" +
            "(select delete_flag from t_upload_info where t_upload_info.table_id=t_sex_num.table_id limit 1)=0 \n" +
            "and table_id=#{param1} limit 1;")
    SexNum selectSexNum(Integer tableId);

    //查询文化程度供求人数表
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "below_juni_high_scho_need AS belowJuniHighSchoNeed, \n" +
            "below_juni_high_scho_jobseek AS belowJuniHighSchoJobseek, \n" +
            "high_scho_need AS highSchoNeed, \n" +
            "high_scho_jobseek AS highSchoJobseek,\n" +
            "other_high_scho_need AS otherHighSchoNeed, \n" +
            "other_high_scho_jobseek AS otherHighSchoJobseek, \n" +
            "juni_coll_need AS juniCollNeed, \n" +
            "juni_coll_jobseek AS juniCollJobseek, \n" +
            "univ_need AS univNeed, \n" +
            "univ_jobseek AS univJobseek, \n" +
            "no_requ_need AS noRequNeed \n" +
            "from t_degree_num where \n" +
            "(select delete_flag from t_upload_info where t_upload_info.table_id=t_degree_num.table_id limit 1)=0 \n" +
            "and table_id=#{param1} limit 1;")
    DegreeNum selectDegreeNum(Integer tableId);

    //查询年龄供求人数表
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "16_24_need AS sixteenTwentyfourNeed, \n" +
            "16_24_jobseek AS sixteenTwentyfourJobseek, \n" +
            "25_34_need AS twentyfiveThirtyfourNeed, \n" +
            "25_34_jobseek AS twentyfiveThirtyfourJobseek, \n" +
            "35_44_need AS thirtyfiveFortyfourNeed, \n" +
            "35_44_jobseek AS thirtyfiveFortyfourJobseek, \n" +
            "over_45_need AS overFortyfourNeed, \n" +
            "over_45_jobseek AS overFortyfourJobseek, \n" +
            "no_requ_need AS noRequNeed \n" +
            "from t_age_num where \n" +
            "(select delete_flag from t_upload_info where t_upload_info.table_id=t_age_num.table_id limit 1)=0 \n" +
            "and table_id=#{param1} limit 1;")
    AgeNum selectAgeNum(Integer tableId);

    //查询技术等级供求人数表
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "prof_level_5_need AS profLevel5Need, \n" +
            "prof_level_5_jobseek AS profLevel5Jobseek, \n" +
            "prof_level_4_need AS profLevel4Need, \n" +
            "prof_level_4_jobseek AS profLevel4Jobseek, \n" +
            "prof_level_3_need AS profLevel3Need, \n" +
            "prof_level_3_jobseek AS profLevel3Jobseek, \n" +
            "prof_level_2_need AS profLevel2Need, \n" +
            "prof_level_2_jobseek AS profLevel2Jobseek, \n" +
            "prof_level_1_need AS profLevel1Need,\n" +
            "prof_level_1_jobseek AS profLevel1Jobseek, \n" +
            "prim_prof_need AS primProfNeed, \n" +
            "prim_prof_jobseek AS primProfJobseek, \n" +
            "medi_prof_need AS mediProfNeed, \n" +
            "medi_prof_jobseek AS mediProfJobseek, \n" +
            "seni_prof_need AS seniProfNeed, \n" +
            "seni_prof_jobseek AS seniProfJobseek, \n" +
            "no_tech_jobseek AS noTechJobseek, \n" +
            "no_requ_need AS noRequNeed \n" +
            "from t_tech_grade_num where \n" +
            "(select delete_flag from t_upload_info where t_upload_info.table_id=t_tech_grade_num.table_id limit 1)=0 \n" +
            "and table_id=#{param1} limit 1;")
    TechGradeNum selectTechGradeNum(Integer tableId);

    //上传数据条件查询，查时间段和条件
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "upload_period_id AS uploadPeriodId, \n" +
            "state_flag AS stateFlag, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTime, \n" +
            "reviser AS reviser \n" +
            "from t_upload_info where \n" +
            "delete_flag=0 \n" +
            "and state_flag=3 \n" +
            "and (((select @tmp:=usertype from t_user where t_user.user_id=#{param1} limit 1)=1) \n" +
            "or (@tmp=2 and t_upload_info.creator in (select t_user.user_id from t_user where t_user.superior=#{param1})) \n" +
            "or (@tmp=3 and t_upload_info.creator=#{param1})) \n" +
            "and ((#{param2}<(select @tmp:=t_upload_period.start_date from t_upload_period where t_upload_period.upload_period_id=t_upload_info.upload_period_id limit 1) and @tmp<#{param3}) \n" +
            "or (@tmp<=#{param2} and #{param2}<(select t_upload_period.end_date from t_upload_period where t_upload_period.upload_period_id=t_upload_info.upload_period_id limit 1))) \n" +
            "and (((select @tmp:=fullname from t_user where t_user.user_id=t_upload_info.creator limit 1) like (case when #{param4} is not null then CONCAT('%',#{param4},'%') else @tmp end)) \n" +
            "or ((select @tmp:=region_emp_name from t_record_info where region_emp_id=t_upload_info.creator limit 1) like (case when #{param4} is not null then CONCAT('%',#{param4},'%') else @tmp end)) \n" +
            "or ((select @tmp:=region_name from t_record_info where region_emp_id=t_upload_info.creator limit 1) like (case when #{param4} is not null then CONCAT('%',#{param4},'%') else @tmp end)) \n" +
            "or ((select @tmp:=region_emp_contact from t_record_info where region_emp_id=t_upload_info.creator limit 1) like (case when #{param4} is not null then CONCAT('%',#{param4},'%') else @tmp end)));")
    List<UploadInfo> selectUploadInfoByCondition(Integer userId,java.sql.Date startDate,java.sql.Date endDate,String condition);

    //根据用户id查询上传数据信息
    @Select("SELECT \n" +
            "table_id AS tableId, \n" +
            "upload_period_id AS uploadPeriodId, \n" +
            "state_flag AS stateFlag, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTime, \n" +
            "reviser AS reviser \n" +
            "from t_upload_info where \n" +
            "delete_flag=0 \n" +
            "and state_flag=3 \n" +
            "and creator=#{param1};")
    List<UploadInfo> selectUploadInfoById(Integer userId);

    //根据id查询调查期
    @Select("SELECT \n" +
            "upload_period_id AS uploadPeriodId, \n" +
            "start_date AS startDate, \n" +
            "end_date AS endDate, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTime, \n" +
            "reviser AS reviser \n" +
            "from t_upload_period where \n" +
            "delete_flag=0 \n" +
            "and upload_period_id=#{param1} limit 1;")
    UploadPeriod selectUploadPeriod(Integer uploadPeriodId);

    //根据时间段查询调查期（有任意一天在这个时间段的所有调查期）
    @Select("SELECT \n" +
            "upload_period_id AS uploadPeriodId, \n" +
            "start_date AS startDate, \n" +
            "end_date AS endDate, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTime, \n" +
            "reviser AS reviser \n" +
            "from t_upload_period where \n" +
            "delete_flag=0 \n" +
            "and ((#{param1}<start_date and start_date<#{param2}) \n" +
            "or (start_date<=#{param1} and #{param1}<end_date));")
    List<UploadPeriod> selectUploadPeriodByTime(java.sql.Date startDate,java.sql.Date endDate);

    //取样分析，生成产业需求人数信息饼图数据，其中tableId中存的是所统计的个数
    @Select("SELECT \n" +
            "count(1) AS tableId, \n" +
            "sum(industry1_need) AS industry1Need, \n" +
            "sum(industry2_need) AS industry2Need, \n" +
            "sum(industry3_need) AS industry3Need, \n" +
            "sum(mine_need) AS mineNeed, \n" +
            "sum(manu_need) AS manuNeed, \n" +
            "sum(elec_gas_water_need) AS elecGasWaterNeed, \n" +
            "sum(arch_need) AS archNeed, \n" +
            "sum(tran_stor_post_need) AS tranStorPostNeed, \n" +
            "sum(info_comp_soft_need) AS infoCompSoftNeed, \n" +
            "sum(retail_need) AS retailNeed, \n" +
            "sum(acco_cater_need) AS accoCaterNeed, \n" +
            "sum(finance_need) AS financeNeed, \n" +
            "sum(estate_need) AS estateNeed, \n" +
            "sum(lease_busi_serv_need) AS leaseBusiservNeed, \n" +
            "sum(rese_tech_addr_need) AS reseTechAddrNeed, \n" +
            "sum(water_envi_faci_need) AS waterEnviFaciNeed, \n" +
            "sum(resi_serv_need) AS resiServNeed, \n" +
            "sum(edu_need) AS eduNeed,\n" +
            "sum(heal_secu_welf_need) AS healSecuWelfNeed, \n" +
            "sum(cult_sport_ente_need) AS cultSportEnteNeed, \n" +
            "sum(mana_orga_need) AS manaOrgaNeed, \n" +
            "sum(inte_orga_need) AS inteOrgaNeed \n" +
            "from t_industry_num where \n" +
            "((select delete_flag from t_upload_info where t_upload_info.table_id=t_industry_num.table_id limit 1)=0) \n" +
            "and ((select state_flag from t_upload_info where t_upload_info.table_id=t_industry_num.table_id limit 1)=3) \n" +
            "and (((select @type:=usertype from t_user where t_user.user_id=#{param1})=1) \n" +
            "or (@type=2 and (select @tmp:=t_upload_info.creator from t_upload_info where t_upload_info.table_id=t_industry_num.table_id limit 1) in (select user_id from t_user where superior=#{param1})) \n" +
            "or (@type=3 and @tmp=#{param1})) \n" +
            "and ((select upload_period_id from t_upload_info where t_upload_info.table_id=t_industry_num.table_id limit 1)=#{param2});")
    IndustryNum pieChartIndustryNum(Integer userId,Integer uploadPeriodId);

    //对比分析，生成供求总体人数折线图数据，其中tableId中存的是调查期的id
    @Select("SELECT \n" +
            "upload_period_id AS tableId, \n" +
            "sum(need_popu) AS needPopu, \n" +
            "sum(jobseek_popu) AS jobseekPopu \n" +
            "from t_total_num inner join t_upload_info on t_total_num.table_id=t_upload_info.table_id where \n" +
            "delete_flag=0 \n" +
            "and state_flag=3 \n" +
            "and (((select @tmp:=usertype from t_user where t_user.user_id=#{param1} limit 1)=1) \n" +
            "or (@tmp=2 and t_upload_info.creator in (select t_user.user_id from t_user where t_user.superior=#{param1})) \n" +
            "or (@tmp=3 and t_upload_info.creator=#{param1})) \n" +
            "and ((#{param2}<(select @tmp:=t_upload_period.start_date from t_upload_period where t_upload_period.upload_period_id=t_upload_info.upload_period_id limit 1) and @tmp<#{param3}) \n" +
            "or (@tmp<=#{param2} and #{param2}<(select t_upload_period.end_date from t_upload_period where t_upload_period.upload_period_id=t_upload_info.upload_period_id limit 1))) \n" +
            "group by upload_period_id;")
    List<TotalNum> lineChartTotalNum(Integer userId,java.sql.Date startDate,java.sql.Date endDate);

    //趋势分析，生成年龄供求人数折线图数据，其中tableId中存的是所统计的个数
    @Select("SELECT \n" +
            "count(1) AS tableId,\n" +
            "sum(16_24_need) AS sixteenTwentyfourNeed,\n" +
            "sum(16_24_jobseek) AS sixteenTwentyfourJobseek,\n" +
            "sum(25_34_need) AS twentyfiveThirtyfourNeed,\n" +
            "sum(25_34_jobseek) AS twentyfiveThirtyfourJobseek,\n" +
            "sum(35_44_need) AS thirtyfiveFortyfourNeed,\n" +
            "sum(35_44_jobseek) AS thirtyfiveFortyfourJobseek,\n" +
            "sum(over_45_need) AS overFortyfourNeed,\n" +
            "sum(over_45_jobseek) AS overFortyfourJobseek,\n" +
            "sum(no_requ_need) AS noRequNeed\n" +
            "from t_age_num inner join t_upload_info on t_age_num.table_id=t_upload_info.table_id where\n" +
            "delete_flag=0 \n" +
            "and state_flag=3 \n" +
            "and (((select @tmp:=usertype from t_user where t_user.user_id=#{param1} limit 1)=1) \n" +
            "or (@tmp=2 and t_upload_info.creator in (select t_user.user_id from t_user where t_user.superior=#{param1})) \n" +
            "or (@tmp=3 and t_upload_info.creator=#{param1})) \n" +
            "and ((#{param2}<(select @tmp:=t_upload_period.start_date from t_upload_period where t_upload_period.upload_period_id=t_upload_info.upload_period_id limit 1) and @tmp<#{param3}) \n" +
            "or (@tmp<=#{param2} and #{param2}<(select t_upload_period.end_date from t_upload_period where t_upload_period.upload_period_id=t_upload_info.upload_period_id limit 1)));")
    AgeNum lineChartAgeNum(Integer userId,java.sql.Date startDate,java.sql.Date endDate);
}
