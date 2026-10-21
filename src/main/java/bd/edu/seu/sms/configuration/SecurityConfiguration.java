package bd.edu.seu.sms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfiguration {

    private final CustomLoginSuccessHandler customLoginSuccessHandler;
    private final CustomAuthFailureHandler customAuthFailureHandler;

    public SecurityConfiguration(CustomLoginSuccessHandler customLoginSuccessHandler, CustomAuthFailureHandler customAuthFailureHandler) {
        this.customLoginSuccessHandler = customLoginSuccessHandler;
        this.customAuthFailureHandler = customAuthFailureHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                requests -> requests
                        .requestMatchers("/css/**", "/assets/**", "/scripts/**", "/fonts/**", "/js/**")
                        .permitAll()
                        .requestMatchers("/login", "/register","/home").permitAll()
                        .requestMatchers("/courses").hasAnyRole("STUDENT","ADMIN")
                        .anyRequest().hasRole("ADMIN")
                )
                .formLogin(
                form -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .failureHandler(customAuthFailureHandler)
                        //.failureForwardUrl("/login?error=true")
                        .successHandler(customLoginSuccessHandler)
                        //.defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )
                .logout(
                req -> req
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
        ).sessionManagement(session -> session
                        .invalidSessionUrl("/login?session=invalid")
                        .maximumSessions(1)
                ).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
