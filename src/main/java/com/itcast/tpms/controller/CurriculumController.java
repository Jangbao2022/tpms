package com.itcast.tpms.controller;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.enums.PageLimitEnum;
import com.itcast.tpms.exp.CurriculumExp;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.Curriculum;
import com.itcast.tpms.model.CurriculumMidCourse;
import com.itcast.tpms.model.Major;
import com.itcast.tpms.service.courseservice.ICourseService;
import com.itcast.tpms.service.curriculumSerivce.ICurriculumService;
import com.itcast.tpms.service.majorService.IMajorService;
import com.itcast.tpms.utils.currmidUtil.ICurrmidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    private ICurriculumService curriculumService;

    @Autowired
    private IMajorService majorService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICurrmidUtil currmidUtil;

    @RequestMapping("getCurriculumByPage")
    public String getCurriculumByPage(Integer page, Model model) {
        if (page == null || page < 0) {
            page = 0;
        }
        PageDto<CurriculumExp> currExpPageDto = curriculumService.getCurriculumByPage(page, PageLimitEnum.CURRICULUM_LIMIT.getLimit());
        model.addAttribute("pageDto", currExpPageDto);
        return "index";
    }

    @RequestMapping("addCurriculumPage")
    public String addCurriculumPage(Model model) {
        List<Major> allMajor = majorService.getAllMajor();
        model.addAttribute("majors", allMajor);

        List<Course> allCourse = courseService.getAllCourse();
        model.addAttribute("courses", allCourse);
        return "IPCurriculumPage";
    }

    @RequestMapping("updateCurriculumPageById")
    public String updateCurriculumPageById(Long curriculumId, Model model) {
        CurriculumExp curriculumExp = curriculumService.getCurriculumById(curriculumId);
        model.addAttribute("curriculumExp", curriculumExp);

        return "forward:/curriculum/addCurriculumPage";
    }

    @RequestMapping("deleteCurriculumPageById")
    public String deleteCurriculumPageById(Long curriculumId) {

        curriculumService.deleteCurriculum(curriculumId);
        return "redirect:/curriculum/getCurriculumByPage";
    }

    @RequestMapping("seeMoreById")
    public String seeMoreById(Long curriculumId, Model model) {
        CurriculumExp curriculumExp = curriculumService.getCurriculumById(curriculumId);
        model.addAttribute("currExp", curriculumExp);

        return "currSeeMore";
    }

    @RequestMapping("deleteCourseAtCurr")
    public String deleteCourseAtCurr(CurriculumMidCourse curriculumMidCourse) {

        currmidUtil.deleteCourseAtCurr(curriculumMidCourse);
        return "redirect:/curriculum/seeMoreById?curriculumId=" + curriculumMidCourse.getCurriculumId();
    }

    @RequestMapping("addOrUpdateCurriculum")
    public String addOrUpdateCourse(Curriculum curriculum, HttpServletRequest request) {

        String[] courseIds = request.getParameterValues("courseId");

        Long curriculumId = curriculum.getId();

        //添加课程方案
        curriculumService.addOrUpdateCurriculum(curriculum);
        if (curriculumId == null) {
            //或取最近插入的curr的id
            curriculumId = curriculumService.getLastCurrId();
        }
        //重新添加课程
        currmidUtil.addCurr(curriculumId, courseIds);

        return "redirect:/curriculum/getCurriculumByPage";
    }
}
