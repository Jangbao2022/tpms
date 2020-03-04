package com.itcast.tpms.controller;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.enums.PageLimitEnum;
import com.itcast.tpms.exp.CurriculumExp;
import com.itcast.tpms.exp.ModuleExp;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.Module;
import com.itcast.tpms.service.courseservice.ICourseService;
import com.itcast.tpms.service.moduleService.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private ICourseService courseService;

    @RequestMapping("getModuleByPage")
    public String getModuleByPage(SearchDto searchDto, Model model) {
        searchDto.setLimit(PageLimitEnum.MODULE_LIMIT.getLimit());
        PageDto<Module> modulePageDto = moduleService.getModuleBySearchDto(searchDto);
        model.addAttribute("pageDto", modulePageDto);
        return "module";
    }

    @RequestMapping("addModulePage")
    public String addModulePage(Model model) {
        List<Course> allCourse = courseService.getAllCourse();
        model.addAttribute("courses", allCourse);
        List<Module> allOneLevelModule = moduleService.getAllOneLevelModule();
        model.addAttribute("allOneLevelModule", allOneLevelModule);

        return "IPModulePage";
    }

    @RequestMapping("updateModulePageById")
    public String updateModulePageById(Long moduleId, Model model) {
        ModuleExp moduleExp = moduleService.getModuleById(moduleId);
        model.addAttribute("moduleExp", moduleExp);
        return "forward:/module/addModulePage";
    }

//    @RequestMapping("addOrUpdateModule")
//    public String addOrUpdateModule(Module module) {
//        moduleService.addOrUpdateModule(module);
//        return "redirect:/module/getModuleByPage";
//    }

    @RequestMapping("deleteModuleById")
    public String deleteModuleById(Long moduleId, Model model) {
        boolean b = moduleService.deleteModuleById(moduleId);
        if (!b) {
            model.addAttribute("message", "无法删除,其他地方存在引用");
        }
        return "forward:/module/getModuleByPage";
    }

    @RequestMapping("addOrUpdateModule")
    public String addOrUpdateCourse(Module module, HttpServletRequest request) {

        String[] courseIds = request.getParameterValues("courseId");

        Long moduleId = module.getId();

        //添加课程方案
        moduleService.addOrUpdateModule(module);

        //修改课程
        courseService.updateCourseByIds(moduleId, courseIds);

        return "redirect:/module/getModuleByPage";
    }

    @RequestMapping("seeMoreById")
    public String seeMoreById(Long moduleId, Model model) {
        ModuleExp moduleExp = moduleService.getModuleById(moduleId);
        model.addAttribute("moduleExp", moduleExp);

        return "moduleSeeMore";
    }

    @RequestMapping("deleteCourseAtModule")
    public String deleteCourseAtModule(Long courseId, Model model) {
        courseService.deleteCourseAtModule(courseId);
        return "redirect:/module/seeMoreById";
    }
}
