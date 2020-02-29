package com.itcast.tpms.exp;

import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.Curriculum;
import com.itcast.tpms.model.Major;
import com.itcast.tpms.model.Module;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 课程方案扩展
 */

@Data
public class ModuleExp {

    //普通课程方案
    private Module module;

    //
    private Set<Long> courseIds;

    //含有课程集合
    private List<Course> containsCourses;

}
