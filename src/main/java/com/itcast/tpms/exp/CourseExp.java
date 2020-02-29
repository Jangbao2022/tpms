package com.itcast.tpms.exp;

import com.itcast.tpms.model.Course;
import lombok.Data;

@Data
public class CourseExp {

    private Course course;

    private String oneModuleName;

    private String twoModuleName;

    private Integer teach;

    private Integer experiment;

    private Integer practice;

    private Integer oac;

    private String semester;

}
