package com.itcast.tpms.controller;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
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
    public String getMajorByPage(SearchDto searchDto, Model model) {
        searchDto.setLimit(PageLimitEnum.MAJOR_LIMIT.getLimit());
        PageDto<Major> majorPageDto = majorService.getMajorBySearchDto(searchDto);
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

    @RequestMapping("addOrUpdateMajor")
    public String addOrUpdateMajor(Major major) {
        majorService.addOrUpdateMajor(major);
        return "redirect:/major/getMajorByPage";
    }

    @RequestMapping("deleteMajorById")
    public String deleteMajorById(Long majorId, Model model) {
        boolean b = majorService.checkDeleteById(majorId);
        if (!b) {
            model.addAttribute("message", "无法删除,其他地方存在引用");
            return "forward:/major/getMajorByPage";
        }
        curriculumService.deleteCurrByMajorId(majorId);
        majorService.deleteMajorById(majorId);
        return "forward:/major/getMajorByPage";
    }
}
