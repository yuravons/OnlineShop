package online.patologia.shop.model;

import online.patologia.shop.validators.ValidEmail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Collections;

@Entity
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotEmpty(message = "Імя не може бути пусте")
    @Size(min = 2, message = "Назва облікового запису занадто коротка")
    @Size(max = 30, message = "Назва облікового запису задовга")
    private String username;
    @NotEmpty(message = "Пароль не може бути порожнім")
    @NotBlank
    private String password;
    private String passwordMatcher;
    @ValidEmail(message = "Введіть дійсну пошту")
    @NotBlank
    @NotEmpty(message = "Пошта не можу бути пуста")
    private String email;
    private String role;


    public String getPasswordMatcher() {
        return passwordMatcher;
    }

    public void setPasswordMatcher(String passwordMatcher) {
        this.passwordMatcher = passwordMatcher;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public MyUser(@NotBlank @NotEmpty(message = "Імя не може бути пустим") @Size(min = 2, message = "Назва облікового запису занадто коротка") @Size(max = 30, message = "Назва облікового запису задовга") String username, @NotEmpty(message = "Пароль не може бути порожнім") @NotBlank String password, String passwordMatcher, @Email @NotBlank @NotEmpty(message = "Поле пошта не може бути пустим") String email, String role) {
        this.username = username;
        this.password = password;
        this.passwordMatcher = passwordMatcher;
        this.email = email;
        this.role = role;
    }

    public MyUser(@NotBlank @NotEmpty(message = "Імя не може бути пустим") @Size(min = 2, message = "Назва облікового запису занадто коротка") @Size(max = 30, message = "Назва облікового запису задовга") String username, @NotEmpty(message = "Пароль не може бути порожнім") @NotBlank String password, String passwordMatcher, @Email @NotBlank @NotEmpty(message = "Поле пошта не може бути пустим") String email) {
        this.username = username;
        this.password = password;
        this.passwordMatcher = passwordMatcher;
        this.email = email;
    }

    public MyUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return Collections.singleton(new SimpleGrantedAuthority(role));
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
