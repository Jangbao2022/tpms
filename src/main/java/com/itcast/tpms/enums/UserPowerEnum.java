package com.itcast.tpms.enums;

public enum UserPowerEnum {

    STUDENT(1, "学生"),
    SUPPORT_STAFF(2, "教辅人员"),
    ORDINARY_TEACHER(3, "普通老师"),
    SUPER_TEACHER(4, "教研处老师"),
    SUPER_ADMINISTRATOR(5, "超级管理员"),
    ;

    private Integer power;
    private String describe;

    UserPowerEnum(int power, String describe) {
        this.power = power;
        this.describe = describe;
    }

    public Integer getPower() {
        return power;
    }

    public String getDescribe() {
        return describe;
    }

    //通过权限拿到描述
    public static String getDescribe(Integer power) {
        UserPowerEnum[] userPowerEnums = UserPowerEnum.values();
        for (UserPowerEnum userPowerEnum : userPowerEnums) {
            if (userPowerEnum.getPower().equals(power)) {
                return userPowerEnum.getDescribe();
            }
        }
        return null;
    }
}
