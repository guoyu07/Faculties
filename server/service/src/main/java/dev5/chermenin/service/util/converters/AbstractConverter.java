package dev5.chermenin.service.util.converters;

import dev5.chermenin.model.entity.BaseObj;
import dev5.chermenin.service.dto.Dto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ancarian on 24.12.2017.
 */
public abstract class AbstractConverter<T extends Dto, K extends BaseObj> implements Converter<T, K> {

    @Override
    public List<K> convertToEntity(List<T> dtoList) {
        return dtoList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<T> convertToDto(List<K> objectList) {
        return objectList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
