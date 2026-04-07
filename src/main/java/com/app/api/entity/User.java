package com.app.api.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is mandatory and cannot be empty")
    @Size(max = 50, message = "Username cannot exceed 50 characters")
    private String username;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is mandatory and cannot be empty")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Email(message = "Email must be valid")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory and cannot be empty")
    @Size(max = 255, message = "Password cannot exceed 255 characters")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, 
    orphanRemoval = true) /*if a review is deleted from Set<Review> in code, it will be deleted in db */
    private Set<Review> reviews;

    @ManyToMany
    @JoinTable(name = "user_movie", 
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies;
    
}