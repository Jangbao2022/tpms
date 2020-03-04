package com.itcast.tpms.service.courseservice.impl;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.enums.PageUrlEnum;
import com.itcast.tpms.exp.CourseExp;
import com.itcast.tpms.mapper.CourseMapper;
import com.itcast.tpms.mapper.ModuleMapper;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.CourseExample;
import com.itcast.tpms.service.courseservice.ICourseService;
import com.itcast.tpms.utils.currmidUtil.impl.CurrmidUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CurrmidUtil currmidUtil;

    @Autowired
    private ModuleMapper moduleMapper;


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
    public List<List<CourseExp>> getAllCourseExp() {
        HashMap<Long, List<CourseExp>> map = new HashMap<>();
        List<Course> allCourse = getAllCourse();
        for (Course course : allCourse) {
            CourseExp courseExp = new CourseExp();
            courseExp.setCourse(course);
            Long moduleId = course.getModuleId();
            courseExp.setTwoModuleName(moduleMapper.selectByPrimaryKey(moduleId).getName());
            if (!map.containsKey(moduleId)) {
                map.put(moduleId, new ArrayList<>());
            }
            map.get(moduleId).add(courseExp);
        }
        List<List<CourseExp>> courseExpsList = new ArrayList<>();
        for (Long key : map.keySet()) {
            courseExpsList.add(map.get(key));
        }
        return courseExpsList;
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
        int i = courseMapper.updateByPrimaryKeySelective(course);
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

    @Override
    public boolean deleteCourseAtModule(Long courseId) {
        Course course = new Course();
        course.setId(courseId);
        course.setModuleId(1L);
        updateCourse(course);
        return true;
    }

    @Override
    public boolean updateCourseByIds(Long moduleId, String courseIds[]) {
        if (courseIds != null) {
            for (String courseId : courseIds) {
                Course courseById = getCourseById(Long.parseLong(courseId));
                courseById.setModuleId(moduleId);
                updateCourse(courseById);
            }
        }
        return true;
    }
}
