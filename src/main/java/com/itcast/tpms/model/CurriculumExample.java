package com.itcast.tpms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurriculumExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public CurriculumExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCurrentIsNull() {
            addCriterion("current is null");
            return (Criteria) this;
        }

        public Criteria andCurrentIsNotNull() {
            addCriterion("current is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentEqualTo(Integer value) {
            addCriterion("current =", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentNotEqualTo(Integer value) {
            addCriterion("current <>", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentGreaterThan(Integer value) {
            addCriterion("current >", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentGreaterThanOrEqualTo(Integer value) {
            addCriterion("current >=", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentLessThan(Integer value) {
            addCriterion("current <", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentLessThanOrEqualTo(Integer value) {
            addCriterion("current <=", value, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentIn(List<Integer> values) {
            addCriterion("current in", values, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentNotIn(List<Integer> values) {
            addCriterion("current not in", values, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentBetween(Integer value1, Integer value2) {
            addCriterion("current between", value1, value2, "current");
            return (Criteria) this;
        }

        public Criteria andCurrentNotBetween(Integer value1, Integer value2) {
            addCriterion("current not between", value1, value2, "current");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(String value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(String value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(String value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(String value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(String value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(String value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLike(String value) {
            addCriterion("grade like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotLike(String value) {
            addCriterion("grade not like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<String> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<String> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(String value1, String value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(String value1, String value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryClassHourIsNull() {
            addCriterion("need_obligatory_class_hour is null");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryClassHourIsNotNull() {
            addCriterion("need_obligatory_class_hour is not null");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryClassHourEqualTo(Integer value) {
            addCriterion("need_obligatory_class_hour =", value, "needObligatoryClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryClassHourNotEqualTo(Integer value) {
            addCriterion("need_obligatory_class_hour <>", value, "needObligatoryClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryClassHourGreaterThan(Integer value) {
            addCriterion("need_obligatory_class_hour >", value, "needObligatoryClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryClassHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_obligatory_class_hour >=", value, "needObligatoryClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryClassHourLessThan(Integer value) {
            addCriterion("need_obligatory_class_hour <", value, "needObligatoryClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryClassHourLessThanOrEqualTo(Integer value) {
            addCriterion("need_obligatory_class_hour <=", value, "needObligatoryClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryClassHourIn(List<Integer> values) {
            addCriterion("need_obligatory_class_hour in", values, "needObligatoryClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryClassHourNotIn(List<Integer> values) {
            addCriterion("need_obligatory_class_hour not in", values, "needObligatoryClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryClassHourBetween(Integer value1, Integer value2) {
            addCriterion("need_obligatory_class_hour between", value1, value2, "needObligatoryClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryClassHourNotBetween(Integer value1, Integer value2) {
            addCriterion("need_obligatory_class_hour not between", value1, value2, "needObligatoryClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryCreditIsNull() {
            addCriterion("need_obligatory_credit is null");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryCreditIsNotNull() {
            addCriterion("need_obligatory_credit is not null");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryCreditEqualTo(Integer value) {
            addCriterion("need_obligatory_credit =", value, "needObligatoryCredit");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryCreditNotEqualTo(Integer value) {
            addCriterion("need_obligatory_credit <>", value, "needObligatoryCredit");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryCreditGreaterThan(Integer value) {
            addCriterion("need_obligatory_credit >", value, "needObligatoryCredit");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryCreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_obligatory_credit >=", value, "needObligatoryCredit");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryCreditLessThan(Integer value) {
            addCriterion("need_obligatory_credit <", value, "needObligatoryCredit");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryCreditLessThanOrEqualTo(Integer value) {
            addCriterion("need_obligatory_credit <=", value, "needObligatoryCredit");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryCreditIn(List<Integer> values) {
            addCriterion("need_obligatory_credit in", values, "needObligatoryCredit");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryCreditNotIn(List<Integer> values) {
            addCriterion("need_obligatory_credit not in", values, "needObligatoryCredit");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryCreditBetween(Integer value1, Integer value2) {
            addCriterion("need_obligatory_credit between", value1, value2, "needObligatoryCredit");
            return (Criteria) this;
        }

        public Criteria andNeedObligatoryCreditNotBetween(Integer value1, Integer value2) {
            addCriterion("need_obligatory_credit not between", value1, value2, "needObligatoryCredit");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveClassHourIsNull() {
            addCriterion("need_elective_class_hour is null");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveClassHourIsNotNull() {
            addCriterion("need_elective_class_hour is not null");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveClassHourEqualTo(Integer value) {
            addCriterion("need_elective_class_hour =", value, "needElectiveClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveClassHourNotEqualTo(Integer value) {
            addCriterion("need_elective_class_hour <>", value, "needElectiveClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveClassHourGreaterThan(Integer value) {
            addCriterion("need_elective_class_hour >", value, "needElectiveClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveClassHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_elective_class_hour >=", value, "needElectiveClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveClassHourLessThan(Integer value) {
            addCriterion("need_elective_class_hour <", value, "needElectiveClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveClassHourLessThanOrEqualTo(Integer value) {
            addCriterion("need_elective_class_hour <=", value, "needElectiveClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveClassHourIn(List<Integer> values) {
            addCriterion("need_elective_class_hour in", values, "needElectiveClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveClassHourNotIn(List<Integer> values) {
            addCriterion("need_elective_class_hour not in", values, "needElectiveClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveClassHourBetween(Integer value1, Integer value2) {
            addCriterion("need_elective_class_hour between", value1, value2, "needElectiveClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveClassHourNotBetween(Integer value1, Integer value2) {
            addCriterion("need_elective_class_hour not between", value1, value2, "needElectiveClassHour");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveCreditIsNull() {
            addCriterion("need_elective_credit is null");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveCreditIsNotNull() {
            addCriterion("need_elective_credit is not null");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveCreditEqualTo(Integer value) {
            addCriterion("need_elective_credit =", value, "needElectiveCredit");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveCreditNotEqualTo(Integer value) {
            addCriterion("need_elective_credit <>", value, "needElectiveCredit");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveCreditGreaterThan(Integer value) {
            addCriterion("need_elective_credit >", value, "needElectiveCredit");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveCreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_elective_credit >=", value, "needElectiveCredit");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveCreditLessThan(Integer value) {
            addCriterion("need_elective_credit <", value, "needElectiveCredit");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveCreditLessThanOrEqualTo(Integer value) {
            addCriterion("need_elective_credit <=", value, "needElectiveCredit");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveCreditIn(List<Integer> values) {
            addCriterion("need_elective_credit in", values, "needElectiveCredit");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveCreditNotIn(List<Integer> values) {
            addCriterion("need_elective_credit not in", values, "needElectiveCredit");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveCreditBetween(Integer value1, Integer value2) {
            addCriterion("need_elective_credit between", value1, value2, "needElectiveCredit");
            return (Criteria) this;
        }

        public Criteria andNeedElectiveCreditNotBetween(Integer value1, Integer value2) {
            addCriterion("need_elective_credit not between", value1, value2, "needElectiveCredit");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNull() {
            addCriterion("major_id is null");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNotNull() {
            addCriterion("major_id is not null");
            return (Criteria) this;
        }

        public Criteria andMajorIdEqualTo(Long value) {
            addCriterion("major_id =", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotEqualTo(Long value) {
            addCriterion("major_id <>", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThan(Long value) {
            addCriterion("major_id >", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("major_id >=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThan(Long value) {
            addCriterion("major_id <", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThanOrEqualTo(Long value) {
            addCriterion("major_id <=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdIn(List<Long> values) {
            addCriterion("major_id in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotIn(List<Long> values) {
            addCriterion("major_id not in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdBetween(Long value1, Long value2) {
            addCriterion("major_id between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotBetween(Long value1, Long value2) {
            addCriterion("major_id not between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNull() {
            addCriterion("gmt_created is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNotNull() {
            addCriterion("gmt_created is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedEqualTo(Date value) {
            addCriterion("gmt_created =", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotEqualTo(Date value) {
            addCriterion("gmt_created <>", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThan(Date value) {
            addCriterion("gmt_created >", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_created >=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThan(Date value) {
            addCriterion("gmt_created <", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_created <=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIn(List<Date> values) {
            addCriterion("gmt_created in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotIn(List<Date> values) {
            addCriterion("gmt_created not in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedBetween(Date value1, Date value2) {
            addCriterion("gmt_created between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_created not between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table curriculum
     *
     * @mbg.generated do_not_delete_during_merge Mon Mar 09 20:21:53 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table curriculum
     *
     * @mbg.generated Mon Mar 09 20:21:53 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}