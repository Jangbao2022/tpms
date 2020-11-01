package com.itcast.tpms.service.userservice.impl;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.enums.PageUrlEnum;
import com.itcast.tpms.enums.UserPowerEnum;
import com.itcast.tpms.exp.UserExp;
import com.itcast.tpms.mapper.UserMapper;
import com.itcast.tpms.model.Major;
import com.itcast.tpms.model.MajorExample;
import com.itcast.tpms.model.User;
import com.itcast.tpms.model.UserExample;
import com.itcast.tpms.service.userservice.IUserService;
import com.itcast.tpms.utils.PasswordUtil;
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
    public PageDto<UserExp> getUserExpBySearchDto(SearchDto searchDto) {
        PageDto<UserExp> userExpPageDto = new PageDto<>(PageUrlEnum.USER_URL.getUrl());

        UserExample example = new UserExample();
        if (searchDto.getKeyword() != null && !"null".equals(searchDto.getKeyword())) {
            example.createCriteria().andNameLike("%" + searchDto.getKeyword() + "%");
        }

        searchDto.setTotal((int) userMapper.countByExample(example));
        userExpPageDto.init(searchDto);
        searchDto.setOffset((userExpPageDto.getPage() - 1) * searchDto.getLimit());

        List<User> users = userMapper.selectByExampleWithRowbounds(example, new RowBounds(searchDto.getOffset(), searchDto.getLimit()));

        List<UserExp> userExps = new ArrayList<>();
        for (User user : users) {
            UserExp userExp = new UserExp();
            userExp.setTitle(UserPowerEnum.getDescribe(user.getPower()));
            String pass = PasswordUtil.decryptBASE64(user.getPassword());
            user.setPassword(pass);
            //设置密码不可见
            user.setPassword(user.getPassword().replaceAll(".", "*"));
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
        user.setPassword(PasswordUtil.encryptBASE64(user.getPassword().getBytes()));
        int insert = userMapper.insert(user);
        return insert == 1;
    }

    @Override
    public boolean updateUser(User preUser, User user) {
        preUser.setAccount(user.getAccount());
        preUser.setPassword(PasswordUtil.encryptBASE64(user.getPassword().getBytes()));

        return updateUser(preUser);
    }

    @Override
    public boolean updateUser(User user) {
        user.setGmtModified(new Date());
        int i = userMapper.updateByPrimaryKeySelective(user);
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

    @Override
    public boolean checkAccount(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountEqualTo(user.getAccount());
        return userMapper.countByExample(example) <= 0;
    }
}
