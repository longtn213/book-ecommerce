package com.southdragon.book_ecommerce.repository;

import com.southdragon.book_ecommerce.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
