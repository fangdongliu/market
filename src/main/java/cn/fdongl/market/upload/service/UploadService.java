package cn.fdongl.market.upload.service;

import cn.fdongl.market.upload.mapper.from.SelectMapper;
import cn.fdongl.market.upload.mapper.to.UploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class UploadService {
    @Autowired
    SelectMapper selectMapper;

    public Integer isPeriodEnd(int periodId){
        return selectMapper.isPeriodEnd(periodId);
    }

    @Transactional
    public void upload(int periodId){
        try {
            selectMapper.updatePeriodStatus(periodId, 1);
            selectMapper.updateDict();
            selectMapper.insertPeriodTables(periodId);
            selectMapper.insertAgeNum(periodId);
            selectMapper.insertSexNum(periodId);
            selectMapper.insertDegreeNum(8);
            selectMapper.insertEmployerNum(8);
            selectMapper.insertIndustryNum(8);
            selectMapper.insertJobLeastNeeded(8);
            selectMapper.insertJobSeekerNum(8);
            selectMapper.insertProfNum(8);
            selectMapper.insertMostNeeded(8);
            selectMapper.insertTotalNum(8);
            selectMapper.insertTechGradeNum(8);
            selectMapper.insertSexNum(8);
            selectMapper.updatePeriodStatus(periodId, 2);
            selectMapper.insertUploadPeriod(periodId);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

}
