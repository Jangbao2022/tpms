package com.itcast.tpms.service.curriculumSerivce;

import com.itcast.tpms.dto.PageDto;
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

    PageDto<CurriculumExp> getCurriculumByPage(Integer page, Integer limit);

    PageDto<CurriculumExp> getCurriculumByKeyWord(String keyword, Integer page, Integer limit);

    CurriculumExp getCurriculumById(Long curriculumId);

    boolean addCurriculum(Curriculum curriculum);

    boolean updateCurriculum(Curriculum curriculum);

    boolean deleteCurriculum(Long curriculumId);

    boolean addOrUpdateCurriculum(Curriculum curriculum);

    /**
     * 删除专业相关的课程方案
     *
     * @param majorId
     */
    void deleteCurrByMajorId(Long majorId);


}
