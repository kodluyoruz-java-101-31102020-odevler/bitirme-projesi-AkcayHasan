package com.spring.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.aspect.MethodRunningTime;
import com.spring.entity.Book;
import com.spring.jpa.repository.BookRepository;
import com.spring.service.BookService;

@Service
//@Scope(value = "singleton")  Default olarak geldiği singleton için yazmaya gerek yok.
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	/*
	@Transactional
	public Long save(BookModel book) {
		
		Book books= new Book();
		books.setBookId(book.getId());
		books.setName(book.getName());
		books.setAuthorrName(book.getAuthor());
		books.setPublishDate(book.getPublishDate());
		books.setDescription(book.getDescription());
		books = bookRepository.save(books);
		
		return books.getBookId();
	}
	*/
	@Transactional
	public Book save(Book book) {
		if (book != null) {
			return bookRepository.save(book);
		} else {
			return null;
		}
	}
	@MethodRunningTime(active = true)
	public List<Book> getAll() {
		return (List<Book>) bookRepository.findAll();
	}

	@Transactional
	public void delete(Long bookId) {
		if (bookId != null) {
			bookRepository.deleteById(bookId);
			System.out.println("Kayıt Silindi");
		} else {
			System.out.println("İşlem Başarısız!");
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Book> findWithBookName(String bookName) {
		if (bookName != null) {
			return (List<Book>) bookRepository.findWithBookName(bookName);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Book> findWithBookId(Long bookId) {
		if (bookId != null) {
			return (List<Book>) bookRepository.findWithBookId(bookId);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public long update(Book book) {
		
		Book bookUpdate = bookRepository.findWithBookId(book.getBookId());
		if(bookUpdate == null) {
			throw new RuntimeException(book.getBookId() + " ID not found in DB!");
		}
		bookUpdate.setName(book.getName());
		bookUpdate.setBookId(book.getBookId());
		bookUpdate.setPublishDate(book.getPublishDate());
		bookUpdate.setDescription(book.getDescription());
		bookUpdate.setAuthor(book.getAuthor());
		
		bookRepository.save(bookUpdate);
		
		return bookUpdate.getBookId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> search(String bookName) {
		
		List<Book> results = null;
		if (bookName != null && (bookName.trim().length() > 0)) {
			results = (List<Book>) bookRepository.findWithBookName(bookName);
		} else {
			results = findWithBookName(bookName);
		}

		return results;
	}

}
