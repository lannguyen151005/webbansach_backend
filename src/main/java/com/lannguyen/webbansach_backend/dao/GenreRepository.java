package com.lannguyen.webbansach_backend.dao;

import com.lannguyen.webbansach_backend.entity.Genre;
import com.lannguyen.webbansach_backend.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
