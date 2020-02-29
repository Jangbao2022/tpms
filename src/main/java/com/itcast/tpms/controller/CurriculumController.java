package com.itcast.tpms.controller;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.enums.PageLimitEnum;
import com.itcast.tpms.exp.CurriculumExp;
import com.itcast.tpms.exp.OneLevelList;
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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getCurriculumByPage(SearchDto searchDto, Model model) {
        searchDto.setLimit(PageLimitEnum.CURRICULUM_LIMIT.getLimit());
        PageDto<CurriculumExp> currExpPageDto = curriculumService.getCurriculumBySearchDto(searchDto);
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

        curriculumService.deleteCurriculum(curriculumId, 1);
        curriculumService.deleteCurriculum(curriculumId, 2);
        return "redirect:/curriculum/getCurriculumByPage";
    }

    @RequestMapping("seeMoreById")
    public String seeMoreById(Long curriculumId, Model model) {
        CurriculumExp curriculumExp = curriculumService.getCurriculumById(curriculumId);
        OneLevelList oneLevelList = curriculumService.seeMore(curriculumExp);
        model.addAttribute("oneLevelList", oneLevelList);
        model.addAttribute("currExp", curriculumExp);

        return "currSeeMore";
    }

    @RequestMapping("deleteCourseAtCurr")
    public String deleteCourseAtCurr(CurriculumMidCourse curriculumMidCourse) {

        currmidUtil.deleteCourseAtCurr(curriculumMidCourse);
        return "redirect:/curriculum/seeMoreById?curriculumId=" + curriculumMidCourse.getCurriculumId();
    }

    @RequestMapping("addOrUpdateCurriculum")
    public String addOrUpdateCourse(Curriculum curriculum, @RequestParam("type") Integer type, HttpServletRequest request) {

        String[] courseIds = request.getParameterValues("courseId");

        Long curriculumId = curriculum.getId();

        //添加课程方案
        curriculumService.addOrUpdateCurriculum(curriculum, type);
        if (curriculumId == null) {
            //或取最近插入的curr的id
            curriculumId = curriculumService.getLastCurrId();
        }
        //重新添加课程
        currmidUtil.addCurr(curriculumId, courseIds, type);

        return "redirect:/curriculum/getCurriculumByPage";
    }

    @RequestMapping("updateCurrMidCourse")
    public String updateCurrMidCourse(CurriculumMidCourse curriculumMidCourse) {
        currmidUtil.addOrUpdateCurriculumMidCourse(curriculumMidCourse);
        return "redirect:/curriculum/getCurriculumByPage";
    }

    @RequestMapping("setCourseClassHour")
    public String setCourseClassHour(CurriculumMidCourse curriculumMidCourse, Model model) {

        Course courseById = courseService.getCourseById(curriculumMidCourse.getCourseId());
        CurriculumExp curriculumById = curriculumService.getCurriculumById(curriculumMidCourse.getCurriculumId());

        model.addAttribute("courseName", courseById.getName());
        model.addAttribute("classHour", courseById.getClassHour());
        model.addAttribute("curriculumName", curriculumById.getCurr().getName());
        model.addAttribute("curriculumMidCourse", curriculumMidCourse);
        return "setCourseClassHour";
    }
}
