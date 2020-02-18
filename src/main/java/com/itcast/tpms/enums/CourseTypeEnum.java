package com.itcast.tpms.enums;

public enum CourseTypeEnum {

    OBLIGATORY(1, "必修"),
    ELECTIVE(2, "选修"),
    ;

    private Integer type;
    private String describe;

    CourseTypeEnum(int type, String describe) {
        this.type = type;
        this.describe = describe;
    }

    public Integer getType() {
        return type;
    }

    public String getDescribe() {
        return describe;
    }
}
