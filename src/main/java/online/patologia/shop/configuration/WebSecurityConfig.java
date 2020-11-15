package online.patologia.shop.configuration;

import online.patologia.shop.model.MyUser;
import online.patologia.shop.repo.UserRepo;
import online.patologia.shop.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailsService userDetailsService;
    private UserRepo userRepo;
    @Autowired
    public WebSecurityConfig(MyUserDetailsService userDetailsService, UserRepo userRepo) {
        this.userDetailsService = userDetailsService;
        this.userRepo=userRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("USER","ADMIN")
                .antMatchers("/product/edit/*").hasAnyRole("ADMIN")
                .antMatchers("/product/save").hasAnyRole("ADMIN")
                .antMatchers("/product/new").hasAnyRole("ADMIN")
                .antMatchers("/product/*").hasAnyRole("USER","ADMIN")
                .antMatchers("/product/delete/*").hasAnyRole("ADMIN")
                .antMatchers("/panel").hasRole("ADMIN")
                .antMatchers("/product/update").hasRole("ADMIN")
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/user/save").permitAll()
                .antMatchers("/contact").permitAll()
                .antMatchers("/feedback").permitAll()
                .antMatchers("/cart/shipment").permitAll()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .failureUrl("/login-error.html")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and().headers().frameOptions().disable()
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        MyUser appUserUser = new MyUser("yura", passwordEncoder().encode("yura"),"yura", "yura123@gmail.com","ROLE_ADMIN");
        userRepo.save(appUserUser);
        appUserUser = new MyUser("123", passwordEncoder().encode("123"),"123", "123123@gmail.com" ,"ROLE_USER");
        userRepo.save(appUserUser);
    }
}

