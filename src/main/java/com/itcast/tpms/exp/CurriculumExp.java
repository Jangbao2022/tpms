package com.itcast.tpms.exp;

import com.itcast.tpms.enums.CourseTypeEnum;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.Curriculum;
import com.itcast.tpms.model.Major;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 课程方案扩展
 */

@Data
public class CurriculumExp {

    //普通课程方案
    private Curriculum curr;

    private OneLevelList oneLevelList;

    //专业
    private Major major;

    //课程集合
    private List<Course> courses;

    //
    private Set<Long> courseIds;

    //学分学时情况
    private Study study = new Study();

    /**
     * 添加学分
     *
     * @param course
     */
    public void addStudy(Course course) {
        this.study.studyDoCourse(course);
        this.study.setLack(this.curr);
    }


}
