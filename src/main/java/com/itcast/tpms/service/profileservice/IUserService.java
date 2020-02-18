package com.itcast.tpms.service.profileservice;

import com.itcast.tpms.model.User;

import java.util.List;

/**
 * 用户service
 */
public interface IUserService {

    List<User> getUserBypage(Integer page, Integer limit);

    boolean addUser(User user);

    boolean updateUser(User preUser, User user);

    boolean deleteUser(Long userId);

}
