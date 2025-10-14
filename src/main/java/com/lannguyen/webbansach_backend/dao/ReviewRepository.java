package com.lannguyen.webbansach_backend.dao;

import com.lannguyen.webbansach_backend.entity.OrderDetail;
import com.lannguyen.webbansach_backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
