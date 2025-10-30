package com.lannguyen.webbansach_backend.dao;

import com.lannguyen.webbansach_backend.entity.Book;
import com.lannguyen.webbansach_backend.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Integer> {
    Page<Book> findByNameContaining(String name, Pageable pageable);
    Page<Book> findByGenreList_Id(int id, Pageable pageable);
    Page<Book> findByNameContainingAndGenreList_Id(String name, int id, Pageable pageable);
}
