package com.itcast.tpms.exp;

import com.itcast.tpms.model.Major;
import com.itcast.tpms.model.User;
import lombok.Data;

/**
 * 用户扩展
 */

@Data
public class UserExp {

    private User user;
    private boolean isStudent;//是不是学生
    private Major major;//专业
    private String title;//职称

}
