package com.itcast.tpms.controller;

import com.itcast.tpms.enums.PageLimitEnum;
import com.itcast.tpms.exp.CurriculumExp;
import com.itcast.tpms.model.Curriculum;
import com.itcast.tpms.service.curriculumSerivce.ICurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("/curriculum")
public class CurriculumController {

    @Autowired
    private ICurriculumService curriculumService;

    @RequestMapping("/getCurriculumByPage")
    public String getCurriculumByPage(Integer page, Model model) {
        if (page == null || page < 0) {
            page = 0;
        }
        List<CurriculumExp> currExps = curriculumService.getCurriculumByPage(page, PageLimitEnum.COURSE_LIMIT.getLimit());
        model.addAttribute("currExps", currExps);
        return "index";
    }
}
