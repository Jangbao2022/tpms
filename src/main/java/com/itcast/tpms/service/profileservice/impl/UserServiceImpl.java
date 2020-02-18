package com.itcast.tpms.service.profileservice.impl;

import com.itcast.tpms.mapper.UserMapper;
import com.itcast.tpms.model.User;
import com.itcast.tpms.model.UserExample;
import com.itcast.tpms.service.profileservice.IUserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserBypage(Integer page, Integer limit) {

        List<User> users = userMapper.selectByExampleWithRowbounds(new UserExample(), new RowBounds(page, limit));
        return users;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User preUser, User user) {
        preUser.setAccount(user.getAccount());
        preUser.setPassword(user.getPassword());
        int i = userMapper.updateByPrimaryKey(preUser);
        return i == 1;
    }

    @Override
    public boolean deleteUser(Long userId) {
        return false;
    }
}
