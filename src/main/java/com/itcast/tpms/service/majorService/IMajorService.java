package com.itcast.tpms.service.majorService;

import com.itcast.tpms.model.Major;

import java.util.List;

/**
 * 专业service
 */
public interface IMajorService {

    List<Major> getMajorByPage(Integer page, Integer limit);

    boolean addMajor(Major major);

    boolean updateMajor(Major major);

    boolean deleteMajor(Long majorId);

}
