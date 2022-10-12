package com.ZenPack.repository;

import com.ZenPack.model.ZenPack;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
@RequiredArgsConstructor
public class ZenPackSpecRepo {
    @Autowired
    private ZenPackRepository repo;

    public List<ZenPack> getQueryResult(List<Filter> filters){
        if(filters.size()>0) {
            return repo.findAll((Sort) getSpecificationFromFilters(filters));
        }else {
            return repo.findAll();
        }
    }

    private Specification<ZenPack> getSpecificationFromFilters(List<Filter> filter) {
        Specification<ZenPack> specification = where(createSpecification(filter.remove(0)));
        for (Filter input : filter) {
            specification = specification.and(createSpecification(input));
        }
        return specification;
    }

    private Specification<ZenPack> createSpecification(Filter input) {
        return switch (input.getOperator()) {
//            case CONTAINS:
//                return ((root, query, criteriaBuilder) ->
//                        criteriaBuilder.isTrue(root.get(input.getField()),
//                                castToRequiredType(root.get(input.getField()).getJavaType(),input.getValue()));
//            case NOT_CONTAINS:
//                return ((root, query, criteriaBuilder) ->
//                        criteriaBuilder.isEmpty(root.get(input.getField()),
//                                castToRequiredType(root.get(input.getField()).getJavaType(),input.getValue()));
            case EQUALS -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(input.getField()),
                            castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case NOT_EQUAL -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.notEqual(root.get(input.getField()),
                            castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case GREATER_THAN -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.gt(root.get(input.getField()),
                            (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case LESS_THAN -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.lt(root.get(input.getField()),
                            (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
//            case STARTS_WITH:
//                return (root, query, criteriaBuilder) ->
//                        criteriaBuilder.like(root.get(input.getField()),
//                                (java.lang.String) castToRequiredType(root.get(input.getField()).getJavaType(),input.getValue()));
//            case ENDS_WITH:
//                return (root, query, criteriaBuilder) ->
//                        criteriaBuilder.lt(root.get(input.getField()),
//                                castToRequiredType(root.get(input.getField().getJavaType()),input.getValue()));
//            case BLANKS:
//                return (root, query, criteriaBuilder) ->
//                        criteriaBuilder.isFalse(root.get(input.getField()),
//                                castToRequiredType(root.get(input.getField().get()),input.getValue()));
//            case NOT_BLANKS:
//                return (root, query, criteriaBuilder) ->
//                        criteriaBuilder.isNotEmpty(root.get(input.getField()),
//                                castToRequiredType(root.get(input.getField().getJavaType()),input.getValue()));
            case IN_RANGE -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.in(root.get(input.getField()))
                            .value(castToRequiredType(root.get(input.getField()).getJavaType(), input.getValues()));
            default -> throw new RuntimeException("Operation not supported yet");
        };
    }

    private Object castToRequiredType(Class fieldType,String value) {
        if(fieldType.isAssignableFrom(Integer.class)){
            return Integer.valueOf(value);
        }else if(Enum.class.isAssignableFrom(fieldType)){
            return Enum.valueOf(fieldType, value);
        }
        return null;
    }

    private Object castToRequiredType(Class fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }
}
