package com.southdragon.book_ecommerce.config;

import com.southdragon.book_ecommerce.exception.CustomAccessDeniedHandler;
import com.southdragon.book_ecommerce.exception.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // 🔹 Auth APIs: Public
                        .requestMatchers("/api/auth/**").permitAll()

                        // 🔹 Public GET (ai cũng xem được)
                        .requestMatchers(HttpMethod.GET,
                                "/api/categories/**",
                                "/api/authors/**",
                                "/api/publishers/**",
                                "/api/books/**"
                        ).permitAll()

                        // 🔹 Chỉ STAFF hoặc ADMIN được thêm/sửa/xóa
                        .requestMatchers(HttpMethod.POST,
                                "/api/categories/**",
                                "/api/authors/**",
                                "/api/publishers/**",
                                "/api/books/**"
                        ).hasAnyRole("STAFF", "ADMIN")

                        .requestMatchers(HttpMethod.PUT,
                                "/api/categories/**",
                                "/api/authors/**",
                                "/api/publishers/**",
                                "/api/books/**"
                        ).hasAnyRole("STAFF", "ADMIN")

                        .requestMatchers(HttpMethod.DELETE,
                                "/api/categories/**",
                                "/api/authors/**",
                                "/api/publishers/**",
                                "/api/books/**"
                        ).hasAnyRole("STAFF", "ADMIN")

                        // 🔹 Admin-only APIs
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // 🔹 User APIs
                        .requestMatchers("/api/user/**").hasAnyRole("CUSTOMER", "ADMIN")

                        // 🔹 Còn lại: yêu cầu xác thực
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(customAccessDeniedHandler)
                        .authenticationEntryPoint(customAuthenticationEntryPoint))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

