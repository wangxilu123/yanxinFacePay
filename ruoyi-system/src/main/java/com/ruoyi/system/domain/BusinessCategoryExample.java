package com.ruoyi.system.domain;

import java.util.ArrayList;
import java.util.List;

public class BusinessCategoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BusinessCategoryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameIsNull() {
            addCriterion("one_category_name is null");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameIsNotNull() {
            addCriterion("one_category_name is not null");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameEqualTo(String value) {
            addCriterion("one_category_name =", value, "oneCategoryName");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameNotEqualTo(String value) {
            addCriterion("one_category_name <>", value, "oneCategoryName");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameGreaterThan(String value) {
            addCriterion("one_category_name >", value, "oneCategoryName");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("one_category_name >=", value, "oneCategoryName");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameLessThan(String value) {
            addCriterion("one_category_name <", value, "oneCategoryName");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("one_category_name <=", value, "oneCategoryName");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameLike(String value) {
            addCriterion("one_category_name like", value, "oneCategoryName");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameNotLike(String value) {
            addCriterion("one_category_name not like", value, "oneCategoryName");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameIn(List<String> values) {
            addCriterion("one_category_name in", values, "oneCategoryName");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameNotIn(List<String> values) {
            addCriterion("one_category_name not in", values, "oneCategoryName");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameBetween(String value1, String value2) {
            addCriterion("one_category_name between", value1, value2, "oneCategoryName");
            return (Criteria) this;
        }

        public Criteria andOneCategoryNameNotBetween(String value1, String value2) {
            addCriterion("one_category_name not between", value1, value2, "oneCategoryName");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeIsNull() {
            addCriterion("one_category_code is null");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeIsNotNull() {
            addCriterion("one_category_code is not null");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeEqualTo(String value) {
            addCriterion("one_category_code =", value, "oneCategoryCode");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeNotEqualTo(String value) {
            addCriterion("one_category_code <>", value, "oneCategoryCode");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeGreaterThan(String value) {
            addCriterion("one_category_code >", value, "oneCategoryCode");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("one_category_code >=", value, "oneCategoryCode");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeLessThan(String value) {
            addCriterion("one_category_code <", value, "oneCategoryCode");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeLessThanOrEqualTo(String value) {
            addCriterion("one_category_code <=", value, "oneCategoryCode");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeLike(String value) {
            addCriterion("one_category_code like", value, "oneCategoryCode");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeNotLike(String value) {
            addCriterion("one_category_code not like", value, "oneCategoryCode");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeIn(List<String> values) {
            addCriterion("one_category_code in", values, "oneCategoryCode");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeNotIn(List<String> values) {
            addCriterion("one_category_code not in", values, "oneCategoryCode");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeBetween(String value1, String value2) {
            addCriterion("one_category_code between", value1, value2, "oneCategoryCode");
            return (Criteria) this;
        }

        public Criteria andOneCategoryCodeNotBetween(String value1, String value2) {
            addCriterion("one_category_code not between", value1, value2, "oneCategoryCode");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameIsNull() {
            addCriterion("two_category_name is null");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameIsNotNull() {
            addCriterion("two_category_name is not null");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameEqualTo(String value) {
            addCriterion("two_category_name =", value, "twoCategoryName");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameNotEqualTo(String value) {
            addCriterion("two_category_name <>", value, "twoCategoryName");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameGreaterThan(String value) {
            addCriterion("two_category_name >", value, "twoCategoryName");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("two_category_name >=", value, "twoCategoryName");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameLessThan(String value) {
            addCriterion("two_category_name <", value, "twoCategoryName");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("two_category_name <=", value, "twoCategoryName");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameLike(String value) {
            addCriterion("two_category_name like", value, "twoCategoryName");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameNotLike(String value) {
            addCriterion("two_category_name not like", value, "twoCategoryName");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameIn(List<String> values) {
            addCriterion("two_category_name in", values, "twoCategoryName");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameNotIn(List<String> values) {
            addCriterion("two_category_name not in", values, "twoCategoryName");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameBetween(String value1, String value2) {
            addCriterion("two_category_name between", value1, value2, "twoCategoryName");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryNameNotBetween(String value1, String value2) {
            addCriterion("two_category_name not between", value1, value2, "twoCategoryName");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeIsNull() {
            addCriterion("two_category_code is null");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeIsNotNull() {
            addCriterion("two_category_code is not null");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeEqualTo(String value) {
            addCriterion("two_category_code =", value, "twoCategoryCode");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeNotEqualTo(String value) {
            addCriterion("two_category_code <>", value, "twoCategoryCode");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeGreaterThan(String value) {
            addCriterion("two_category_code >", value, "twoCategoryCode");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("two_category_code >=", value, "twoCategoryCode");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeLessThan(String value) {
            addCriterion("two_category_code <", value, "twoCategoryCode");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeLessThanOrEqualTo(String value) {
            addCriterion("two_category_code <=", value, "twoCategoryCode");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeLike(String value) {
            addCriterion("two_category_code like", value, "twoCategoryCode");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeNotLike(String value) {
            addCriterion("two_category_code not like", value, "twoCategoryCode");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeIn(List<String> values) {
            addCriterion("two_category_code in", values, "twoCategoryCode");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeNotIn(List<String> values) {
            addCriterion("two_category_code not in", values, "twoCategoryCode");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeBetween(String value1, String value2) {
            addCriterion("two_category_code between", value1, value2, "twoCategoryCode");
            return (Criteria) this;
        }

        public Criteria andTwoCategoryCodeNotBetween(String value1, String value2) {
            addCriterion("two_category_code not between", value1, value2, "twoCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameIsNull() {
            addCriterion("three_category_name is null");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameIsNotNull() {
            addCriterion("three_category_name is not null");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameEqualTo(String value) {
            addCriterion("three_category_name =", value, "threeCategoryName");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameNotEqualTo(String value) {
            addCriterion("three_category_name <>", value, "threeCategoryName");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameGreaterThan(String value) {
            addCriterion("three_category_name >", value, "threeCategoryName");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("three_category_name >=", value, "threeCategoryName");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameLessThan(String value) {
            addCriterion("three_category_name <", value, "threeCategoryName");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("three_category_name <=", value, "threeCategoryName");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameLike(String value) {
            addCriterion("three_category_name like", value, "threeCategoryName");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameNotLike(String value) {
            addCriterion("three_category_name not like", value, "threeCategoryName");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameIn(List<String> values) {
            addCriterion("three_category_name in", values, "threeCategoryName");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameNotIn(List<String> values) {
            addCriterion("three_category_name not in", values, "threeCategoryName");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameBetween(String value1, String value2) {
            addCriterion("three_category_name between", value1, value2, "threeCategoryName");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryNameNotBetween(String value1, String value2) {
            addCriterion("three_category_name not between", value1, value2, "threeCategoryName");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeIsNull() {
            addCriterion("three_category_code is null");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeIsNotNull() {
            addCriterion("three_category_code is not null");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeEqualTo(String value) {
            addCriterion("three_category_code =", value, "threeCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeNotEqualTo(String value) {
            addCriterion("three_category_code <>", value, "threeCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeGreaterThan(String value) {
            addCriterion("three_category_code >", value, "threeCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("three_category_code >=", value, "threeCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeLessThan(String value) {
            addCriterion("three_category_code <", value, "threeCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeLessThanOrEqualTo(String value) {
            addCriterion("three_category_code <=", value, "threeCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeLike(String value) {
            addCriterion("three_category_code like", value, "threeCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeNotLike(String value) {
            addCriterion("three_category_code not like", value, "threeCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeIn(List<String> values) {
            addCriterion("three_category_code in", values, "threeCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeNotIn(List<String> values) {
            addCriterion("three_category_code not in", values, "threeCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeBetween(String value1, String value2) {
            addCriterion("three_category_code between", value1, value2, "threeCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThreeCategoryCodeNotBetween(String value1, String value2) {
            addCriterion("three_category_code not between", value1, value2, "threeCategoryCode");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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