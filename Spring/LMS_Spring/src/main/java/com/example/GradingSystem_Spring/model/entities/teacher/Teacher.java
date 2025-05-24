package com.example.GradingSystem_Spring.model.entities.teacher;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    public Teacher(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
