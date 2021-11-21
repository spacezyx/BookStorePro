package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query("select b from Book b")
    List<Book> getBooks();

    @Query("select b.bookId from Book b WHERE b.tag LIKE %?1%")
    Set<Integer> getByTags(@Param("tag") String tag);

    @Query("SELECT count(*) FROM Book")
    Integer getBookNumber();

    @Query("SELECT sum(inventory) FROM Book")
    Integer getBookInventorySum();

    @Query("SELECT inventory FROM Book where bookId = :id")
    Integer getBookInventory(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Book SET  inventory = (inventory- :num) WHERE bookId = :id")
    void decreaseInventory(@Param("num") Integer num,@Param("id") Integer id);
}
