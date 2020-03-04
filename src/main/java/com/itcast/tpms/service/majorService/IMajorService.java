package com.itcast.tpms.service.majorService;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.dto.SearchDto;
import com.itcast.tpms.model.Major;

import java.util.List;

/**
 * 专业service
 */
public interface IMajorService {

    PageDto<Major> getMajorBySearchDto(SearchDto searchDto);

    List<Major> getAllMajor();

    boolean addMajor(Major major);

    boolean updateMajor(Major major);

    boolean addOrUpdateMajor(Major major);

    boolean deleteMajorById(Long majorId);

    Major getMajorById(Long majorId);

    boolean checkDeleteById(Long majorId);
}
