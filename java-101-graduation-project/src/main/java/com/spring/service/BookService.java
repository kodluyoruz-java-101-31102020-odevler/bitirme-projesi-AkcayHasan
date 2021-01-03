package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.spring.entity.Book;

@Service
public interface BookService {
	
	Book save(Book book);
	
	void delete(Long bookId);
	
	List<Book> getAll();
	
	List<Book> findWithBookName(String bookName);
	
	List<Book> findWithBookId(Long bookId);
	
	long update (Book book);
	
	public List<Book> search(String bookName);
	
	
}
