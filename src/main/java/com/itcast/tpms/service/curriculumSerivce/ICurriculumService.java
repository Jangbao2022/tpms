package com.itcast.tpms.service.curriculumSerivce;

import com.itcast.tpms.exp.CurriculumExp;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.Curriculum;
import com.itcast.tpms.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 培养方案service
 */
public interface ICurriculumService {

    List<CurriculumExp> getCurriculumByPage(Integer page, Integer limit);

    boolean addCurriculum(Curriculum curriculum);

    boolean updateCurriculum(Curriculum curriculum);

    boolean deleteCurriculum(Long curriculumId);

}
