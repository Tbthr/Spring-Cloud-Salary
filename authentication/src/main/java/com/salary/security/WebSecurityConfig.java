package com.salary.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salary.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;


/**
 * spring security 的配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security全局注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserService userService;
    @Resource
    private MyAccessDecisionManager myAccessDecisionManager;
    @Resource
    private MySecurityMetadataSource mySecurityMetadataSource;
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Resource
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 放行特定URL
        web.ignoring().antMatchers("/forget", "/sendMail",
                "/swagger-ui**", "/doc**", "/swagger-resources/**", "/webjars/**", "/v2/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(myAccessDecisionManager);
                        object.setSecurityMetadataSource(mySecurityMetadataSource);
                        return object;
                    }
                })
                // 处理跨域请求中的Preflight请求
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // 除特定URL外，都需要验证权限
                .anyRequest().authenticated()
                .and()
                // 自定义 JWT 过滤器
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic()
                .and()
                // 无权限时的处理
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                .and()
                // 设置表单登陆
                .formLogin()
                .loginProcessingUrl("/login")
                // 设置成功、失败 跳转URL
                .successForwardUrl("/login/success")
                .failureForwardUrl("/login/failed")
                // 自定义登陆用户名和密码参数，默认为username和password
                .usernameParameter("id")
                .passwordParameter("psd")
                .and()
                // 跨域
                .cors()
                .and()
                // 登出
                .logout().permitAll();

        // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Bean
    // security 加密组件
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // 返回前端 object to string 组件
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
