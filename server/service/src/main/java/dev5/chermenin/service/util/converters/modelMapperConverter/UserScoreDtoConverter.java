package dev5.chermenin.service.util.converters.modelMapperConverter;

import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.dto.impl.user.UserScoreDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

public class UserScoreDtoConverter extends ConverterConfigurerSupport<User, UserScoreDto> {
    @Override
    public Converter<User, UserScoreDto> converter() {

        return new AbstractConverter<User, UserScoreDto>() {
            @Override
            protected UserScoreDto convert(User source) {
                UserScoreDto userScoreDto = new UserScoreDto();

                userScoreDto.setId(source.getId());
                userScoreDto.setScore(source.getSubjects().values().stream().mapToInt(Integer::intValue).sum());
                return userScoreDto;
            }
        };
    }
}

