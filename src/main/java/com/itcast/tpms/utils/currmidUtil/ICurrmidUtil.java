package com.itcast.tpms.utils.currmidUtil;

import com.itcast.tpms.exp.CurriculumExp;
import com.itcast.tpms.model.CurriculumMidCourse;

import java.util.List;

public interface ICurrmidUtil {

    /**
     * 封装curriculumExp
     *
     * @param curriculumExp
     * @return
     */
    void pottingCurrExp(CurriculumExp curriculumExp);


    /**
     * 能不能删除课程
     *
     * @param courseId
     * @return
     */
    boolean canDeleteCourse(Long courseId);

    /**
     * 通过方案id和课程id以及type获取中间表
     *
     * @param currId
     * @return
     */
    CurriculumMidCourse getCurrMidCourse(Long currId, Long courseId, Integer type);

//    /**
//     * 能不能删除培养方案
//     *
//     * @param currId
//     * @return
//     */
//    boolean canDeleteCurr(Long currId);


    /**
     * 删除培养方案
     *
     * @param currId
     */
    void deleteCurr(Long currId, Integer type);

    /**
     * 增加培养方案
     *
     * @param currId
     * @param courseIds
     */
    void addCurr(Long currId, List<Long> courseIds, Integer type);

    /**
     * 增加培养方案
     *
     * @param currId
     * @param courseIds
     */
    void addCurr(Long currId, String[] courseIds, Integer type);

    /**
     * 删除培养方案中的课程
     *
     * @param curriculumMidCourse
     * @return
     */
    boolean deleteCourseAtCurr(CurriculumMidCourse curriculumMidCourse);


    /**
     * 修改学时分配
     *
     * @param curriculumMidCourse
     * @return
     */
    boolean addOrUpdateCurriculumMidCourse(CurriculumMidCourse curriculumMidCourse);
}
