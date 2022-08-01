package fr.fms.apitrainings.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    String usersByUsernameQuery = "select email, password, enable from users where email = ?";
    String authoritiesByUsernameQuery = "SELECT u.email, r.name from users AS u \r\n"
            + "INNER JOIN users_roles ur ON u.id = ur.users_id \r\n"
            + "INNER JOIN role r ON ur.roles_id = r.id \r\n"
            + "where email = ?";

    public String encodePassword(String password) {
        PasswordEncoder passwordEncoder = passwordEncoder();
        return passwordEncoder.encode(password);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(usersByUsernameQuery)
                .authoritiesByUsernameQuery(authoritiesByUsernameQuery).rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/trainings").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/categorie/{id}/trainings").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/trainingImage/{id}").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/categories").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/category/{id}").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/login").permitAll();
////        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/signin").permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
