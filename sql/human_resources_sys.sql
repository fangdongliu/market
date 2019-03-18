/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/3/13 18:36:45                           */
/*==============================================================*/


drop table if exists t_age_num;

drop table if exists t_career_dic;

drop table if exists t_upload_info;

drop table if exists t_degree_num;

drop table if exists t_employer_num;

drop table if exists t_industry_num;

drop table if exists t_job_seeker_num;

drop table if exists t_least_needed;

drop table if exists t_most_needed;

drop table if exists t_notice;

drop table if exists t_prof_num;

drop table if exists t_record_info;

drop table if exists t_right;

drop table if exists t_role;

drop table if exists t_role_right;

drop table if exists t_sex_num;

drop table if exists t_tech_grade_num;

drop table if exists t_total_num;

drop table if exists t_upload_period;

drop table if exists t_user;

drop table if exists t_user_right;

drop table if exists t_user_role;

/*==============================================================*/
/* Table: t_age_num                                             */
/*==============================================================*/
create table t_age_num
(
   table_id             int,
   16_24_need           int,
   16_24_jobseek        int,
   25_34_need           int,
   25_34_jobseek        int,
   35_44_need           int,
   35_44_jobseek        int,
   over_45_need         int,
   over_45_jobseek      int,
   no_requ_need         int
);

alter table t_age_num comment '年龄供求人数表';

/*==============================================================*/
/* Table: t_career_dic                                          */
/*==============================================================*/
create table t_career_dic
(
   career_id            int not null auto_increment,
   career_name          nvarchar(100) not null,
   create_time          datetime not null,
   creator              char(10) not null,
   revise_time          datetime,
   reviser              int,
   delete_flag          int not null,
   primary key (career_id)
);

alter table t_career_dic comment '职业字典表';

/*==============================================================*/
/* Table: t_upload_info                                               */
/*==============================================================*/
create table t_upload_info
(
   table_id             int not null auto_increment,
   upload_period_id     int not null,
   state_flag           int not null,
   create_time          datetime not null,
   creator              int not null,
   revise_time          datetime,
   reviser              int,
   delete_flag          int not null,
   primary key (table_id)
);

alter table t_upload_info comment '上传数据表格';

/*==============================================================*/
/* Table: t_degree_num                                          */
/*==============================================================*/
create table t_degree_num
(
   table_id             int,
   below_juni_high_scho_need int,
   below_juni_high_scho_jobseek int,
   high_scho_need       int,
   high_scho_jobseek    int,
   other_high_scho_need int,
   other_high_scho_jobseek int,
   juni_coll_need       int,
   juni_coll_jobseek    int,
   univ_need            int,
   univ_jobseek         int,
   no_requ_need         int
);

alter table t_degree_num comment '文化程度供求人数表';

/*==============================================================*/
/* Table: t_employer_num                                        */
/*==============================================================*/
create table t_employer_num
(
   table_id             int,
   sta_own_need         int,
   coll_need            int,
   coop_stock_need      int,
   joint_need           int,
   limi_liab_need       int,
   limited_need         int,
   priv_need            int,
   other_ente_need      int,
   hmt_inve_need        int,
   fore_inve_ente       int,
   indi_need            int,
   inst_need            int,
   orga_need            int,
   other_need           int
);

alter table t_employer_num comment '用人单位性质需求人数表';

/*==============================================================*/
/* Table: t_industry_num                                        */
/*==============================================================*/
create table t_industry_num
(
   table_id             int,
   industry1_need       int,
   industry2_need       int,
   industry3_need       int,
   mine_need            int,
   manu_need            int,
   elec_gas_water_need  int,
   arch_need            int,
   tran_stor_post_need  int,
   info_comp_soft_need  int,
   retail_need          int,
   acco_cater_need      int,
   finance_need         int,
   estate_need          int,
   lease_busi_serv_need int,
   rese_tech_addr_need  int,
   water_envi_faci_need int,
   resi_serv_need       int,
   edu_need             int,
   heal_secu_welf_need  int,
   cult_sport_ente_need int,
   mana_orga_need       int,
   inte_orga_need       int
);

