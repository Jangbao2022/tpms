package com.itcast.tpms.service.userservice.impl;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.enums.PageUrlEnum;
import com.itcast.tpms.enums.UserPowerEnum;
import com.itcast.tpms.exp.UserExp;
import com.itcast.tpms.mapper.UserMapper;
import com.itcast.tpms.model.User;
import com.itcast.tpms.model.UserExample;
import com.itcast.tpms.service.userservice.IUserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public PageDto<UserExp> getUserExpBypage(Integer page, Integer limit) {
        PageDto<UserExp> userExpPageDto = new PageDto<>(PageUrlEnum.USER_URL.getUrl());

        long total = userMapper.countByExample(new UserExample());

        userExpPageDto.countTotalPage((int) total, limit);
        userExpPageDto.countPreAndAfter(page);

        int offset = (userExpPageDto.getPage() - 1) * limit;
        List<User> users = userMapper.selectByExampleWithRowbounds(new UserExample(), new RowBounds(offset, limit));

        List<UserExp> userExps = new ArrayList<>();
        for (User user : users) {
            UserExp userExp = new UserExp();
            userExp.setTitle(UserPowerEnum.getDescribe(user.getPower()));
            userExp.setUser(user);

            userExps.add(userExp);
        }
        userExpPageDto.setElements(userExps);

        return userExpPageDto;
    }


    @Override
    public User getUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(User user) {
        user.setGmtCreated(new Date());
        user.setGmtModified(user.getGmtCreated());

        int insert = userMapper.insert(user);
        return insert == 1;
    }

    @Override
    public boolean updateUser(User preUser, User user) {
        preUser.setAccount(user.getAccount());
        preUser.setPassword(user.getPassword());

        return updateUser(preUser);
    }

    @Override
    public boolean updateUser(User user) {
        user.setGmtModified(new Date());
        int i = userMapper.updateByPrimaryKey(user);
        return i == 1;
    }

    @Override
    public boolean addOrUpdateUser(User user) {
        if (user.getId() == null) {
            return addUser(user);
        } else {
            return updateUser(user);
        }
    }

    @Override
    public boolean deleteUserById(Long userId) {
        int delete = userMapper.deleteByPrimaryKey(userId);
        return delete == 1;
    }
}
