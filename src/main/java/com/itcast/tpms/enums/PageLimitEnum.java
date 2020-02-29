package com.itcast.tpms.enums;

public enum PageLimitEnum {

    USER_LIMIT(10, "用户每页数量"),
    COURSE_LIMIT(10, "课程每页数量"),
    MAJOR_LIMIT(10, "专业每页数量"),
    CURRICULUM_LIMIT(10, "课程方案每页数量"),
    MODULE_LIMIT(10, "模块方案每页数量"),
    ;

    private Integer limit;
    private String describe;

    PageLimitEnum(int limit, String describe) {
        this.limit = limit;
        this.describe = describe;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getDescribe() {
        return describe;
    }
}
