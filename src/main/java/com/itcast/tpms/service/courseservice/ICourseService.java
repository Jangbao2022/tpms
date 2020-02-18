package com.itcast.tpms.service.courseservice;

import com.itcast.tpms.model.Course;

import java.util.List;

/**
 * 课程service
 */
public interface ICourseService {

    List<Course> getCourseByPage(Integer page, Integer limit);

    boolean addCourse(Course course);

    boolean updateCourse(Course Course);

    boolean deleteCourse(Long courseId);

}
