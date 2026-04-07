package com.app.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.api.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}