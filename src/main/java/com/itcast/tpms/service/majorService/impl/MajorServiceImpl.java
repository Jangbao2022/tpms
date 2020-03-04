package com.itcast.tpms.service.majorService.impl;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.enums.PageUrlEnum;
import com.itcast.tpms.mapper.CurriculumMapper;
import com.itcast.tpms.mapper.MajorMapper;
import com.itcast.tpms.mapper.UserMapper;
import com.itcast.tpms.model.CurriculumExample;
import com.itcast.tpms.model.Major;
import com.itcast.tpms.model.MajorExample;
import com.itcast.tpms.model.UserExample;
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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CurriculumMapper curriculumMapper;

    @Override
    public PageDto<Major> getMajorBySearchDto(SearchDto searchDto) {
        PageDto<Major> majorPageDto = new PageDto<>(PageUrlEnum.MAJOR_URL.getUrl());

        MajorExample example = new MajorExample();
        if (searchDto.getKeyword() != null && !"null".equals(searchDto.getKeyword())) {
            example.createCriteria().andNameLike("%" + searchDto.getKeyword() + "%");
        }

        searchDto.setTotal((int) majorMapper.countByExample(example));
        majorPageDto.init(searchDto);
        searchDto.setOffset((majorPageDto.getPage() - 1) * searchDto.getLimit());

        List<Major> majors = majorMapper.selectByExampleWithRowbounds(example, new RowBounds(searchDto.getOffset(), searchDto.getLimit()));

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
        int i = majorMapper.updateByPrimaryKeySelective(major);
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

    @Override
    public boolean checkDeleteById(Long majorId) {
        CurriculumExample example1 = new CurriculumExample();
        example1.createCriteria().andMajorIdEqualTo(majorId);
        long l = curriculumMapper.countByExample(example1);
        if (l != 0) {
            return false;
        }
        UserExample example = new UserExample();
        example.createCriteria().andMajorIdEqualTo(majorId);
        long count = userMapper.countByExample(example);
        return count == 0;
    }
}
