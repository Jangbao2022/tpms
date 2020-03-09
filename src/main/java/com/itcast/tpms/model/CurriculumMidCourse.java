package com.itcast.tpms.model;

import java.util.Date;

public class CurriculumMidCourse {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column curriculum_mid_course.id
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column curriculum_mid_course.course_id
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    private Long courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column curriculum_mid_course.curriculum_id
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    private Long curriculumId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column curriculum_mid_course.gmt_created
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    private Date gmtCreated;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column curriculum_mid_course.gmt_modified
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    private Date gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column curriculum_mid_course.teach
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    private Integer teach;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column curriculum_mid_course.experiment
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    private Integer experiment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column curriculum_mid_course.practice
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    private Integer practice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column curriculum_mid_course.oac
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    private Integer oac;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column curriculum_mid_course.semester
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    private String semester;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column curriculum_mid_course.type
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    private Integer type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column curriculum_mid_course.id
     *
     * @return the value of curriculum_mid_course.id
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column curriculum_mid_course.id
     *
     * @param id the value for curriculum_mid_course.id
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column curriculum_mid_course.course_id
     *
     * @return the value of curriculum_mid_course.course_id
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column curriculum_mid_course.course_id
     *
     * @param courseId the value for curriculum_mid_course.course_id
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column curriculum_mid_course.curriculum_id
     *
     * @return the value of curriculum_mid_course.curriculum_id
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public Long getCurriculumId() {
        return curriculumId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column curriculum_mid_course.curriculum_id
     *
     * @param curriculumId the value for curriculum_mid_course.curriculum_id
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column curriculum_mid_course.gmt_created
     *
     * @return the value of curriculum_mid_course.gmt_created
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column curriculum_mid_course.gmt_created
     *
     * @param gmtCreated the value for curriculum_mid_course.gmt_created
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column curriculum_mid_course.gmt_modified
     *
     * @return the value of curriculum_mid_course.gmt_modified
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column curriculum_mid_course.gmt_modified
     *
     * @param gmtModified the value for curriculum_mid_course.gmt_modified
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column curriculum_mid_course.teach
     *
     * @return the value of curriculum_mid_course.teach
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public Integer getTeach() {
        return teach;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column curriculum_mid_course.teach
     *
     * @param teach the value for curriculum_mid_course.teach
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setTeach(Integer teach) {
        this.teach = teach;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column curriculum_mid_course.experiment
     *
     * @return the value of curriculum_mid_course.experiment
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public Integer getExperiment() {
        return experiment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column curriculum_mid_course.experiment
     *
     * @param experiment the value for curriculum_mid_course.experiment
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setExperiment(Integer experiment) {
        this.experiment = experiment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column curriculum_mid_course.practice
     *
     * @return the value of curriculum_mid_course.practice
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public Integer getPractice() {
        return practice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column curriculum_mid_course.practice
     *
     * @param practice the value for curriculum_mid_course.practice
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setPractice(Integer practice) {
        this.practice = practice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column curriculum_mid_course.oac
     *
     * @return the value of curriculum_mid_course.oac
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public Integer getOac() {
        return oac;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column curriculum_mid_course.oac
     *
     * @param oac the value for curriculum_mid_course.oac
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setOac(Integer oac) {
        this.oac = oac;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column curriculum_mid_course.semester
     *
     * @return the value of curriculum_mid_course.semester
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public String getSemester() {
        return semester;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column curriculum_mid_course.semester
     *
     * @param semester the value for curriculum_mid_course.semester
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setSemester(String semester) {
        this.semester = semester == null ? null : semester.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column curriculum_mid_course.type
     *
     * @return the value of curriculum_mid_course.type
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column curriculum_mid_course.type
     *
     * @param type the value for curriculum_mid_course.type
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }
}