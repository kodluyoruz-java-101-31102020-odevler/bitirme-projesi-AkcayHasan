package com.spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	
	@Query(value = "SELECT e FROM Book e")
	public List<Book> getAll();

	@Query(value = "SELECT e FROM Book e WHERE e.name = :book_name")
	public Book findWithBookName(@Param("book_name") String bookName);

	@Query(value = "SELECT e FROM Book e WHERE e.bookId = :book_id")
	public Book findWithBookId(@Param("book_id") Long bookId);

}
