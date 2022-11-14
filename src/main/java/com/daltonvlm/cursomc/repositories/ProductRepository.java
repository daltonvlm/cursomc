package com.daltonvlm.cursomc.repositories;

import com.daltonvlm.cursomc.domain.Category;
import com.daltonvlm.cursomc.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /*
    @Query("SELECT DISTINCT  obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat in :categories")
    Page<Product> findDistinctByNameContainingAndCategoriesIn(@Param("name") String name, @Param("categories") List<Category> categories, Pageable pageRequest);
     */
    @Transactional(readOnly = true)
    // Just a query, no need to do a transaction
    Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);
}
