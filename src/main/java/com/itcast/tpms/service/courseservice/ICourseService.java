package com.itcast.tpms.service.courseservice;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.model.Course;

import java.util.List;

/**
 * 课程service
 */
public interface ICourseService {

    PageDto<Course> getCourseBySearchDto(SearchDto searchDto);

    Course getCourseById(Long courseId);

    List<Course> getAllCourse();

    boolean addCourse(Course course);

    boolean updateCourse(Course course);

    boolean deleteCourse(Long courseId);

    boolean addOrUpdateCourse(Course course);

    boolean updateCourseByIds(Long moduleId, String courseIds[]);

    boolean deleteCourseAtModule(Long courseId);

}
