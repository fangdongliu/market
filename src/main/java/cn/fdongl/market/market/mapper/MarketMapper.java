package cn.fdongl.market.market.mapper;


import cn.fdongl.market.market.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.core.annotation.Order;

@Mapper
@Order(1)
public interface MarketMapper {

    //新建一条备案信息
    @Insert("INSERT INTO t_record_info \n" +
            "(region_emp_id,region_emp_name,region_name,region_emp_contact,region_emp_contact_mobi,region_emp_contact_num, \n" +
            "region_emp_fax,state_flag,create_time,creator,revise_time,reviser,delete_flag) \n" +
            "VALUES(#{regionEmpId},#{regionEmpName},#{regionName},#{regionEmpContact},#{regionEmpContactMobi},#{regionEmpContactNum}, \n" +
            "#{regionEmpFax},#{stateFlag},#{createTime},#{creator},#{reviseTime},#{reviser},0);")
    Integer recordInsert(Record record);

    //更新一条备案信息
    @Update("UPDATE t_record_info SET \n" +
            "region_emp_id=#{regionEmpId}, \n" +
            "region_emp_name=#{regionEmpName}, \n" +
            "region_name=#{regionName}, \n" +
            "region_emp_contact=#{regionEmpContact}, \n" +
            "region_emp_contact_mobi=#{regionEmpContactMobi}, \n" +
            "region_emp_contact_num=#{regionEmpContactNum}, \n" +
            "region_emp_fax=#{regionEmpFax}, \n" +
            "state_flag=#{stateFlag}, \n" +
            "create_time=#{createTime}, \n" +
            "creator=#{creator}, \n" +
            "revise_time=#{reviseTIme}, \n" +
            "reviser=#{reviser} \n" +
            "where region_emp_id=#{regionEmpId} and state_flag=0;")
    Integer recordUpdate(Record record);

    //根据用户id查已完成的备案信息
    @Select("SELECT \n" +
            "region_emp_id AS regionEmpId, \n" +
            "region_emp_name AS regionEmpName, \n" +
            "region_name AS regionName, \n" +
            "region_emp_contact AS regionEmpContact, \n" +
            "region_emp_contact_mobi AS regionEmpContactMobi, \n" +
            "region_emp_contact_num AS regionEmpContactNum, \n" +
            "region_emp_fax AS regionEmpFax, \n" +
            "state_flag AS stateFlag, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTIme, \n" +
            "reviser AS reviser \n" +
            "from t_record_info where state_flag=2 limit 1;")
    Record recordSelectFinished(Integer userId);

    //根据用户id查询保存或上传的备案信息
    @Select("SELECT \n" +
            "region_emp_id AS regionEmpId, \n" +
            "region_emp_name AS regionEmpName, \n" +
            "region_name AS regionName, \n" +
            "region_emp_contact AS regionEmpContact, \n" +
            "region_emp_contact_mobi AS regionEmpContactMobi, \n" +
            "region_emp_contact_num AS regionEmpContactNum, \n" +
            "region_emp_fax AS regionEmpFax, \n" +
            "state_flag AS stateFlag, \n" +
            "create_time AS createTime, \n" +
            "creator AS creator, \n" +
            "revise_time AS reviseTIme, \n" +
            "reviser AS reviser \n" +
            "from t_record_info where (state_flag=0 or state_flag=1) limit 1;")
    Record recordSelectUnfinished(Integer userId);

    //查询upload_info的下一个自增id
    @Select("SELECT AUTO_INCREMENT \n" +
            "FROM INFORMATION_SCHEMA.TABLES \n" +
            "WHERE TABLE_NAME = '{t_upload_info}' limit 1;")
    Integer uploadSelectNextTableId();

    //新建一条上传数据信息
    @Insert("INSERT INTO t_upload_info \n" +
            "(state_flag,create_time,creator,revise_time,reviser,delete_flag) \n" +
            "VALUES(#{stateFlag},#{createTime},#{creator},#{reviseTime},#{reviser},0);")
    Integer uploadInsertUploadInfo(UploadInfo uploadInfo);

