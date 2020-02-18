package com.itcast.tpms.service.majorService.impl;

import com.itcast.tpms.mapper.CourseMapper;
import com.itcast.tpms.mapper.MajorMapper;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.CourseExample;
import com.itcast.tpms.model.Major;
import com.itcast.tpms.model.MajorExample;
import com.itcast.tpms.service.majorService.IMajorService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements IMajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public List<Major> getMajorByPage(Integer page, Integer limit) {

        List<Major> majors = majorMapper.selectByExampleWithRowbounds(new MajorExample(), new RowBounds(page, limit));
        return majors;
    }

    @Override
    public boolean addMajor(Major major) {
        return false;
    }

    @Override
    public boolean updateMajor(Major major) {
        return false;
    }

    @Override
    public boolean deleteMajor(Long majorId) {
        return false;
    }
}
