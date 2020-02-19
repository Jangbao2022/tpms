package com.itcast.tpms.service.majorService;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.model.Major;

import java.util.List;

/**
 * 专业service
 */
public interface IMajorService {

    PageDto<Major> getMajorByPage(Integer page, Integer limit);

    List<Major> getAllMajor();

    boolean addMajor(Major major);

    boolean updateMajor(Major major);

    boolean addOrUpdateMajor(Major major);

    boolean deleteMajorById(Long majorId);

    Major getMajorById(Long majorId);
}
