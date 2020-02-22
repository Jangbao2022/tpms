package com.itcast.tpms.service.userservice;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.exp.UserExp;
import com.itcast.tpms.model.User;


/**
 * 用户service
 */
public interface IUserService {

    PageDto<UserExp> getUserExpBySearchDto(SearchDto searchDto);

    User getUserById(Long userId);

    boolean addUser(User user);

    /**
     * 修改账号密码
     *
     * @param preUser
     * @param user
     * @return
     */
    boolean updateUser(User preUser, User user);

    /**
     * 修改所有信息
     *
     * @param user
     * @return
     */
    boolean updateUser(User user);

    boolean addOrUpdateUser(User user);

    boolean deleteUserById(Long userId);

}