alter table t_industry_num comment '产业需求人数表';

/*==============================================================*/
/* Table: t_job_seeker_num                                      */
/*==============================================================*/
create table t_job_seeker_num
(
   table_id             int,
   unemp_youth          int,
   graduate             int,
   emp_to_unemp         int,
   oth_unemp            int,
   emped                int,
   laid_off             int,
   retiree              int,
   student              int,
   city_rural           int,
   fore                 int
);

alter table t_job_seeker_num comment '人员类别求职人数表';

/*==============================================================*/
/* Table: t_least_needed                                        */
/*==============================================================*/
create table t_least_needed
(
   table_id             int,
   least_prof1_id       int,
   least_prof2_id       int,
   least_prof3_id       int,
   least_prof4_id       int,
   least_prof5_id       int,
   least_prof6_id       int,
   least_prof7_id       int,
   least_prof8_id       int,
   least_prof9_id       int,
   least_prof10_id      int,
   least_prof1_num      int,
   least_prof2_num      int,
   least_prof3_num      int,
   least_prof4_num      int,
   least_prof5_num      int,
   least_prof6_num      int,
   least_prof7_num      int,
   least_prof8_num      int,
   least_prof9_num      int,
   least_prof10_num     int
);

alter table t_least_needed comment '饱和前十职业表';

/*==============================================================*/
/* Table: t_most_needed                                         */
/*==============================================================*/
create table t_most_needed
(
   table_id             int,
   most_prof1_id        int,
   most_prof2_id        int,
   most_prof3_id        int,
   most_prof4_id        int,
   most_prof5_id        int,
   most_prof6_id        int,
   most_prof7_id        int,
   most_prof8_id        int,
   most_prof9_id        int,
   most_prof10_id       int,
   most_prof1_num       int,
   most_prof2_num       int,
   most_prof3_num       int,
   most_prof4_num       int,
   most_prof5_num       int,
   most_prof6_num       int,
   most_prof7_num       int,
   most_prof8_num       int,
   most_prof9_num       int,
   most_prof10_num      int
);

alter table t_most_needed comment '需求前十职业表';

/*==============================================================*/
/* Table: t_notice                                              */
/*==============================================================*/
create table t_notice
(
   notice_id            int not null auto_increment,
   notice_title         nvarchar(100) not null,
   notice_content       nvarchar(4000) not null,
   create_time          datetime not null,
   creator              int not null,
   revise_time          datetime,
   reviser              int,
   delete_flag          int not null,
   reciver              int,
   primary key (notice_id)
);

alter table t_notice comment '通知表';

/*==============================================================*/
/* Table: t_prof_num                                            */
/*==============================================================*/
create table t_prof_num
(
   table_id             int,
   leader_need          int,
   leader_jobseek       int,
   prof_tech_need       int,
   prof_tech_jobseek    int,
   staff_rela_need      int,
   staff_rela_jobseek   int,
   busi_serv_need       int,
   busi_serv_jobseek    int,
   prod_need            int,
   prod_jobseek         int,
   oper_need            int,
   oper_jobseek         int,
   other_need           int,
   other_jobseek        int,
   no_requ_jobseek      int
);

alter table t_prof_num comment '职业供求人数表';

/*==============================================================*/
/* Table: t_record_info                                         */
/*==============================================================*/
create table t_record_info
(
   record_info_id       int not null auto_increment,
   region_emp_id     int not null,
   region_emp_name   nvarchar(63) not null,
   region_name          nvarchar(63) not null,
   region_emp_contact nvarchar(63) not null,
   region_emp_contact_mobi varchar(15) not null,
   region_emp_contact_num varchar(15) not null,
   region_emp_fax    varchar(15) not null,
   state_flag           int not null,
   create_time          datetime not null,
   creator              int not null,
   revise_time          datetime,
   reviser              int,
   delete_flag          int not null,
   primary key (record_info_id)
);

alter table t_record_info comment '人力资源市场用户备案信息表';

