package com.cyberoxi.hstpfacilities.configurations.security;


import com.cyberoxi.hstpfacilities.components.Accesses;
import com.cyberoxi.hstpfacilities.repositories.CredentialRepository;
import com.cyberoxi.hstpfacilities.services.LoginService;
import com.cyberoxi.hstpfacilities.services.UserDetailsServiceImpl;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author Mohamad Zarei Maram
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/30/2020
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CredentialRepository credentialRepository;
    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private LoginService loginService;
    private Accesses accesses;

    @Autowired
    public SecurityConfig(CredentialRepository credentialRepository, UserDetailsServiceImpl userDetailsService,
                          BCryptPasswordEncoder bCryptPasswordEncoder, LoginService loginService, Accesses accesses) {
        this.credentialRepository = credentialRepository;
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.loginService = loginService;
        this.accesses = accesses;
    }

    /**
     * configuration of rest endpoints
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), loginService))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), credentialRepository))
                .addFilter(jwtAuthorizationFilter())
                .authorizeRequests()
                .antMatchers("/login", "/").permitAll()
                // TODO: 3/11/2020 to add a SUPER ADMIN we need to comment line below
                .antMatchers("/web/admins/register").hasAuthority("SA")
                .antMatchers("/mng/**").hasAuthority("SA");


        /*for (AccessLevel accessLevel : accesses.getAccessLevels()) {
            Map<String, List<String>> methodUrls = new HashMap<>();
            for (UrlMethod urlMethod : accessLevel.getUrlMethods()) {
                for (String method : urlMethod.getMethods()) {
                    if (methodUrls.containsKey(method)) {
                        methodUrls.get(method).add(urlMethod.getUrl());
                        methodUrls.replace(method, methodUrls.get(method));
                    } else {
                        methodUrls.put(method, new ArrayList<String>() {{
                            add(urlMethod.getUrl());
                        }});
                    }
                }
            }
            for (Map.Entry<String, List<String>> entry : methodUrls.entrySet()) {
                http.authorizeRequests().antMatchers(HttpMethod.valueOf(entry.getKey()), entry.getValue().toArray(new String[0])).hasAuthority(accessLevel.getRole());
                log.info("Role " + accessLevel.getRole() + " and Method " + entry.getKey() + " permit this urls: " + entry.getValue().toString());
            }
        }*/

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/web/admins/**").hasAnyAuthority("C0", "A0", "A1", "A2")
                .antMatchers(HttpMethod.POST, "/web/admins/**").hasAuthority("SA")

                .antMatchers(HttpMethod.GET, "/web/establishments/unit/**").hasAnyAuthority("C0", "A0", "A1", "A2")
                .antMatchers(HttpMethod.GET, "/web/establishments/**").hasAnyAuthority("A0", "A1", "A2")
                .antMatchers(HttpMethod.POST, "/web/establishments/unit/**").hasAnyAuthority("A0", "A1")
                .antMatchers(HttpMethod.POST, "/web/establishments/**").hasAnyAuthority("A0")

                .antMatchers(HttpMethod.GET, "/web/facilities/fields").hasAnyAuthority("C0", "A0", "A1", "A2")
                .antMatchers(HttpMethod.GET, "/web/facilities/unit/**").hasAnyAuthority("C0", "A0", "A1", "A2")
                .antMatchers(HttpMethod.GET, "/web/facilities/**").hasAnyAuthority("A0", "A1", "A2")
                .antMatchers(HttpMethod.POST, "/web/facilities/unit/**").hasAnyAuthority("A0", "A1")
                .antMatchers(HttpMethod.POST, "/web/facilities/**").hasAnyAuthority("A0")

                .antMatchers(HttpMethod.GET, "/web/ideas/fields").hasAnyAuthority("C0", "A0", "A1", "A2")
                .antMatchers(HttpMethod.GET, "/web/ideas/unit/**").hasAnyAuthority("C0", "A0", "A1", "A2")
                .antMatchers(HttpMethod.GET, "/web/ideas/**").hasAnyAuthority("A0", "A1", "A2")
                .antMatchers(HttpMethod.POST, "/web/ideas/unit/**").hasAnyAuthority("A0", "A1")
                .antMatchers(HttpMethod.POST, "/web/ideas/**").hasAnyAuthority("A0")

                .antMatchers(HttpMethod.GET, "/web/payments").hasAnyAuthority("A0")
                .antMatchers(HttpMethod.POST, "/web/payments").hasAnyAuthority("A0")
                .antMatchers(HttpMethod.GET, "/web/payments/{id}").hasAnyAuthority("C0", "A0", "A1", "A2")
                .antMatchers(HttpMethod.GET, "/web/payments/unit/{id}").hasAnyAuthority("C0", "A0", "A1", "A2")

                .antMatchers(HttpMethod.GET, "/web/units").hasAnyAuthority("A0", "A1", "A2")
                .antMatchers(HttpMethod.POST, "/web/units").hasAnyAuthority("A0", "A1")
                .antMatchers(HttpMethod.GET, "/web/units/{id}").hasAnyAuthority("C0", "A0", "A1", "A2")
                .antMatchers(HttpMethod.POST, "/web/units/{id}").hasAnyAuthority("A0")
                .antMatchers(HttpMethod.GET, "/web/units/brief").hasAnyAuthority("A0", "A1", "A2")
                .antMatchers(HttpMethod.GET, "/web/units/fields").hasAnyAuthority("C0", "A0", "A1", "A2")
                .antMatchers(HttpMethod.GET, "/web/units/report/{id}").hasAnyAuthority("C0", "A0", "A1", "A2");

        /*http
                .authorizeRequests()
                // TODO: 3/4/2020 if we want to have basic auth just for first page we have to delete ** from second parameter
                .antMatchers("/swagger-resources/**", "/swagger-ui.html**", "/webjars/**", "/v2/api-docs")
                .hasAuthority("SA").and().httpBasic();*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    private JwtAuthenticationFilter jwtAuthorizationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager(), loginService);
        jwtAuthenticationFilter.setFilterProcessesUrl("/web/login");
        return jwtAuthenticationFilter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("GET", "POST", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setExposedHeaders(ImmutableList.of("Authorization"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
