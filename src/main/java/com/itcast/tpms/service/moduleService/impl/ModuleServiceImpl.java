package com.itcast.tpms.service.moduleService.impl;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.enums.PageUrlEnum;
import com.itcast.tpms.exp.ModuleExp;
import com.itcast.tpms.mapper.CourseMapper;
import com.itcast.tpms.mapper.MajorMapper;
import com.itcast.tpms.mapper.ModuleMapper;
import com.itcast.tpms.model.*;
import com.itcast.tpms.service.moduleService.IModuleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public PageDto<Module> getModuleBySearchDto(SearchDto searchDto) {
        PageDto<Module> modulePageDto = new PageDto<>(PageUrlEnum.MODULE_URL.getUrl());

        ModuleExample example = new ModuleExample();
        if (searchDto.getKeyword() != null && !"null".equals(searchDto.getKeyword())) {
            example.createCriteria().andNameLike("%" + searchDto.getKeyword() + "%");
        }

        searchDto.setTotal((int) moduleMapper.countByExample(example));
        modulePageDto.init(searchDto);
        searchDto.setOffset((modulePageDto.getPage() - 1) * searchDto.getLimit());

        List<Module> modules = moduleMapper.selectByExampleWithRowbounds(example, new RowBounds(searchDto.getOffset(), searchDto.getLimit()));

        modulePageDto.setElements(modules);

        return modulePageDto;
    }

    @Override
    public List<Module> getAllOneLevelModule() {
        return getAllLevelModule(1);
    }

    private List<Module> getAllLevelModule(Integer level) {
        ModuleExample example = new ModuleExample();
        example.createCriteria().andRankEqualTo(level);
        List<Module> modules = moduleMapper.selectByExample(example);
        return modules;
    }

    @Override
    public List<Module> getAllTwoLevelModule() {
        return getAllLevelModule(2);
    }
    //    @Override
//    public List<Module> getAllModule() {
//        return moduleMapper.selectByExample(new ModuleExample());
//    }

    @Override
    public ModuleExp getModuleById(Long moduleId) {
        ModuleExp moduleExp = new ModuleExp();
        Module module = moduleMapper.selectByPrimaryKey(moduleId);
        List<Course> courses = courseMapper.selectByExample(new CourseExample());
        Set<Long> courseIds = new HashSet<>();
        List<Course> containsCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getModuleId().equals(moduleId)) {
                courseIds.add(course.getId());
                containsCourses.add(course);
            }
        }
        moduleExp.setModule(module);
        moduleExp.setCourseIds(courseIds);
        moduleExp.setContainsCourses(containsCourses);
        return moduleExp;
    }

    @Override
    public boolean addModule(Module module) {
        module.setGmtCreated(new Date());
        module.setGmtModified(module.getGmtCreated());
        int insert = moduleMapper.insert(module);
        return insert == 1;
    }

    @Override
    public boolean updateModule(Module module) {
        module.setGmtModified(new Date());
        int i = moduleMapper.updateByPrimaryKeySelective(module);
        return i == 1;
    }

    @Override
    public boolean addOrUpdateModule(Module module) {
        if (module.getId() == null) {
            return addModule(module);
        } else {
            return updateModule(module);
        }
    }

    @Override
    public boolean deleteModuleById(Long moduleId) {
        int delete = moduleMapper.deleteByPrimaryKey(moduleId);
        return delete == 1;
    }
}
