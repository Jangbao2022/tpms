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
    private Float totalCredit;
    private Integer totalClassHour;
    private Integer totalCourse;

    public void init() {
        totalCredit = 0.f;
        totalClassHour = 0;
        totalCourse = 1;
        for (TwoLevelList twoLevelList : oneLevels) {
            twoLevelList.init();
            totalClassHour += twoLevelList.getTotalClassHour();
            totalCourse += twoLevelList.getTotalCourse();
            totalCredit += twoLevelList.getTotalCredit();
        }
    }

    public String oneLevelNameString() {
        String string = "";
        if (oneLevels.size() == 0) {
            return string;
        }
        for (TwoLevelList oneLevel : oneLevels) {
            string += oneLevel.getModuleName() + " ";
        }
        return string.substring(0, string.length() - 1);
    }

    public String oneLevelClassHourString() {

        String string = "";
        if (oneLevels.size() == 0) {
            return string;
        }
        for (TwoLevelList oneLevel : oneLevels) {
            string += oneLevel.getTotalClassHour() + " ";
        }
        return string.substring(0, string.length() - 1);
    }

    public String oneLevelCreditString() {
        String string = "";
        if (oneLevels.size() == 0) {
            return string;
        }
        for (TwoLevelList oneLevel : oneLevels) {
            string += oneLevel.getTotalCredit() + " ";
        }
        return string.substring(0, string.length() - 1);
    }
}
