package com.example.keywordalarmappbackend.config;

import com.example.keywordalarmappbackend.auth.JwtAuthenticationFilter;
import com.example.keywordalarmappbackend.auth.OAuth2UserService;
import com.example.keywordalarmappbackend.auth.OAuthSuccessHandler;
import com.example.keywordalarmappbackend.auth.RedirectUrlCookieFilter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
  private OAuth2UserService oAuthUserService;

  @Autowired
  private OAuthSuccessHandler oAuthSuccessHandler;

  @Autowired
  private RedirectUrlCookieFilter redirectUrlCookieFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        .configurationSource(corsConfigurationSource())
        .and()
        .csrf()
        .disable()
        .httpBasic()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/", "/auth/**", "/oauth2/**", "/h2-console/**").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .oauth2Login()
        .redirectionEndpoint()
        .baseUri("/oauth2/callback/*")
        .and()
        .authorizationEndpoint()
        .baseUri("/auth/authorize")
        .and()
        .userInfoEndpoint()
        .userService(oAuthUserService)
        .and()
        .successHandler(oAuthSuccessHandler)
        .and()
        .headers().frameOptions().disable()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new Http403ForbiddenEntryPoint());

    http.addFilterAfter(
        jwtAuthenticationFilter,
        CorsFilter.class
    );
    http.addFilterBefore(
        redirectUrlCookieFilter,
        OAuth2AuthorizationRequestRedirectFilter.class
    );
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOriginPatterns(List.of("*"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
