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

import java.util.ArrayList;
import java.util.List;

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
        List<CurriculumMidCourse> currmids = curriculumMidCourseMapper.selectByExample(example);
        List<Course> courses = new ArrayList<>();
        for (CurriculumMidCourse currmid : currmids) {

            Course course = courseMapper.selectByPrimaryKey(currmid.getCourseId());
            curriculumExp.addStudy(course);
            courses.add(course);
        }

        curriculumExp.setCourses(courses);
    }

}

