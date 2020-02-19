package com.itcast.tpms.utils.currmidUtil.impl;

import com.itcast.tpms.exp.CurriculumExp;
import com.itcast.tpms.mapper.CourseMapper;
import com.itcast.tpms.mapper.CurriculumMidCourseMapper;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.CurriculumMidCourse;
import com.itcast.tpms.model.CurriculumMidCourseExample;
import com.itcast.tpms.utils.currmidUtil.ICurrmidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.*;

@Component
public class CurrmidUtil implements ICurrmidUtil {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CurriculumMidCourseMapper curriculumMidCourseMapper;

    @Override
    public void pottingCurrExp(CurriculumExp curriculumExp) {

        CurriculumMidCourseExample example = new CurriculumMidCourseExample();
        example.createCriteria().andCurriculumIdEqualTo(curriculumExp.getCurr().getId());

        //得到中间节点集合
        List<CurriculumMidCourse> currMids = curriculumMidCourseMapper.selectByExample(example);
        List<Course> courses = new ArrayList<>();
        Set<Long> courseIds = new HashSet<>();
        for (CurriculumMidCourse currMid : currMids) {

            Course course = courseMapper.selectByPrimaryKey(currMid.getCourseId());
            curriculumExp.addStudy(course);
            courses.add(course);
            courseIds.add(course.getId());
        }
        Collections.sort(courses, (c1, c2) ->
                c2.getCredit().compareTo(c1.getCredit())
        );
        curriculumExp.setCourseIds(courseIds);
        curriculumExp.setCourses(courses);
    }

    @Override
    public boolean canDeleteCourse(Long courseId) {
        CurriculumMidCourseExample example = new CurriculumMidCourseExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<CurriculumMidCourse> curriculumMidCourses = curriculumMidCourseMapper.selectByExample(example);

        return curriculumMidCourses.size() == 0;
    }

    @Override
    public void deleteCurr(Long currId) {
        CurriculumMidCourseExample example = new CurriculumMidCourseExample();
        example.createCriteria().andCurriculumIdEqualTo(currId);
        curriculumMidCourseMapper.deleteByExample(example);
    }

    @Override
    public void addCurr(Long currId, List<Long> courseIds) {
        CurriculumMidCourse curriculumMidCourse = new CurriculumMidCourse();
        for (Long courseId : courseIds) {
            curriculumMidCourse.setCourseId(courseId);
            curriculumMidCourse.setCurriculumId(currId);
            curriculumMidCourse.setGmtCreated(new Date());
            curriculumMidCourse.setGmtModified(curriculumMidCourse.getGmtCreated());

            curriculumMidCourseMapper.insert(curriculumMidCourse);
        }
    }

    @Override
    public void addCurr(Long currId, String[] sCourseIds) {

        ArrayList<Long> courseIds = new ArrayList<>();
        for (String sCourseId : sCourseIds) {
            long courseId = Long.parseLong(sCourseId);
            courseIds.add(courseId);
        }

        addCurr(currId, courseIds);
    }

    @Override
    public boolean deleteCourseAtCurr(CurriculumMidCourse curriculumMidCourse) {
        CurriculumMidCourseExample example = new CurriculumMidCourseExample();
        example.createCriteria()
                .andCourseIdEqualTo(curriculumMidCourse.getCourseId())
                .andCurriculumIdEqualTo(curriculumMidCourse.getCurriculumId());
        int delete = curriculumMidCourseMapper.deleteByExample(example);
        return delete == 1;
    }


    //    @Override
//    public boolean canDeleteCurr(Long currId) {
//        CurriculumMifdCourseExample example = new CurriculumMidCourseExample();
//        example.createCriteria().andCurriculumIdEqualTo(currId);
//        List<CurriculumMidCourse> curriculumMidCourses = curriculumMidCourseMapper.selectByExample(example);
//
//        return curriculumMidCourses.size() == 0;
//    }
}