    //新建一条供求总体人数信息
    @Insert("INSERT INTO t_total_num \n" +
            "(table_id,need_popu,jobseek_popu) \n" +
            "VALUES(#{tableId},#{needPopu},#{jobseekPopu});")
    Integer uploadInsertTotalNum(TotalNum totalNum);

    //新建一条产业需求人数信息
    @Insert("INSERT INTO t_industry_num \n" +
            "(table_id,industry1_need,industry2_need,industry3_need,mine_need,manu_need, \n" +
            "elec_gas_water_need,arch_need,tran_stor_post_need,info_comp_soft_need,retail_need,acco_cater_need, \n" +
            "finance_need,estate_need,lease_busi_serv_need,rese_tech_addr_need,water_envi_faci_need,resi_serv_need, \n" +
            "edu_need,heal_secu_welf_need,cult_sport_ente_need,mana_orga_need,inte_orga_need) \n" +
            "VALUES(#{tableId},#{industry1Need},#{industry2Need},#{industry3Need},#{mineNeed},#{manuNeed}, \n" +
            "#{elecGasWaterNeed},#{archNeed},#{tranStorPostNeed},#{infoCompSoftNeed},#{retailNeed},#{accoCaterNeed}, \n" +
            "#{financeNeed},#{estateNeed},#{leaseBusiServNeed},#{reseTechAddrNeed},#{waterEnviFaciNeed},#{resiServNeed}, \n" +
            "#{eduNeed},#{healSecuWelfNeed},#{cultSportEnteNeed},#{manaOrgaNeed},#{inteOrgaNeed});")
    Integer uploadInsertIndustryNum(IndustryNum industryNum);

    //新建一条用人单位性质需求人数信息
    @Insert("INSERT INTO t_employer_num \n" +
            "(table_id,sta_own_need,coll_need,coop_stock_need,joint_need,limi_liab_need, \n" +
            "limited_need,priv_need,other_ente_need,hmt_inve_need,fore_inve_ente,indi_need, \n" +
            "inst_need,orga_need,other_need) \n" +
            "VALUES(#{tableId},#{staOwnNeed},#{collNeed},#{coopStockNeed},#{jointNeed},#{limiLiabNeed}, \n" +
            "#{limitedNeed},#{privNeed},#{otherEnteNeed},#{hmtInveNeed},#{foreInveEnte},#{indiNeed}, \n" +
            "#{instNeed},#{orgaNeed},#{otherNeed});")
    Integer uploadInsertEmployerNum(EmployerNum employerNum);

    //新建一条职业供求人数信息
    @Insert("INSERT INTO t_prof_num \n" +
            "(table_id,leader_need,leader_jobseek,prof_tech_need,prof_tech_jobseek,staff_rela_need, \n" +
            "staff_rela_jobseek,busi_serv_need,busi_serv_jobseek,prod_need,prod_jobseek,oper_need, \n" +
            "oper_jobseek,other_jobseek,no_requ_jobseek) \n" +
            "VALUES(#{tableId},#{leaderNeed},#{leaderJobseek},#{profTechNeed},#{profTechJobseek},#{staffRelaNeed}, \n" +
            "#{staffRelaJobseek},#{busiServNeed},#{busiServJobseek},#{prodNeed},#{prodJobseek},#{operNeed}, \n" +
            "#{operJobseek},#{otherNeed},#{otherJobseek},#{noRequJobseek});")
    Integer uploadInsertProfNum(ProfNum profNum);

