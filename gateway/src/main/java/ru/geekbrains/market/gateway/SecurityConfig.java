package ru.geekbrains.market.gateway;

//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;

//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
public class SecurityConfig {
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange()
//                .pathMatchers("/user/api/v1/register").permitAll()
//                .pathMatchers("/auth/**").permitAll()
////                .anyExchange().authenticated()
//                .anyExchange().permitAll()
//                .and()
//                .csrf().disable()
//                .oauth2Login()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//        return http.build();
//    }
}
