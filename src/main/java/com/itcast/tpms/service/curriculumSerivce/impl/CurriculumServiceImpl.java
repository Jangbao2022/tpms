package com.itcast.tpms.service.curriculumSerivce.impl;

import com.itcast.tpms.dto.PageDto;
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
import java.util.HashSet;
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
    public PageDto<CurriculumExp> getCurriculumByPage(Integer page, Integer limit) {
        PageDto<CurriculumExp> curriculumPageDto = new PageDto<>(PageUrlEnum.CURRICULUM_URL.getUrl());

        long total = curriculumMapper.countByExample(new CurriculumExample());

        curriculumPageDto.countTotalPage((int) total, limit);
        curriculumPageDto.countPreAndAfter(page);

        List<CurriculumExp> currExps = new ArrayList<>();
        int offset = (curriculumPageDto.getPage() - 1) * limit;

        List<Curriculum> currs = curriculumMapper.selectByExampleWithRowbounds(new CurriculumExample(), new RowBounds(offset, limit));
        for (Curriculum curr : currs) {

            //通过Id封装
            CurriculumExp currExp = getCurriculumById(curr.getId());

            //将扩展的课程方案加入集合
            currExps.add(currExp);
        }

        curriculumPageDto.setElements(currExps);

        return curriculumPageDto;
    }

    @Override
    public PageDto<CurriculumExp> getCurriculumByKeyWord(String keyword, Integer page, Integer limit) {

        PageDto<CurriculumExp> curriculumPageDto = new PageDto<>(PageUrlEnum.CURRICULUM_SEARCH_URL.getUrl());

        HashSet<CurriculumExp> curriculumExps = new HashSet<>();

        List<Curriculum> curricula;

        //按名字搜索
        CurriculumExample example = new CurriculumExample();
        example.createCriteria().andNameLike("%" + keyword + "%");
        curricula = curriculumMapper.selectByExample(example);
        for (Curriculum curriculum : curricula) {
            CurriculumExp curriculumById = getCurriculumById(curriculum.getId());
            curriculumExps.add(curriculumById);
        }

        //按年级搜索
        example.clear();
        example.createCriteria().andGradeLike("%" + keyword + "%");
        curricula = curriculumMapper.selectByExample(example);
        for (Curriculum curriculum : curricula) {
            CurriculumExp curriculumById = getCurriculumById(curriculum.getId());
            curriculumExps.add(curriculumById);
        }
        ArrayList<CurriculumExp> curriculumExpList = new ArrayList<>(curriculumExps);

        curriculumPageDto.countTotalPage(curriculumExpList.size(), limit);
        curriculumPageDto.countPreAndAfter(page);

        curriculumPageDto.setElements(curriculumExpList);

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
