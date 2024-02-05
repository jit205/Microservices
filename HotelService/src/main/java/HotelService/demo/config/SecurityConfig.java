package HotelService.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {


        security
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();

        return security.build();


    }
    @Bean
    public JwtDecoder jwtDecoder() {
        // You can customize the JwtDecoder based on your needs.
        // For example, you can use OidcJwtDecoder or NimbusJwtDecoder.
        return JwtDecoders.fromOidcIssuerLocation("https://dev-55278743.okta.com/oauth2/default");
    }
}
