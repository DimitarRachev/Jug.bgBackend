package bg.jug.configuration;


import bg.jug.filter.CustomAuthenticationFilter;
import bg.jug.filter.CustomAuthorizationFilter;
import bg.jug.util.TokenGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    private final TokenGenerator tokenGenerator;
    private final AuthenticationConfiguration auth;


    public SecurityConfig(TokenGenerator tokenGenerator, AuthenticationConfiguration auth) {
        this.tokenGenerator = tokenGenerator;
        this.auth = auth;
    }


    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/refreshToken", "/topics")
                .permitAll()
                .antMatchers("/*")
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(auth), tokenGenerator));
        http.addFilterBefore(new CustomAuthorizationFilter(tokenGenerator), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder);
//        provider.setUserDetailsService(userService);
//        return provider;
//    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }
}
