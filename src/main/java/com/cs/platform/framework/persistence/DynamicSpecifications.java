package com.cs.platform.framework.persistence;

import com.cs.platform.framework.util.Collections3;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

/**
 * filter组装
 *
 * @author calvin
 */
public class DynamicSpecifications {

    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param filters     xx
     * @param entityClazz xx
     * @return xx
     */
    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz) {
        return new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (Collections3.isNotEmpty(filters)) {
                    List<Predicate> predicates = Lists.newArrayList();
                    for (SearchFilter filter : filters) {
                        // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                        String[] names = StringUtils.split(filter.fieldName, ".");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {
                            expression = expression.get(names[i]);
                        }
                        // logic operator
                        switch (filter.operator) {
                            case EQ:
                                predicates.add(builder.equal(expression, filter.value));
                                break;
                            case NEQ:
                                predicates.add(builder.notEqual(expression, filter.value));
                                break;
                            case LIKE:
                                predicates.add(builder.like(expression, "%" + filter.value + "%"));
                                break;
                            case LLIKE:
                                predicates.add(builder.like(expression, filter.value + "%"));
                                break;
                            case RLIKE:
                                predicates.add(builder.like(expression, "%" + filter.value));
                                break;
                            case GT:
                                predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                                break;
                            case LT:
                                predicates.add(builder.lessThan(expression, (Comparable) filter.value));
                                break;
                            case GTE:
                                predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                                break;
                            case LTE:
                                predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                                break;
                            case NOTNULL:
                                predicates.add(builder.and(expression.isNotNull()));
                                break;
                            default:
                                break;
                        }
                    }
                    // 将所有条件用 and 联合起来
                    if (predicates.size() > 0) {
                        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                }
                return builder.conjunction();
            }
        };
    }

    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param leftFilters  leftFilters
     * @param rightFilters rightFilters
     * @param entityClazz  entityClazz
     * @return xx
     */
    public static <T> Specification<T> or(final Collection<SearchFilter> leftFilters, final Collection<SearchFilter> rightFilters,
                                          final Class<T> entityClazz) {
        return new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (Collections3.isNotEmpty(leftFilters) && Collections3.isNotEmpty(rightFilters)) {
                    List<Predicate> leftPredicates = Lists.newArrayList();
                    for (SearchFilter filter : leftFilters) {
                        // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                        String[] names = StringUtils.split(filter.fieldName, ".");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {
                            expression = expression.get(names[i]);
                        }
                        // logic operator
                        switchFilter(builder, leftPredicates, filter, expression);
                    }
                    List<Predicate> rightPredicates = Lists.newArrayList();
                    for (SearchFilter filter : rightFilters) {
                        // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                        String[] names = StringUtils.split(filter.fieldName, ".");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {
                            expression = expression.get(names[i]);
                        }
                        // logic operator
                        switchFilter(builder, rightPredicates, filter, expression);
                    }
                    // 将所有条件用 and 联合起来
                    // if (predicates.size() > 0) {
                    Predicate leftPred = builder.and(leftPredicates.toArray(new Predicate[leftPredicates.size()]));
                    Predicate rightPred = builder.and(rightPredicates.toArray(new Predicate[rightPredicates.size()]));
                    return builder.or(leftPred, rightPred);
                    // }
                }
                return builder.conjunction();
            }

            private void switchFilter(CriteriaBuilder builder, List<Predicate> predicates, SearchFilter filter, Path expression) {
                switch (filter.operator) {
                    case EQ:
                        predicates.add(builder.equal(expression, filter.value));
                        break;
                    case NEQ:
                        predicates.add(builder.notEqual(expression, filter.value));
                        break;
                    case LIKE:
                        predicates.add(builder.like(expression, "%" + filter.value + "%"));
                        break;
                    case LLIKE:
                        predicates.add(builder.like(expression, filter.value + "%"));
                        break;
                    case RLIKE:
                        predicates.add(builder.like(expression, "%" + filter.value));
                        break;
                    case GT:
                        predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                        break;
                    case LT:
                        predicates.add(builder.lessThan(expression, (Comparable) filter.value));
                        break;
                    case GTE:
                        predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                        break;
                    case LTE:
                        predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                        break;
                    default:
                        break;
                }
            }
        };
    }

    /**
     * leftFilters和rightFilters为and关系，leftFilters里子条件为and关系，rightFilters里子条件为or关系 是实现搜索群组的需求而写的方法
     *
     * @param leftFilters  leftFilters
     * @param rightFilters rightFilters
     * @param entityClazz  entityClazz
     * @return Specification
     */
    public static <T> Specification<T> bothAndOr(final Collection<SearchFilter> leftFilters, final Collection<SearchFilter> rightFilters,
                                                 final Class<T> entityClazz) {
        return new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (Collections3.isNotEmpty(leftFilters) && Collections3.isNotEmpty(rightFilters)) {
                    List<Predicate> leftPredicates = Lists.newArrayList();
                    for (SearchFilter filter : leftFilters) {
                        // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                        String[] names = StringUtils.split(filter.fieldName, ".");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {
                            expression = expression.get(names[i]);
                        }
                        // logic operator
                        switchFilter(builder, leftPredicates, filter, expression);
                    }
                    List<Predicate> rightPredicates = Lists.newArrayList();
                    for (SearchFilter filter : rightFilters) {
                        // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                        String[] names = StringUtils.split(filter.fieldName, ".");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {
                            expression = expression.get(names[i]);
                        }
                        // logic operator
                        switchFilter(builder, rightPredicates, filter, expression);
                    }
                    // 将所有条件用 and 联合起来
                    // if (predicates.size() > 0) {
                    Predicate leftPred = builder.and(leftPredicates.toArray(new Predicate[leftPredicates.size()]));
                    Predicate rightPred = builder.or(rightPredicates.toArray(new Predicate[rightPredicates.size()]));
                    return builder.and(leftPred, rightPred);
                    // }
                }
                return builder.conjunction();
            }

            private void switchFilter(CriteriaBuilder builder, List<Predicate> predicates, SearchFilter filter, Path expression) {
                switch (filter.operator) {
                    case EQ:
                        predicates.add(builder.equal(expression, filter.value));
                        break;
                    case NEQ:
                        predicates.add(builder.notEqual(expression, filter.value));
                        break;
                    case LIKE:
                        predicates.add(builder.like(expression, "%" + filter.value + "%"));
                        break;
                    case LLIKE:
                        predicates.add(builder.like(expression, filter.value + "%"));
                        break;
                    case RLIKE:
                        predicates.add(builder.like(expression, "%" + filter.value));
                        break;
                    case GT:
                        predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                        break;
                    case LT:
                        predicates.add(builder.lessThan(expression, (Comparable) filter.value));
                        break;
                    case GTE:
                        predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                        break;
                    case LTE:
                        predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                        break;
                    default:
                        break;
                }
            }
        };
    }

}
