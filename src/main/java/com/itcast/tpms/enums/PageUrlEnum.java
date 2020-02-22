package com.itcast.tpms.enums;

public enum PageUrlEnum {

    USER_URL("/user/getUerByPage", "user页面分页URL"),
    COURSE_URL("/course/getCourseByPage", "课程页面分页URL"),
    MAJOR_URL("/major/getMajorByPage", "专业页面分页URL"),
    CURRICULUM_URL("/curriculum/getCurriculumByPage", "方案课程页面分页URL"),

//    CURRICULUM_SEARCH_URL("/search/curriculum", "search方案课程页面分页URL"),


    ;

    private String url;
    private String describe;

    PageUrlEnum(String url, String describe) {
        this.url = url;
        this.describe = describe;
    }

    public String getUrl() {
        return url;
    }

    public String getDescribe() {
        return describe;
    }
}
