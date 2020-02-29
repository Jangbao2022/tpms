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
    public CurriculumMidCourse getCurrMidCourse(Long currId, Long courseId, Integer type) {
        CurriculumMidCourseExample example = new CurriculumMidCourseExample();
        example.createCriteria()
                .andTypeEqualTo(type)
                .andCurriculumIdEqualTo(currId)
                .andCourseIdEqualTo(courseId);
        return curriculumMidCourseMapper.selectByExample(example).get(0);
    }

    @Override
    public void pottingCurrExp(CurriculumExp curriculumExp) {

        CurriculumMidCourseExample example = new CurriculumMidCourseExample();
        example.createCriteria()
                .andCurriculumIdEqualTo(curriculumExp.getCurr().getId())
                .andTypeEqualTo(1);

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
    public void deleteCurr(Long currId, Integer type) {
        CurriculumMidCourseExample example = new CurriculumMidCourseExample();
        example.createCriteria()
                .andCurriculumIdEqualTo(currId)
                .andTypeEqualTo(type);
        curriculumMidCourseMapper.deleteByExample(example);
    }

    @Override
    public void addCurr(Long currId, List<Long> courseIds, Integer type) {
        CurriculumMidCourse curriculumMidCourse = new CurriculumMidCourse();
        for (Long courseId : courseIds) {
            curriculumMidCourse.setCourseId(courseId);
            curriculumMidCourse.setType(type);
            curriculumMidCourse.setCurriculumId(currId);
            curriculumMidCourse.setGmtCreated(new Date());
            curriculumMidCourse.setGmtModified(curriculumMidCourse.getGmtCreated());

            curriculumMidCourseMapper.insert(curriculumMidCourse);
        }
    }

    @Override
    public void addCurr(Long currId, String[] sCourseIds, Integer type) {
        ArrayList<Long> courseIds = new ArrayList<>();
        if (sCourseIds != null) {
            for (String sCourseId : sCourseIds) {
                long courseId = Long.parseLong(sCourseId);
                courseIds.add(courseId);
            }
        }

        addCurr(currId, courseIds, type);
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


    @Override
    public boolean addOrUpdateCurriculumMidCourse(CurriculumMidCourse curriculumMidCourse) {
        CurriculumMidCourseExample example = new CurriculumMidCourseExample();
        example.createCriteria().andCurriculumIdEqualTo(curriculumMidCourse.getCurriculumId())
                .andCourseIdEqualTo(curriculumMidCourse.getCourseId());
        List<CurriculumMidCourse> curriculumMidCourses = curriculumMidCourseMapper.selectByExample(example);
        if (curriculumMidCourses.size() == 0) {
            return add(curriculumMidCourse);
        } else {
            return update(curriculumMidCourse);
        }
    }

    public boolean add(CurriculumMidCourse curriculumMidCourse) {
        return curriculumMidCourseMapper.insert(curriculumMidCourse) == 1;
    }

    public boolean update(CurriculumMidCourse curriculumMidCourse) {
        CurriculumMidCourseExample curriculumMidCourseExample = new CurriculumMidCourseExample();
        curriculumMidCourseExample.createCriteria()
                .andCourseIdEqualTo(curriculumMidCourse.getCourseId())
                .andCurriculumIdEqualTo(curriculumMidCourse.getCurriculumId());
        return curriculumMidCourseMapper.updateByExampleSelective(curriculumMidCourse, curriculumMidCourseExample) == 1;
    }
}

