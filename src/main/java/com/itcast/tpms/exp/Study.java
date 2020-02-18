package com.itcast.tpms.exp;

import com.itcast.tpms.enums.CourseTypeEnum;
import com.itcast.tpms.model.Course;
import lombok.Data;

/**
 * 学分学时管理类
 */

@Data
public class Study {

    //总共必修学分
    private Integer totalObligatoryCredit = 0;
    //总共必修学时
    private Integer totalObligatoryClassHour = 0;
    //总共选修学分
    private Integer totalElectiveCredit = 0;
    //总共选修学时
    private Integer totalElectiveClassHour = 0;

    //学习课程
    public void studyDoCourse(Course course) {
        this.totalObligatoryClassHour += (course.getType().equals(CourseTypeEnum.OBLIGATORY.getType()) ? course.getClassHour() : 0);
        this.totalObligatoryCredit += (course.getType().equals(CourseTypeEnum.OBLIGATORY.getType()) ? course.getCredit() : 0);
        this.totalElectiveClassHour += (course.getType().equals(CourseTypeEnum.ELECTIVE.getType()) ? course.getClassHour() : 0);
        this.totalElectiveCredit += (course.getType().equals(CourseTypeEnum.ELECTIVE.getType()) ? course.getCredit() : 0);
    }

    /**
     * 让前端获取值
     *
     * @return
     */
    public String beString() {
        return totalObligatoryCredit + " "
                + totalObligatoryClassHour + " "
                + totalElectiveCredit + " "
                + totalElectiveClassHour;
    }
}
