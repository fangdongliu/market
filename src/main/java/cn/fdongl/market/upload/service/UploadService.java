package cn.fdongl.market.upload.service;

import cn.fdongl.market.upload.mapper.from.SelectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UploadService {
    @Autowired
    SelectMapper selectMapper;

    @Transactional(transactionManager = "secondTransactionManager")
    public void upload(){
        selectMapper.insertAgeNum(8);
       // selectMapper.insertCareerDic(8);
        throw new RuntimeException();
//        selectMapper.insertDegreeNum(8);
//        selectMapper.insertEmployerNum(8);
//        selectMapper.insertIndustryNum(8);
//        selectMapper.insertJobLeastNeeded(8);
//        selectMapper.insertJobSeekerNum(8);
//        selectMapper.insertProfNum(8);
//        selectMapper.insertMostNeeded(8);
//        selectMapper.insertTotalNum(8);
//        selectMapper.insertTechGradeNum(8);
//        selectMapper.insertSexNum(8);
    }

}
