package com.itcast.tpms.service.moduleService;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.exp.ModuleExp;
import com.itcast.tpms.model.Module;

import java.util.List;

/**
 * 专业service
 */
public interface IModuleService {

    PageDto<Module> getModuleBySearchDto(SearchDto searchDto);

    List<Module> getAllOneLevelModule();

    List<Module> getAllTwoLevelModule();

    boolean addModule(Module module);

    boolean updateModule(Module module);

    boolean addOrUpdateModule(Module module);

    boolean deleteModuleById(Long moduleId);

    ModuleExp getModuleById(Long moduleId);

}
