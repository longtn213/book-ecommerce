package com.southdragon.book_ecommerce.repository;

import com.southdragon.book_ecommerce.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByCategories_Id(Long categoryId);
}
