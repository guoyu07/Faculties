package dev5.chermenin.service.util.converters;

import java.util.List;

/**
 * Created by Ancarian on 02.12.2017.
 */

public interface Converter<T, K> {
    T convertToDto(K baseObj);

    K convertToEntity(T dto);

    List<K> convertToEntity(List<T> dtoList);

    List<T> convertToDto(List<K> objectList);
}
