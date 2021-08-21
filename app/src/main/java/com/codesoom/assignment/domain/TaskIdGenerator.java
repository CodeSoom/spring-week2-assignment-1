package com.codesoom.assignment.domain;

import com.codesoom.assignment.exceptions.NotSupportedIdTypeException;
import org.springframework.stereotype.Component;

@Component
public class TaskIdGenerator implements IdGenerator<Long>{
    public static final Long DEFAULT_INCREASE_SEQUENCE = 1L;

    @Override
    public Long generate(Object source) {
        if(!(source instanceof  Long)){
            throw new NotSupportedIdTypeException(source.getClass().getName(), Long.class.getName());
        }
        return (Long)source + DEFAULT_INCREASE_SEQUENCE;
    }
}
