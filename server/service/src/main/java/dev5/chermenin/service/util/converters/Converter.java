package dev5.chermenin.service.util.converters;

import dev5.chermenin.model.entity.BaseObject;
import dev5.chermenin.service.dto.Dto;

import java.util.List;

/**
 * Created by Ancarian on 02.12.2017.
 */

public interface Converter<T extends Dto, K extends BaseObject> {
    T convertToDto(K baseObj);

    K convertToEntity(T dto);

    List<K> convertToEntity(List<T> dtoList);

    List<T> convertToDto(List<K> objectList);
}
