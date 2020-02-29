package com.itcast.tpms.exp;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二级模块表格
 */
@Data
public class TwoLevelList {

    private String moduleName;
    private List<CourseExpList> twoLevels = new ArrayList<>();
    private Integer totalCredit;
    private Integer totalCourse;

    public void init() {
        totalCredit = 0;
        totalCourse = 1;
        for (CourseExpList courseList : twoLevels) {
            moduleName = courseList.getCourseExps().get(0).getOneModuleName();
            courseList.init();
            totalCourse += courseList.getTotalCourse();
            totalCredit += courseList.getTotalCredit();
        }

    }

    public OneLevelList toOneLevelList() {
        OneLevelList oneLevelList = new OneLevelList();
        Map<String, TwoLevelList> oneMap = new HashMap<>();
        for (CourseExpList courseExpList : twoLevels) {
            String oneModuleName = courseExpList.getCourseExps().get(0).getOneModuleName();
            if (!oneMap.keySet().contains(oneModuleName)) {

                TwoLevelList value = new TwoLevelList();

                oneLevelList.getOneLevels().add(value);
                oneMap.put(oneModuleName, value);
            }
            oneMap.get(oneModuleName).getTwoLevels().add(courseExpList);
        }
        return oneLevelList;
    }
}
