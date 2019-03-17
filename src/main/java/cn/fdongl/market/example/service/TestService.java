package cn.fdongl.market.example.service;

import cn.fdongl.market.security.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {

    @Autowired
    UserMapper userMapper;

    @Transactional
    public void hh() throws RuntimeException {

        userMapper.test2();

        throw new RuntimeException();

    }

}
