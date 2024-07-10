package onlineFileStorage.root.app.FileStorage.securityConfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class FilterChain {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(config ->
                config.requestMatchers(HttpMethod.GET,"/client/test/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/client/user/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/client/secured/test/**").authenticated()

                )
                .addFilterBefore(filter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf(AbstractHttpConfigurer::disable);


        return http.build();
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtCustomFilter filter(){
        return new JwtCustomFilter();
    }

}
