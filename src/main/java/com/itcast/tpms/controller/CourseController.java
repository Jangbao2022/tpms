package com.itcast.tpms.controller;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.enums.PageLimitEnum;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.service.courseservice.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @RequestMapping("getCourseByPage")
    public String getCourseByPage(SearchDto searchDto, Model model) {

        searchDto.setLimit(PageLimitEnum.COURSE_LIMIT.getLimit());
        PageDto<Course> coursePageDto = courseService.getCourseBySearchDto(searchDto);
        model.addAttribute("pageDto", coursePageDto);
        return "course";
    }

    @RequestMapping("addCoursePage")
    public String addCoursePage() {

        return "IPCoursePage";
    }

    @RequestMapping("updateCoursePageById")
    public String updateCoursePageById(Long courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);

        return "IPCoursePage";
    }

    @RequestMapping("deleteCourseById")
    public String deleteCourseById(Long courseId, Model model) {

        boolean b = courseService.deleteCourse(courseId);
        if (!b) {
            model.addAttribute("message", "无法删除,可能存在级联关系");
            return "forward:/error";
        }
        return "redirect:/course/getCourseByPage";
    }

    @RequestMapping("addOrUpdateCourse")
    public String addOrUpdateCourse(Course course) {
        courseService.addOrUpdateCourse(course);
        return "redirect:/course/getCourseByPage";
    }


}
