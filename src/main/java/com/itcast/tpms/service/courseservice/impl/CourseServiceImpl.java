package com.itcast.tpms.service.courseservice.impl;

import com.itcast.tpms.mapper.CourseMapper;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.CourseExample;
import com.itcast.tpms.service.courseservice.ICourseService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getCourseByPage(Integer page, Integer limit) {

        List<Course> courses = courseMapper.selectByExampleWithRowbounds(new CourseExample(), new RowBounds(page, limit));
        return courses;
    }

    @Override
    public boolean addCourse(Course course) {
        return false;
    }

    @Override
    public boolean updateCourse(Course Course) {
        return false;
    }

    @Override
    public boolean deleteCourse(Long courseId) {

        return false;
    }

}
