package com.itcast.tpms.service.majorService.impl;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.enums.PageUrlEnum;
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

import java.util.Date;
import java.util.List;

@Service
public class MajorServiceImpl implements IMajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public PageDto<Major> getMajorByPage(Integer page, Integer limit) {
        PageDto<Major> majorPageDto = new PageDto<>(PageUrlEnum.MAJOR_URL.getUrl());
        long total = majorMapper.countByExample(new MajorExample());

        majorPageDto.countTotalPage((int) total, limit);
        majorPageDto.countPreAndAfter(page);

        int offset = (majorPageDto.getPage() - 1) * limit;
        List<Major> majors = majorMapper.selectByExampleWithRowbounds(new MajorExample(), new RowBounds(offset, limit));

        majorPageDto.setElements(majors);

        return majorPageDto;
    }

    @Override
    public List<Major> getAllMajor() {
        return majorMapper.selectByExample(new MajorExample());
    }

    @Override
    public Major getMajorById(Long majorId) {
        Major major = majorMapper.selectByPrimaryKey(majorId);
        return major;
    }

    @Override
    public boolean addMajor(Major major) {
        major.setGmtCreated(new Date());
        major.setGmtModified(major.getGmtCreated());
        int insert = majorMapper.insert(major);
        return insert == 1;
    }

    @Override
    public boolean updateMajor(Major major) {
        major.setGmtModified(new Date());
        int i = majorMapper.updateByPrimaryKey(major);
        return i == 1;
    }

    @Override
    public boolean addOrUpdateMajor(Major major) {
        if (major.getId() == null) {
            return addMajor(major);
        } else {
            return updateMajor(major);
        }
    }

    @Override
    public boolean deleteMajorById(Long majorId) {
        int delete = majorMapper.deleteByPrimaryKey(majorId);
        return delete == 1;
    }
}
