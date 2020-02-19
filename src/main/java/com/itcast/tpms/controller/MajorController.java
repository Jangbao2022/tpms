package com.itcast.tpms.controller;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.enums.PageLimitEnum;
import com.itcast.tpms.model.Major;
import com.itcast.tpms.service.curriculumSerivce.ICurriculumService;
import com.itcast.tpms.service.majorService.IMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/major")
public class MajorController {

    @Autowired
    private IMajorService majorService;

    @Autowired
    private ICurriculumService curriculumService;

    @RequestMapping("getMajorByPage")
    public String getMajorByPage(Integer page, Model model) {
        if (page == null || page < 0) {
            page = 0;
        }
        PageDto<Major> majorPageDto = majorService.getMajorByPage(page, PageLimitEnum.MAJOR_LIMIT.getLimit());
        model.addAttribute("pageDto", majorPageDto);
        return "major";
    }

    @RequestMapping("addMajorPage")
    public String addCoursePage() {

        return "IPMajorPage";
    }

    @RequestMapping("updateMajorPageById")
    public String updateCoursePageById(Long majorId, Model model) {
        Major major = majorService.getMajorById(majorId);
        model.addAttribute("major", major);
        return "IPMajorPage";
    }

    @RequestMapping("addOrUpdatcurriculumIdeMajor")
    public String addOrUpdateMajor(Major major) {
        majorService.addOrUpdateMajor(major);
        return "redirect:/major/getMajorByPage";
    }

    @RequestMapping("deleteMajorById")
    public String deleteMajorById(Long majorId) {
        curriculumService.deleteCurrByMajorId(majorId);
        majorService.deleteMajorById(majorId);
        return "redirect:/major/getMajorByPage";
    }
}
