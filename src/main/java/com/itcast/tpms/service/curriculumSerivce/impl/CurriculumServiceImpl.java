package com.itcast.tpms.service.curriculumSerivce.impl;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.enums.PageUrlEnum;
import com.itcast.tpms.exp.CurriculumExp;
import com.itcast.tpms.mapper.CurriculumMapper;
import com.itcast.tpms.mapper.MajorMapper;
import com.itcast.tpms.model.Curriculum;
import com.itcast.tpms.model.CurriculumExample;
import com.itcast.tpms.model.Major;
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
    private ICurrmidUtil currmidUtil;

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
        curriculum.setGmtCreated(new Date());
        curriculum.setGmtModified(curriculum.getGmtCreated());
        int insert = curriculumMapper.insert(curriculum);
        return insert == 1;
    }

    @Override
    public boolean updateCurriculum(Curriculum curriculum) {
        curriculum.setGmtModified(new Date());
        int i = curriculumMapper.updateByPrimaryKey(curriculum);
        return i == 1;
    }

    @Override
    public boolean deleteCurriculum(Long curriculumId) {

        currmidUtil.deleteCurr(curriculumId);
        int delete = curriculumMapper.deleteByPrimaryKey(curriculumId);
        return delete == 1;
    }

    @Override
    public boolean addOrUpdateCurriculum(Curriculum curriculum) {
        if (curriculum.getCurrent().equals(1)) {
            changeCurrent(curriculum.getMajorId());
        }

        if (curriculum.getId() == null) {
            return addCurriculum(curriculum);
        } else {
            //删除已有的课程
            currmidUtil.deleteCurr(curriculum.getId());
            return updateCurriculum(curriculum);
        }
    }

    @Override
    public void deleteCurrByMajorId(Long majorId) {
        CurriculumExample example = new CurriculumExample();
        example.createCriteria().andMajorIdEqualTo(majorId);
        List<Curriculum> curricula = curriculumMapper.selectByExample(example);

        for (Curriculum curriculum : curricula) {
            deleteCurriculum(curriculum.getId());
        }
    }

    @Override
    public Long getLastCurrId() {
        CurriculumExample example = new CurriculumExample();
        List<Curriculum> curricula = curriculumMapper.selectByExample(example);
        return curricula.get(curricula.size() - 1).getId();

    }
}
