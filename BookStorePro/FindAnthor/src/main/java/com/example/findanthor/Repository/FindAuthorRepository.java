package com.example.findanthor.Repository;

import com.example.findanthor.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FindAuthorRepository extends JpaRepository<Book,Integer> {
    @Query("SELECT author FROM Book WHERE name = :name")
    String FindAuthor(@Param("name") String name);
}
