package com.popov.transaction.repository;

import com.popov.transaction.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByIdAndUserId(Long id, Long userId);

}
