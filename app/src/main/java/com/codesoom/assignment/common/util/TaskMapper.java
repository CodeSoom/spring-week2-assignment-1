package com.codesoom.assignment.common.util;

import com.codesoom.assignment.domain.dtos.TaskDTO;
import com.codesoom.assignment.domain.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "task", source = "task")
    TaskDTO toDTO(Task entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "task", source = "task")
    Task toEntity(TaskDTO dto);
}
