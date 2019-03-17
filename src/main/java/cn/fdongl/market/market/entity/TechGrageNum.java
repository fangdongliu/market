package cn.fdongl.market.market.entity;

import lombok.Data;

@Data
public class TechGrageNum {

    Integer profLevel5Need;//职业资格五级（初级技能）需求人数
    Integer profLevel5Jobseek;//职业资格五级（初级技能）求职人数
    Integer profLevel4Need;//职业资格四级（中级技能）需求人数
    Integer profLevel4Jobseek;//职业资格四级（中级技能）求职人数
    Integer profLevel3Need;//职业资格三级（高级技能）需求人数
    Integer profLevel3Jobseek;//职业资格三级（高级技能）求职人数
    Integer profLevel2Need;//职业资格二级（技师）需求人数
    Integer profLevel2Jobseek;//职业资格二级（技师）求职人数
    Integer profLevel1Need;//职业资格一级（高级技师）需求人数
    Integer profLevel1Jobseek;//职业资格一级（高级技师）求职人数
    Integer primProfNeed;//初级专业技术职务需求人数
    Integer primProfJobseek;//初级专业技术职务求职人数
    Integer mediProfNeed;//中级专业技术职务需求人数
    Integer mediProfJobseek;//中级专业技术职务求职人数
    Integer seniProfNeed;//高级专业技术职务需求人数
    Integer seniProfJobseek;//高级专业技术职务求职人数
    Integer noTechJobseek;//无技术等级或职务求职人数
    Integer noRequNeed;//无要求需求人数
}
