package com.smartnews.rest.mapper;

import com.smartnews.model.ModelEntity;
import com.smartnews.rest.dto.NamedDto;

import java.util.List;
import java.util.stream.Collectors;

public interface RestMapper<T extends NamedDto, E extends ModelEntity> {
    T mapToDto(E entity);

    default List<T> mapToDtos(List<E> entities) {
        return entities.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    E mapToEntity(T dto);

    default List<E> mapToEntities(List<T> dtos) {
        return dtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }
}
