package com.trade.billing.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.beans.Customizer

@Configuration
class SecurityConfig<SecurityFilterChain> {
    @Autowired
    private val jwtFilter: JwtFilter? = null

    @Bean
    @Throws(Exception::class)
    open fun filterChain(http: HttpSecurity): DefaultSecurityFilterChain? {
        http
            .csrf{csrf->csrf.disable()}
            .cors(Customizer.withDefaults())
            .sessionManagement{sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
            .authorizeHttpRequests{authRequest->
                authRequest
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().aunthenticated()
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()


    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager? {
        return configuration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}