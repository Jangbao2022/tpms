package com.itcast.tpms.service.curriculumSerivce.impl;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.enums.PageUrlEnum;
import com.itcast.tpms.exp.CourseExp;
import com.itcast.tpms.exp.CourseExpList;
import com.itcast.tpms.exp.CurriculumExp;
import com.itcast.tpms.exp.OneLevelList;
import com.itcast.tpms.mapper.CourseMapper;
import com.itcast.tpms.mapper.CurriculumMapper;
import com.itcast.tpms.mapper.MajorMapper;
import com.itcast.tpms.mapper.ModuleMapper;
import com.itcast.tpms.model.*;
import com.itcast.tpms.service.curriculumSerivce.ICurriculumService;
import com.itcast.tpms.utils.currmidUtil.ICurrmidUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CurriculumServiceImpl implements ICurriculumService {

    @Autowired
    private CurriculumMapper curriculumMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ICurrmidUtil currmidUtil;

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public PageDto<CurriculumExp> getCurriculumBySearchDto(SearchDto searchDto) {

        PageDto<CurriculumExp> curriculumPageDto = new PageDto<>(PageUrlEnum.CURRICULUM_URL.getUrl());

        CurriculumExample example = new CurriculumExample();

        if (searchDto.getKeyword() != null && !"null".equals(searchDto.getKeyword())) {
            example.createCriteria().andNameLike("%" + searchDto.getKeyword() + "%");
        }

        searchDto.setTotal((int) curriculumMapper.countByExample(example));
        curriculumPageDto.init(searchDto);
        searchDto.setOffset((curriculumPageDto.getPage() - 1) * searchDto.getLimit());

        List<Curriculum> curricula = curriculumMapper.selectByExampleWithRowbounds(example, new RowBounds(searchDto.getOffset(), searchDto.getLimit()));

        List<CurriculumExp> curriculumExps = new ArrayList<>();
        for (Curriculum curriculum : curricula) {
            CurriculumExp curriculumById = getCurriculumById(curriculum.getId());
            curriculumExps.add(curriculumById);
        }

        curriculumPageDto.setElements(curriculumExps);

        return curriculumPageDto;
    }

    @Override
    public CurriculumExp getCurriculumById(Long curriculumId) {
        CurriculumExp curriculumExp = new CurriculumExp();

        Curriculum curriculum = curriculumMapper.selectByPrimaryKey(curriculumId);
        Major major = majorMapper.selectByPrimaryKey(curriculum.getMajorId());

        curriculumExp.setCurr(curriculum);
        curriculumExp.setMajor(major);

        currmidUtil.pottingCurrExp(curriculumExp);
        return curriculumExp;
    }

    public void changeCurrent(Long majorId) {
        CurriculumExample example = new CurriculumExample();
        example.createCriteria().andMajorIdEqualTo(majorId);

        List<Curriculum> curriculums = curriculumMapper.selectByExample(example);
        if (curriculums.size() > 0) {
            Curriculum preCurriculum = curriculums.get(0);
            preCurriculum.setCurrent(0);
            curriculumMapper.updateByPrimaryKey(preCurriculum);
        }
    }

    @Override
    public boolean addCurriculum(Curriculum curriculum) {
        curriculum.setNeedObligatoryCredit(3);
        curriculum.setNeedElectiveCredit(3);
        curriculum.setNeedElectiveClassHour(30);
        curriculum.setNeedObligatoryClassHour(30);
        curriculum.setGmtCreated(new Date());
        curriculum.setGmtModified(curriculum.getGmtCreated());
        int insert = curriculumMapper.insert(curriculum);
        return insert == 1;
    }

    @Override
    public boolean updateCurriculum(Curriculum curriculum) {
        curriculum.setGmtModified(new Date());
        int i = curriculumMapper.updateByPrimaryKeySelective(curriculum);
        return i == 1;
    }

    @Override
    public boolean deleteCurriculum(Long curriculumId, Integer type) {

        currmidUtil.deleteCurr(curriculumId, type);
        int delete = curriculumMapper.deleteByPrimaryKey(curriculumId);
        return delete == 1;
    }

    @Override
    public boolean addOrUpdateCurriculum(Curriculum curriculum, Integer type) {
        if (curriculum.getCurrent().equals(1)) {
            changeCurrent(curriculum.getMajorId());
        }

        if (curriculum.getId() == null) {
            return addCurriculum(curriculum);
        } else {
            //删除已有的课程
            currmidUtil.deleteCurr(curriculum.getId(), type);
            return updateCurriculum(curriculum);
        }
    }

    @Override
    public void deleteCurrByMajorId(Long majorId) {
        CurriculumExample example = new CurriculumExample();
        example.createCriteria().andMajorIdEqualTo(majorId);
        List<Curriculum> curricula = curriculumMapper.selectByExample(example);

        for (Curriculum curriculum : curricula) {
            deleteCurriculum(curriculum.getId(), 1);
            deleteCurriculum(curriculum.getId(), 2);
        }
    }

    @Override
    public Long getLastCurrId() {
        CurriculumExample example = new CurriculumExample();
        List<Curriculum> curricula = curriculumMapper.selectByExample(example);
        return curricula.get(curricula.size() - 1).getId();

    }

    @Override
    public OneLevelList seeMore(CurriculumExp currExp) {

        CourseExpList courseList = new CourseExpList();
        List<Course> courses = currExp.getCourses();
        for (Course cours : courses) {
            CourseExp courseExp = new CourseExp();
            CurriculumMidCourse mid = currmidUtil.getCurrMidCourse(currExp.getCurr().getId(), cours.getId(), 1);

            courseExp.setTeach(mid.getTeach());
            courseExp.setExperiment(mid.getExperiment());
            courseExp.setPractice(mid.getPractice());
            courseExp.setOac(mid.getOac());
            courseExp.setSemester(mid.getSemester());

            Module module = moduleMapper.selectByPrimaryKey(cours.getModuleId());
            Module preModule = moduleMapper.selectByPrimaryKey(module.getPreLevelId());
            courseExp.setCourse(cours);

            courseExp.setOneModuleName(preModule.getName());
            courseExp.setTwoModuleName(module.getName());

            courseList.getCourseExps().add(courseExp);
        }
        OneLevelList oneLevelList = courseList.toTwoLevelList().toOneLevelList();
        oneLevelList.init();
        return oneLevelList;
    }

}
