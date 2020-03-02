package com.itcast.tpms.exp;

import lombok.Data;

import java.util.List;

/**
 * 处理方案中模块的学分和学时
 */
@Data
public class ModuleClass {

    private List<String> names;

    private List<Integer> classHours;

    private List<Integer> credits;
}
