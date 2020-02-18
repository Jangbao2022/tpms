package com.itcast.tpms.service.curriculumSerivce.impl;

import com.itcast.tpms.enums.PageLimitEnum;
import com.itcast.tpms.exp.CurriculumExp;
import com.itcast.tpms.mapper.CurriculumMapper;
import com.itcast.tpms.mapper.MajorMapper;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.Curriculum;
import com.itcast.tpms.model.CurriculumExample;
import com.itcast.tpms.model.Major;
import com.itcast.tpms.service.curriculumSerivce.ICurriculumService;
import com.itcast.tpms.utils.currmidUtil.impl.CurrmidUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurriculumServiceImpl implements ICurriculumService {

    @Autowired
    private CurriculumMapper curriculumMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private CurrmidUtil currmidUtil;

    @Override
    public List<CurriculumExp> getCurriculumByPage(Integer page, Integer limit) {
        List<CurriculumExp> currExps = new ArrayList<>();
        List<Curriculum> currs = curriculumMapper.selectByExampleWithRowbounds(new CurriculumExample(), new RowBounds(page, PageLimitEnum.CURRICULUM_LIMIT.getLimit()));
        for (Curriculum curr : currs) {
            //加入普通的方案
            CurriculumExp currExp = new CurriculumExp();
            currExp.setCurr(curr);
            //加入专业
            Major major = majorMapper.selectByPrimaryKey(curr.getMajorId());
            currExp.setMajor(major);

            //加入课程集和学分
            currmidUtil.pottingCurrExp(currExp);

            //将扩展的课程方案加入集合
            currExps.add(currExp);
        }
        return currExps;
    }

    @Override
    public boolean addCurriculum(Curriculum curriculum) {
        return false;
    }

    @Override
    public boolean updateCurriculum(Curriculum curriculum) {
        return false;
    }

    @Override
    public boolean deleteCurriculum(Long curriculumId) {
        return false;
    }
}
