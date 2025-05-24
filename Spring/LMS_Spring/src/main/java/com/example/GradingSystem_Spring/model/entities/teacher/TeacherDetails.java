package com.example.GradingSystem_Spring.model.entities.teacher;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@ToString
public class TeacherDetails implements UserDetails {
    private String name;
    private String pass;

    public TeacherDetails(Teacher teacher) {
        this.name = teacher.getName();
        this.pass = teacher.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("TEACHER"));
    }

    @Override
    public String getPassword() {
        return this.getPass();
    }

    @Override
    public String getUsername() {
        return this.getName();
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
}