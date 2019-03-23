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
            "revise_time=#{reviseTime}, \n" +
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
            "from t_record_info where region_emp_id=#{param1} and state_flag=2 limit 1;")
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
            "from t_record_info where region_emp_id=#{param1} and (state_flag=0 or state_flag=1) limit 1;")
    Record recordSelectUnfinished(Integer userId);

    //查询upload_info的下一个自增id
    @Select("SELECT AUTO_INCREMENT \n" +
            "FROM INFORMATION_SCHEMA.TABLES \n" +
            "WHERE TABLE_SCHEMA='human_resources_sys' AND TABLE_NAME = 't_upload_info' limit 1;")
    Integer uploadSelectNextTableId();

    //新建一条上传数据信息
    @Insert("INSERT INTO t_upload_info \n" +
            "(upload_period_id,state_flag,create_time,creator,revise_time,reviser,delete_flag) \n" +
            "VALUES(#{uploadPeriodId},#{stateFlag},#{createTime},#{creator},#{reviseTime},#{reviser},0);")
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
            "oper_jobseek,other_need,other_jobseek,no_requ_jobseek) \n" +
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
            "medi_prof_need,medi_prof_jobseek,seni_prof_need,seni_prof_jobseek,no_tech_jobseek,no_requ_need) \n" +
            "VALUES(#{tableId}, \n" +
            "#{profLevel5Need},#{profLevel5Jobseek},#{profLevel4Need},#{profLevel4Jobseek},#{profLevel3Need},#{profLevel3Jobseek}, \n" +
            "#{profLevel2Need},#{profLevel2Jobseek},#{profLevel1Need},#{profLevel1Jobseek},#{primProfNeed},#{primProfJobseek}, \n" +
            "#{mediProfNeed},#{mediProfJobseek},#{seniProfNeed},#{seniProfJobseek},#{noTechJobseek},#{noRequNeed});")
    Integer uploadInsertTechGradeNum(TechGradeNum techGradeNum);

    //更新一条上传数据信息
    @Update("UPDATE t_upload_info SET \n" +
            "upload_period_id=#{uploadPeriodId},state_flag=#{stateFlag},create_time=#{createTime}, \n" +
            "creator=#{creator},revise_time=#{reviseTime},reviser=#{reviser} \n" +
            "where table_id=#{tableId};")
    Integer uploadUpdateUploadInfo(UploadInfo uploadInfo);

    //更新一条供求总体人数信息
    @Update("UPDATE t_total_num SET \n" +
            "need_popu=#{needPopu},jobseek_popu#{jobseekPopu} \n" +
            "where table_id=#{tableId};")
    Integer uploadUpdateTotalNum(TotalNum totalNum);

    //更新一条产业需求人数信息
    @Update("UPDATE t_industry_num SET \n" +
            "industry1_need=#{industry1Need},industry2_need=#{industry2Need},industry3_need=#{industry3Need}, \n" +
            "mine_need=#{mineNeed},manu_need=#{manuNeed},elec_gas_water_need=#{elecGasWaterNeed}, \n" +
            "arch_need=#{archNeed},tran_stor_post_need=#{tranStorPostNeed},info_comp_soft_need=#{infoCompSoftNeed}, \n" +
            "retail_need=#{retailNeed},acco_cater_need=#{accoCaterNeed},finance_need=#{financeNeed}, \n" +
            "estate_need=#{estateNeed},lease_busi_serv_need=#{leaseBusiServNeed},rese_tech_addr_need=#{reseTechAddrNeed}, \n" +
            "water_envi_faci_need=#{waterEnviFaciNeed},resi_serv_need=#{resiServNeed},edu_need=#{eduNeed}, \n" +
            "heal_secu_welf_need=#{healSecuWelfNeed},cult_sport_ente_need=#{cultSportEnteNeed},mana_orga_need=#{manaOrgaNeed}, \n" +
            "inte_orga_need=#{inteOrgaNeed} \n" +
            "where table_id=#{tableId};")
    Integer uploadUpdateIndustryNum(IndustryNum industryNum);

    //更新一条用人单位性质需求人数信息
    @Update("UPDATE t_employer_num SET \n" +
            "sta_own_need=#{staOwnNeed},coll_need=#{collNeed},coop_stock_need=#{coopStockNeed}, \n" +
            "joint_need=#{jointNeed},limi_liab_need=#{limiLiabNeed},limited_need=#{limitedNeed}, \n" +
            "priv_need=#{privNeed},other_ente_need=#{otherEnteNeed},hmt_inve_need=#{hmtInveNeed}, \n" +
            "fore_inve_ente=#{foreInveEnte},indi_need=#{indiNeed},inst_need=#{instNeed}, \n" +
            "orga_need=#{orgaNeed},other_need=#{otherNeed} \n" +
            "where table_id=#{tableId};")
    Integer uploadUpdateEmployerNum(EmployerNum employerNum);

    //更新一条职业供求人数信息
    @Update("UPDATE t_prof_num SET \n" +
            "leader_need=#{leaderNeed},leader_jobseek=#{leaderJobseek},prof_tech_need=#{profTechNeed}, \n" +
            "prof_tech_jobseek=#{profTechJobseek},staff_rela_need=#{staffRelaNeed},staff_rela_jobseek=#{staffRelaJobseek}, \n" +
            "busi_serv_need=#{busiServNeed},busi_serv_jobseek=#{busiServJobseek},prod_need=#{prodNeed}, \n" +
            "prod_jobseek=#{prodJobseek},oper_need=#{operNeed},oper_jobseek=#{operJobseek}, \n" +
            "other_need=#{otherNeed},other_jobseek=#{otherJobseek},no_requ_jobseek=#{noRequJobseek} \n" +
            "where table_id=#{tableId};")
    Integer uploadUpdateProfNum(ProfNum profNum);

    //更新一条需求前十职业信息
    @Update("UPDATE t_most_needed SET \n" +
            "most_prof1_name=#{mostProf1Name},most_prof1_num=#{mostProf1Num},most_prof1_need=#{mostProf1Need},most_prof1_jobseek=#{mostProf1Jobseek}, \n" +
            "most_prof2_name=#{mostProf2Name},most_prof2_num=#{mostProf2Num},most_prof2_need=#{mostProf2Need},most_prof2_jobseek=#{mostProf2Jobseek}, \n" +
            "most_prof3_name=#{mostProf3Name},most_prof3_num=#{mostProf3Num},most_prof3_need=#{mostProf3Need},most_prof3_jobseek=#{mostProf3Jobseek}, \n" +
            "most_prof4_name=#{mostProf4Name},most_prof4_num=#{mostProf4Num},most_prof4_need=#{mostProf4Need},most_prof4_jobseek=#{mostProf4Jobseek}, \n" +
            "most_prof5_name=#{mostProf5Name},most_prof5_num=#{mostProf5Num},most_prof5_need=#{mostProf5Need},most_prof5_jobseek=#{mostProf5Jobseek}, \n" +
            "most_prof6_name=#{mostProf6Name},most_prof6_num=#{mostProf6Num},most_prof6_need=#{mostProf6Need},most_prof6_jobseek=#{mostProf6Jobseek}, \n" +
            "most_prof7_name=#{mostProf7Name},most_prof7_num=#{mostProf7Num},most_prof7_need=#{mostProf7Need},most_prof7_jobseek=#{mostProf7Jobseek}, \n" +
            "most_prof8_name=#{mostProf8Name},most_prof8_num=#{mostProf8Num},most_prof8_need=#{mostProf8Need},most_prof8_jobseek=#{mostProf8Jobseek}, \n" +
            "most_prof9_name=#{mostProf9Name},most_prof9_num=#{mostProf9Num},most_prof9_need=#{mostProf9Need},most_prof9_jobseek=#{mostProf9Jobseek}, \n" +
            "most_prof10_name=#{mostProf10Name},most_prof10_num=#{mostProf10Num},most_prof10_need=#{mostProf10Need},most_prof10_jobseek=#{mostProf10Jobseek} \n" +
            "where table_id=#{tableId};")
    Integer uploadUpdateMostNeeded(MostNeeded mostNeeded);

    //更新一条饱和前十职业信息
    @Update("UPDATE t_least_needed SET \n" +
            "least_prof1_name=#{leastProf1Name},least_prof1_num=#{leastProf1Num},least_prof1_need=#{leastProf1Need},least_prof1_jobseek=#{leastProf1Jobseek}, \n" +
            "least_prof2_name=#{leastProf2Name},least_prof2_num=#{leastProf2Num},least_prof2_need=#{leastProf2Need},least_prof2_jobseek=#{leastProf2Jobseek}, \n" +
            "least_prof3_name=#{leastProf3Name},least_prof3_num=#{leastProf3Num},least_prof3_need=#{leastProf3Need},least_prof3_jobseek=#{leastProf3Jobseek}, \n" +
            "least_prof4_name=#{leastProf4Name},least_prof4_num=#{leastProf4Num},least_prof4_need=#{leastProf4Need},least_prof4_jobseek=#{leastProf4Jobseek}, \n" +
            "least_prof5_name=#{leastProf5Name},least_prof5_num=#{leastProf5Num},least_prof5_need=#{leastProf5Need},least_prof5_jobseek=#{leastProf5Jobseek}, \n" +
            "least_prof6_name=#{leastProf6Name},least_prof6_num=#{leastProf6Num},least_prof6_need=#{leastProf6Need},least_prof6_jobseek=#{leastProf6Jobseek}, \n" +
            "least_prof7_name=#{leastProf7Name},least_prof7_num=#{leastProf7Num},least_prof7_need=#{leastProf7Need},least_prof7_jobseek=#{leastProf7Jobseek}, \n" +
            "least_prof8_name=#{leastProf8Name},least_prof8_num=#{leastProf8Num},least_prof8_need=#{leastProf8Need},least_prof8_jobseek=#{leastProf8Jobseek}, \n" +
            "least_prof9_name=#{leastProf9Name},least_prof9_num=#{leastProf9Num},least_prof9_need=#{leastProf9Need},least_prof9_jobseek=#{leastProf9Jobseek}, \n" +
            "least_prof10_name=#{leastProf10Name},least_prof10_num=#{leastProf10Num},least_prof10_need=#{leastProf10Need},least_prof10_jobseek=#{leastProf10Jobseek} \n" +
            "where table_id=#{tableId};")
    Integer uploadUpdateLeastNeeded(LeastNeeded leastNeeded);

    //更新一条人员类别求职人数信息
    @Update("UPDATE t_job_seeker_num SET \n" +
            "unemp_youth=#{unempYouth},graduate=#{graduate},emp_to_unemp=#{empToUnemp}, \n" +
            "oth_unemp=#{othUnemp},emped=#{emped},laid_off=#{laidOff}, \n" +
            "retiree=#{retiree},student=#{student},city_rural=#{cityRural}, \n" +
            "fore=#{fore} \n" +
            "where table_id=#{tableId};")
    Integer uploadUpdateJobSeekerNum(JobSeekerNum jobSeekerNum);

    //更新一条性别供求人数表信息
    @Update("UPDATE t_sex_num SET \n" +
            "male_need=#{maleNeed},male_jobseek=#{maleJobseek},female_need=#{femaleNeed}, \n" +
            "female_jobseek=#{femaleJobseek},no_requ_need=#{noRequNeed} \n" +
            "where table_id=#{tableId};")
    Integer uploadUpdateSexNum(SexNum sexNum);

    //更新一条年龄供求人数信息
    @Update("UPDATE t_age_num SET \n" +
            "16_24_need=#{sixteenTwentyfourNeed},16_24_jobseek=#{sixteenTwentyfourJobseek},25_34_need=#{twentyfiveThirtyfourNeed}, \n" +
            "25_34_jobseek=#{twentyfiveThirtyfourJobseek},35_44_need=#{thirtyfiveFortyfourNeed},35_44_jobseek=#{thirtyfiveFortyfourJobseek}, \n" +
            "over_45_need=#{overFortyfourNeed},over_45_jobseek=#{overFortyfourJobseek},no_requ_need=#{noRequNeed} \n" +
            "where table_id=#{tableId};")
    Integer uploadUpdateAgeNum(AgeNum ageNum);

    //更新一条文化程度供求人数信息
    @Update("UPDATE t_degree_num SET \n" +
            "below_juni_high_scho_need=#{belowJuniHighSchoNeed},below_juni_high_scho_jobseek=#{belowJuniHighSchoJobseek},high_scho_need=#{highSchoNeed}, \n" +
            "high_scho_jobseek=#{highSchoJobseek},other_high_scho_need=#{otherHighSchoNeed},other_high_scho_jobseek=#{otherHighSchoJobseek}, \n" +
            "juni_coll_need=#{juniCollNeed},juni_coll_jobseek=#{juniCollJobseek},univ_need=#{univNeed}, \n" +
            "univ_jobseek=#{univJobseek},no_requ_need=#{noRequNeed} \n" +
            "where table_id=#{tableId};")
    Integer uploadUpdateDegreeNum(DegreeNum degreeNum);

    //更新一条技术等级供求人数信息
    @Update("UPDATE t_tech_grade_num SET \n" +
            "prof_level_5_need=#{profLevel5Need},prof_level_5_jobseek=#{profLevel5Jobseek},prof_level_4_need=#{profLevel4Need}, \n" +
            "prof_level_4_jobseek=#{profLevel4Jobseek},prof_level_3_need=#{profLevel3Need},prof_level_3_jobseek=#{profLevel3Jobseek}, \n" +
            "prof_level_2_need=#{profLevel2Need},prof_level_2_jobseek=#{profLevel2Jobseek},prof_level_1_need=#{profLevel1Need}, \n" +
            "prof_level_1_jobseek=#{profLevel1Jobseek},prim_prof_need=#{primProfNeed},prim_prof_jobseek=#{primProfJobseek}, \n" +
            "medi_prof_need=#{mediProfNeed},medi_prof_jobseek=#{mediProfJobseek},seni_prof_need=#{seniProfNeed}, \n" +
            "seni_prof_jobseek=#{seniProfJobseek},no_tech_jobseek=#{noTechJobseek},no_requ_need=#{noRequNeed} \n" +
            "where table_id=#{tableId};")
    Integer uploadUpdateTechGradeNum(TechGradeNum techGradeNum);
}