    //新建一条需求前十职业信息
    @Insert("INSERT INTO t_most_needed \n" +
            "(table_id, \n" +
            "most_prof1_name,most_prof1_num,most_prof1_need,most_prof1_jobseek, \n" +
            "most_prof2_name,most_prof2_num,most_prof2_need,most_prof2_jobseek, \n" +
            "most_prof3_name,most_prof3_num,most_prof3_need,most_prof3_jobseek, \n" +
            "most_prof4_name,most_prof4_num,most_prof4_need,most_prof4_jobseek, \n" +
            "most_prof5_name,most_prof5_num,most_prof5_need,most_prof5_jobseek, \n" +
            "most_prof6_name,most_prof6_num,most_prof6_need,most_prof6_jobseek, \n" +
            "most_prof7_name,most_prof7_num,most_prof7_need,most_prof7_jobseek, \n" +
            "most_prof8_name,most_prof8_num,most_prof8_need,most_prof8_jobseek, \n" +
            "most_prof9_name,most_prof9_num,most_prof9_need,most_prof9_jobseek, \n" +
            "most_prof10_name,most_prof10_num,most_prof10_need,most_prof10_jobseek) \n" +
            "VALUES(#{tableId}, \n" +
            "#{mostProf1Name},#{mostProf1Num},#{mostProf1Need},#{mostProf1Jobseek}, \n" +
            "#{mostProf2Name},#{mostProf2Num},#{mostProf2Need},#{mostProf2Jobseek}, \n" +
            "#{mostProf3Name},#{mostProf3Num},#{mostProf3Need},#{mostProf3Jobseek}, \n" +
            "#{mostProf4Name},#{mostProf4Num},#{mostProf4Need},#{mostProf4Jobseek}, \n" +
            "#{mostProf5Name},#{mostProf5Num},#{mostProf5Need},#{mostProf5Jobseek}, \n" +
            "#{mostProf6Name},#{mostProf6Num},#{mostProf6Need},#{mostProf6Jobseek}, \n" +
            "#{mostProf7Name},#{mostProf7Num},#{mostProf7Need},#{mostProf7Jobseek}, \n" +
            "#{mostProf8Name},#{mostProf8Num},#{mostProf8Need},#{mostProf8Jobseek}, \n" +
            "#{mostProf9Name},#{mostProf9Num},#{mostProf9Need},#{mostProf9Jobseek}, \n" +
            "#{mostProf10Name},#{mostProf10Num},#{mostProf10Need},#{mostProf10Jobseek});")
    Integer uploadInsertMostNeeded(MostNeeded mostNeeded);

    //新建一条饱和前十职业信息
    @Insert("INSERT INTO t_least_needed \n" +
            "(table_id, \n" +
            "least_prof1_name,least_prof1_num,least_prof1_need,least_prof1_jobseek, \n" +
            "least_prof2_name,least_prof2_num,least_prof2_need,least_prof2_jobseek, \n" +
            "least_prof3_name,least_prof3_num,least_prof3_need,least_prof3_jobseek, \n" +
            "least_prof4_name,least_prof4_num,least_prof4_need,least_prof4_jobseek, \n" +
            "least_prof5_name,least_prof5_num,least_prof5_need,least_prof5_jobseek, \n" +
            "least_prof6_name,least_prof6_num,least_prof6_need,least_prof6_jobseek, \n" +
            "least_prof7_name,least_prof7_num,least_prof7_need,least_prof7_jobseek, \n" +
            "least_prof8_name,least_prof8_num,least_prof8_need,least_prof8_jobseek, \n" +
            "least_prof9_name,least_prof9_num,least_prof9_need,least_prof9_jobseek, \n" +
            "least_prof10_name,least_prof10_num,least_prof10_need,least_prof10_jobseek) \n" +
            "VALUES(#{tableId}, \n" +
            "#{leastProf1Name},#{leastProf1Num},#{leastProf1Need},#{leastProf1Jobseek}, \n" +
            "#{leastProf2Name},#{leastProf2Num},#{leastProf2Need},#{leastProf2Jobseek}, \n" +
            "#{leastProf3Name},#{leastProf3Num},#{leastProf3Need},#{leastProf3Jobseek}, \n" +
            "#{leastProf4Name},#{leastProf4Num},#{leastProf4Need},#{leastProf4Jobseek}, \n" +
            "#{leastProf5Name},#{leastProf5Num},#{leastProf5Need},#{leastProf5Jobseek}, \n" +
            "#{leastProf6Name},#{leastProf6Num},#{leastProf6Need},#{leastProf6Jobseek}, \n" +
            "#{leastProf7Name},#{leastProf7Num},#{leastProf7Need},#{leastProf7Jobseek}, \n" +
            "#{leastProf8Name},#{leastProf8Num},#{leastProf8Need},#{leastProf8Jobseek}, \n" +
            "#{leastProf9Name},#{leastProf9Num},#{leastProf9Need},#{leastProf9Jobseek}, \n" +
            "#{leastProf10Name},#{leastProf10Num},#{leastProf10Need},#{leastProf10Jobseek});")
    Integer uploadInsertLeastNeeded(LeastNeeded leastNeeded);

