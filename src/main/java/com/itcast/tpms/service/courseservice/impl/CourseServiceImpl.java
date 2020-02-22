package com.itcast.tpms.service.courseservice.impl;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.enums.PageUrlEnum;
import com.itcast.tpms.mapper.CourseMapper;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.CourseExample;
import com.itcast.tpms.service.courseservice.ICourseService;
import com.itcast.tpms.utils.currmidUtil.impl.CurrmidUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CurrmidUtil currmidUtil;

    @Override
    public PageDto<Course> getCourseBySearchDto(SearchDto searchDto) {

        PageDto<Course> coursePageDto = new PageDto<>(PageUrlEnum.COURSE_URL.getUrl());
        CourseExample example = new CourseExample();

        if (searchDto.getKeyword() != null && !"null".equals(searchDto.getKeyword())) {
            example.createCriteria().andNameLike("%" + searchDto.getKeyword() + "%");
        }

        searchDto.setTotal((int) courseMapper.countByExample(example));
        coursePageDto.init(searchDto);
        searchDto.setOffset((coursePageDto.getPage() - 1) * searchDto.getLimit());

        List<Course> courses = courseMapper.selectByExampleWithRowbounds(example, new RowBounds(searchDto.getOffset(), searchDto.getLimit()));
        Collections.sort(courses, (c1, c2) ->
                c2.getCredit().compareTo(c1.getCredit())
        );
        coursePageDto.setElements(courses);

        return coursePageDto;
    }

    @Override
    public List<Course> getAllCourse() {
        List<Course> courses = courseMapper.selectByExample(new CourseExample());
        return courses;
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public boolean addCourse(Course course) {
        course.setGmtCreated(new Date());
        course.setGmtModified(course.getGmtCreated());
        int insert = courseMapper.insert(course);
        return insert == 1;
    }

    @Override
    public boolean updateCourse(Course course) {
        course.setGmtModified(new Date());
        int i = courseMapper.updateByPrimaryKey(course);
        return i == 1;
    }

    @Override
    public boolean deleteCourse(Long courseId) {
        boolean b = currmidUtil.canDeleteCourse(courseId);
        if (b) {

            int delete = courseMapper.deleteByPrimaryKey(courseId);
            return delete == 1;
        }
        return false;
    }

    @Override
    public boolean addOrUpdateCourse(Course course) {

        if (course.getId() == null) {
            return addCourse(course);
        } else {
            return updateCourse(course);
        }
    }
}
