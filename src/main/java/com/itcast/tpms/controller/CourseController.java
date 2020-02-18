package com.itcast.tpms.controller;

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
    public String getCourseByPage(Integer page, Model model) {
        if (page == null || page < 0) {
            page = 0;
        }
        List<Course> courses = courseService.getCourseByPage(page, PageLimitEnum.COURSE_LIMIT.getLimit());
        model.addAttribute("courses", courses);
        return "course";
    }
}
