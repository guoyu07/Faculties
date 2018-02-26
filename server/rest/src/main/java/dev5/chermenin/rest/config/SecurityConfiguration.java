package dev5.chermenin.rest.config;

import dev5.chermenin.rest.security.handler.RestAccessDeniedHandler;
import dev5.chermenin.rest.security.handler.RestAuthenticationEntryPoint;
import dev5.chermenin.rest.security.service.JwtAuthenticationFilter;
import dev5.chermenin.rest.security.service.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Created by Ancarian on 20.12.2017.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String[] allowedUrlsForPost = new String[]{"/auth/login"};

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
    };

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService, JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    @Autowired
    public void configureAuthentication(final AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .authenticationProvider(this.jwtAuthenticationProvider)
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//     http.httpBasic().and().cors().and().csrf().disable();
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//                //GROUPS
//                .antMatchers(HttpMethod.GET, "/groups").permitAll()
//                .antMatchers(HttpMethod.POST, "/groups/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/groups/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/groups/**").hasAuthority("ADMIN")
//                //REQUESTS
//                .antMatchers("/requests", "/roles").hasAuthority("ADMIN")
//                //SUBJECTS
//                .antMatchers("/subjects/change_state/").hasAuthority("USER")
//                .antMatchers("/subjects").hasAuthority("ADMIN")
//                //GUEST
//                .antMatchers("/guest").permitAll()
//                //USERS
//                .antMatchers("/users/**").authenticated()
//                // .antMatchers("/users/**").hasAuthority("USER")
//                //STATISTIC
//                .antMatchers("/statistic/**").authenticated().and()
//                .addFilterAfter(new JwtAuthenticationFilter(authenticationManagerBean()),
//                        BasicAuthenticationFilter.class)
//                .exceptionHandling()
//                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
//                .accessDeniedHandler(new RestAccessDeniedHandler());
//        //.antMatchers("/swagger-ui.html").permitAll();

        http.csrf().disable().

        sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST)
                .permitAll()
                .and()
                .csrf().disable()
                .addFilterAfter(new JwtAuthenticationFilter(authenticationManagerBean()),
                        BasicAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(new RestAccessDeniedHandler());
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.POST, allowedUrlsForPost)
                .antMatchers(AUTH_WHITELIST)
                .antMatchers(HttpMethod.GET,"/groups/**")
                .antMatchers(HttpMethod.GET, "/groups/{\\d+}");
    }

}