    //新建一条人员类别求职人数信息
    @Insert("INSERT INTO t_job_seeker_num \n" +
            "(table_id,unemp_youth,graduate,emp_to_unemp,oth_unemp,emped, \n" +
            "laid_off,retiree,student,\tcity_rural,fore) \n" +
            "VALUES(#{tableId},#{unempYouth},#{graduate},#{empToUnemp},#{othUnemp},#{emped}, \n" +
            "#{laidOff},#{retiree},#{student},#{cityRural},#{fore});")
    Integer uploadInsertJobSeekerNum(JobSeekerNum jobSeekerNum);

    //新建一条性别供求人数信息
    @Insert("INSERT INTO t_sex_num \n" +
            "(table_id,male_need,male_jobseek,female_need,female_jobseek,no_requ_need) \n" +
            "VALUES(#{tableId},#{maleNeed},#{maleJobseek},#{femaleNeed},#{femaleJobseek},#{noRequNeed});")
    Integer uploadInsertSexNum(SexNum sexNum);

    //新建一条年龄供求人数信息
    @Insert("INSERT INTO t_age_num \n" +
            "(table_id,16_24_need,16_24_jobseek,25_34_need,25_34_jobseek,35_44_need, \n" +
            "35_44_jobseek,over_45_need,over_45_jobseek,no_requ_need) \n" +
            "VALUES(#{tableId},#{sixteenTwentyfourNeed},#{sixteenTwentyfourJobseek},#{twentyfiveThirtyfourNeed},#{twentyfiveThirtyfourJobseek},#{thirtyfiveFortyfourNeed}, \n" +
            "#{thirtyfiveFortyfourJobseek},#{overFortyfourNeed},#{overFortyfourJobseek},#{noRequNeed});")
    Integer uploadInsertAgeNum(AgeNum ageNum);

    //新建一条文化程度供求人数信息
    @Insert("INSERT INTO t_degree_num \n" +
            "(table_id,below_juni_high_scho_need,below_juni_high_scho_jobseek,high_scho_need,high_scho_jobseek,other_high_scho_need, \n" +
            "other_high_scho_jobseek,juni_coll_need,juni_coll_jobseek,univ_need,univ_jobseek,no_requ_need) \n" +
            "VALUES(#{tableId},#{belowJuniHighSchoNeed},#{belowJuniHighSchoJobseek},#{highSchoNeed},#{highSchoJobseek},#{otherHighSchoNeed}, \n" +
            "#{otherHighSchoJobseek},#{juniCollNeed},#{juniCollJobseek},#{univNeed},#{univJobseek},#{noRequNeed});")
    Integer uploadInsertDegreeNum(DegreeNum degreeNum);

    //新建一条技术等级供求人数信息
    @Insert("INSERT INTO t_tech_grade_num \n" +
            "(table_id, \n" +
            "prof_level_5_need,prof_level_5_jobseek,prof_level_4_need,prof_level_4_jobseek,prof_level_3_need,prof_level_3_jobseek, \n" +
            "prof_level_2_need,prof_level_2_jobseek,prof_level_1_need,prof_level_1_jobseek,prim_prof_need,prim_prof_jobseek, \n" +
            "medi_prof_need,medi_prof_jobseek,seni_prof_need,seni_prof_jobseek,no_tech_jobseek,no_tech_jobseek) \n" +
            "VALUES(#{tableId}, \n" +
            "#{profLevel5Need},#{profLevel5Jobseek},#{profLevel4Need},#{profLevel4Jobseek},#{profLevel3Need},#{profLevel3Jobseek}, \n" +
            "#{profLevel2Need},#{profLevel2Jobseek},#{profLevel1Need},#{profLevel1Jobseek},#{primProfNeed},#{primProfJobseek}, \n" +
            "#{mediProfNeed},#{mediProfJobseek},#{seniProfNeed},#{seniProfJobseek},#{noTechJobseek},#{noRequNeed});")
    Integer uploadInsertTechGrageNum(TechGrageNum techGrageNum);

    //TODO：12张表的改查sql，总计24个
}
