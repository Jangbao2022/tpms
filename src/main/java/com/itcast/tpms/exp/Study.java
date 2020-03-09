package com.itcast.tpms.exp;

import com.itcast.tpms.enums.CourseTypeEnum;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.Curriculum;
import lombok.Data;

/**
 * 学分学时管理类
 */

@Data
public class Study {

    //总共必修学分
    private Float totalObligatoryCredit = 10.f;
    //缺少必修学分
    private Float lackObligatoryCredit = 0.f;

    //总共必修学时
    private Integer totalObligatoryClassHour = 10;
    //缺少必修学分
    private Integer lackObligatoryClassHour = 0;

    //总共选修学分
    private Float totalElectiveCredit = 10.f;
    //缺少选修学分
    private Float lackElectiveCredit = 0.f;

    //总共选修学时
    private Integer totalElectiveClassHour = 10;
    //缺少选修学时
    private Integer lackElectiveClassHour = 0;

    //学习课程
    public void studyDoCourse(Course course) {
        this.totalObligatoryClassHour += (course.getType().equals(CourseTypeEnum.OBLIGATORY.getType()) ? course.getClassHour() : 0);
        this.totalObligatoryCredit += (course.getType().equals(CourseTypeEnum.OBLIGATORY.getType()) ? course.getCredit() : 0);
        this.totalElectiveClassHour += (course.getType().equals(CourseTypeEnum.ELECTIVE.getType()) ? course.getClassHour() : 0);
        this.totalElectiveCredit += (course.getType().equals(CourseTypeEnum.ELECTIVE.getType()) ? course.getCredit() : 0);
    }

    public void setLack(Curriculum curriculum) {
        //缺少必修学时
        int lackOCH = curriculum.getNeedObligatoryClassHour() - this.totalObligatoryClassHour;
        if (lackOCH > 0) {
            this.lackObligatoryClassHour = lackOCH;
        }
        //缺少选修学时
        int lackECH = curriculum.getNeedElectiveClassHour() - this.totalElectiveClassHour;
        if (lackECH > 0) {
            this.lackElectiveClassHour = lackECH;
        }
        //缺少必修学分
        float lackOC = curriculum.getNeedObligatoryCredit() - this.totalObligatoryCredit;
        if (lackOC > 0) {
            this.lackObligatoryCredit = lackOC;
        }
        //缺少必修学分
        float lackEC = curriculum.getNeedElectiveCredit() - this.totalElectiveCredit;
        if (lackEC > 0) {
            this.lackElectiveCredit = lackEC;
        }
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
                + totalElectiveClassHour + " " +

                +lackObligatoryCredit + " " +
                +lackObligatoryClassHour + " " +
                +lackElectiveCredit + " " +
                +lackElectiveClassHour + " ";
    }
}