/*==============================================================*/
/* Table: t_right                                               */
/*==============================================================*/
create table t_right
(
   right_id             int not null auto_increment,
   right_name           nvarchar(63) not null,
   right_desc           nvarchar(4000),
   create_time          datetime not null,
   creator              int not null,
   revise_time          datetime,
   reviser              int,
   delete_flag          int not null,
   primary key (right_id)
);

alter table t_right comment '权限表';

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   role_id              int not null auto_increment,
   role_name            nvarchar(63) not null,
   role_desc            nvarchar(4000),
   create_time          datetime not null,
   creator              int not null,
   revise_time          datetime,
   reviser              int,
   delete_flag          int not null,
   primary key (role_id)
);

alter table t_role comment '角色表';

/*==============================================================*/
/* Table: t_role_right                                          */
/*==============================================================*/
create table t_role_right
(
   right_id             int not null,
   role_id              int not null,
   create_time          datetime not null,
   creator              int not null,
   revise_time          datetime,
   reviser              int,
   delete_flag          int not null,
   primary key (right_id, role_id)
);

alter table t_role_right comment '角色权限表';

/*==============================================================*/
/* Table: t_sex_num                                             */
/*==============================================================*/
create table t_sex_num
(
   table_id             int,
   male_need            int,
   male_jobseek         int,
   female_need          int,
   female_jobseek       int,
   no_requ_need         int
);

alter table t_sex_num comment '性别供求人数表';

/*==============================================================*/
/* Table: t_tech_grade_num                                      */
/*==============================================================*/
create table t_tech_grade_num
(
   table_id             int,
   prof_level_5_need    int,
   prof_level_5_jobseek int,
   prof_level_4_need    int,
   prof_level_4_jobseek int,
   prof_level_3_need    int,
   prof_level_3_jobseek int,
   prof_level_2_need    int,
   prof_level_2_jobseek int,
   prof_level_1_need    int,
   prof_level_1_jobseek int,
   prim_prof_need       int,
   prim_prof_jobseek    int,
   medi_prof_need       int,
   medi_prof_jobseek    int,
   seni_prof_need       int,
   seni_prof_jobseek    int,
   no_tech_jobseek      int,
   no_requ_need         int
);

alter table t_tech_grade_num comment '技术等级供求人数表';

/*==============================================================*/
/* Table: t_total_num                                           */
/*==============================================================*/
create table t_total_num
(
   table_id             int,
   need_popu            int,
   jobseek_popu         int
);

alter table t_total_num comment '供求总体人数表';

/*==============================================================*/
/* Table: t_upload_period                                       */
/*==============================================================*/
create table t_upload_period
(
   upload_period_id     int not null auto_increment,
   start_date           date not null,
   end_date             date not null,
   create_time          datetime not null,
   creator              int not null,
   revise_time          datetime,
   reviser              int,
   delete_flag          int not null,
   primary key (upload_period_id)
);

alter table t_upload_period comment '上传期表';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   user_id              int not null auto_increment,
   username             varchar(63) not null,
   password             varchar(63) not null,
   fullname             nvarchar(63),
   superior             int,
   state_flag           int not null,
   create_time          datetime not null,
   creator              int not null,
   revise_time          datetime,
   reviser              int,
   delete_flag          int not null,
   primary key (user_id)
);

alter table t_user comment '用户表';

/*==============================================================*/
/* Table: t_user_right                                          */
/*==============================================================*/
create table t_user_right
(
   right_id             int not null,
   user_id              int not null,
   create_time          datetime not null,
   creator              int not null,
   revise_time          datetime,
   reviser              int,
   delete_flag          int not null,
   path                 nvarchar(100) not null,
   primary key (right_id, user_id, create_time)
);

alter table t_user_right comment '用户操作记录表';

/*==============================================================*/
/* Table: t_user_role                                           */
/*==============================================================*/
create table t_user_role
(
   role_id              int not null,
   user_id              int not null,
   create_time          datetime not null,
   creator              int not null,
   revise_time          datetime,
   reviser              int,
   delete_flag          int not null,
   primary key (role_id, user_id)
);

alter table t_user_role comment '用户角色表';

