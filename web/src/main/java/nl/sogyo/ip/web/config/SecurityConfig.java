package nl.sogyo.ip.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/login", "/api/auth/register", "/h2-console/**").permitAll() // allow unauthenticated access        
                    .anyRequest().authenticated()   
                 )
                 .cors(cors -> cors.configure(http)) // enable CORS
                 .csrf(csrf -> csrf.disable()) // disable CSRF
                 .headers(headers -> headers
                 .frameOptions(frame -> frame.disable())); // allow H2 console frames
        return http.build();
    }


//         @Bean
//     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/admin/**").hasRole("ADMIN")
//                 .requestMatchers("/user/**").hasRole("USER")
//                 .requestMatchers("/").permitAll())
//             .formLogin(login -> login
//                 .loginPage("/login")
//                 .permitAll())
//             .logout(logout -> logout
//                 .permitAll());
//         return http.build();
//     }
// }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {
    //     return (web) -> web.ignoring()
    //             .requestMatchers("/h2-console");
    // }
}
