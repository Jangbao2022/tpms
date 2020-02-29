package com.itcast.tpms.exp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 一级模块表格
 */
@Data
public class OneLevelList {

    private List<TwoLevelList> oneLevels = new ArrayList<>();
    private Integer totalCredit;
    private Integer totalCourse;

    public void init() {
        totalCredit = 0;
        totalCourse = 1;
        for (TwoLevelList twoLevelList : oneLevels) {
            twoLevelList.init();
            totalCourse += twoLevelList.getTotalCourse();
            totalCredit += twoLevelList.getTotalCredit();
        }
    }

}
