package com.itcast.tpms.controller;

import com.itcast.tpms.enums.PageLimitEnum;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.Major;
import com.itcast.tpms.service.courseservice.ICourseService;
import com.itcast.tpms.service.majorService.IMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/major")
public class MajorController {

    @Autowired
    private IMajorService majorService;

    @RequestMapping("getMajorByPage")
    public String getMajorByPage(Integer page, Model model) {
        if (page == null || page < 0) {
            page = 0;
        }
        List<Major> majors = majorService.getMajorByPage(page, PageLimitEnum.MAJOR_LIMIT.getLimit());
        model.addAttribute("majors", majors);
        return "major";
    }
}
