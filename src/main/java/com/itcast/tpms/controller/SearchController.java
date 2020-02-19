package com.itcast.tpms.controller;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.enums.PageLimitEnum;
import com.itcast.tpms.exp.CurriculumExp;
import com.itcast.tpms.service.curriculumSerivce.ICurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private ICurriculumService curriculumService;

    @RequestMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Integer page, Model model) {

        PageDto<CurriculumExp> curriculumByKeyWord = curriculumService.getCurriculumByKeyWord(keyword, page, PageLimitEnum.CURRICULUM_LIMIT.getLimit());
        curriculumByKeyWord.setPageUrl(curriculumByKeyWord.getPageUrl() + "?keyword=" + keyword);
        model.addAttribute("pageDto", curriculumByKeyWord);
        return "index";
    }
}
