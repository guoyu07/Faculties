package dev5.chermenin.rest;

import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import dev5.chermenin.service.dto.impl.user.UserScoreDto;
import dev5.chermenin.service.util.converters.modelMapperConverter.GroupDtoConverter;
import dev5.chermenin.service.util.converters.modelMapperConverter.UserDtoConverter;
import dev5.chermenin.service.util.converters.modelMapperConverter.UserScoreDtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"dev5.chermenin"})
@EnableTransactionManagement
@EntityScan(basePackages = {"dev5.chermenin.model.entity.impl"})
@EnableJpaRepositories(basePackages = {"dev5.chermenin.dao.repository"})
@EnableWebMvc
public class Application extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, (String[]) args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(User.class, UserDto.class).setConverter(new UserDtoConverter(modelMapper).converter());
        modelMapper.createTypeMap(Group.class, GroupDto.class).setConverter(new GroupDtoConverter(modelMapper).converter());
        modelMapper.createTypeMap(User.class, UserScoreDto.class).setConverter(new UserScoreDtoConverter().converter());
        return modelMapper;
    }

    @Bean
    int bootstrap(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {

        List<User> users = userRepository.findAll();
        users.forEach(o -> o.getInfo().setPassword(passwordEncoder.encode(o.getInfo().getPassword())));
        userRepository.save(users);

        return 1;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
