package com.itcast.tpms.exp;

import lombok.Data;

import java.util.*;

/**
 * 课程表格
 */
@Data
public class CourseExpList {

    private String moduleName;
    private List<CourseExp> courseExps = new ArrayList<>();
    private Float totalCredit;
    private Integer totalClassHour;
    private Integer totalCourse;

    public void init() {
        totalCredit = 0.f;
        totalClassHour = 0;
        totalCourse = 1;
        for (CourseExp courseExp : courseExps) {
            moduleName = courseExp.getTwoModuleName();
            totalClassHour += courseExp.getCourse().getClassHour();
            totalCredit += courseExp.getCourse().getCredit();
            totalCourse++;
        }

    }

    public TwoLevelList toTwoLevelList() {
        TwoLevelList twoLevelList = new TwoLevelList();
        Map<String, CourseExpList> twoMap = new HashMap<>();

        for (CourseExp courseExp : courseExps) {
            String twoModuleName = courseExp.getTwoModuleName();
            if (!twoMap.keySet().contains(twoModuleName)) {
                CourseExpList value = new CourseExpList();

                twoLevelList.getTwoLevels().add(value);
                twoMap.put(twoModuleName, value);
            }
            twoMap.get(twoModuleName).getCourseExps().add(courseExp);

        }
        return twoLevelList;
    }
}
