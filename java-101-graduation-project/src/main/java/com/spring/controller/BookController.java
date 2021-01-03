package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.entity.Book;
import com.spring.service.BookService;

@Controller
@RequestMapping(value = "/book", method = {RequestMethod.GET})
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getBooks(Model model) {

		
		List<Book> books = bookService.getAll();
		System.out.println(books);
		model.addAttribute("books", books);

		return "book_list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBooks(Model model, @Validated Book book, BindingResult bindingResult) {

		if(bindingResult.hasErrors()){
	         return "book_list";
	     }
		
		bookService.save(book);

		return "book_save";
	}

	
	@RequestMapping(value = "/delete/{bookId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("bookId") Long bookId) {
		
		bookService.delete(bookId);
		
		return "book_list";
	}
	
	
	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
	public String findWithId(@PathVariable("bookId") Long bookId) {
		
		bookService.findWithBookId(bookId);
		
		return "book_list";
	}
	
	@RequestMapping(value = "/update/{bookId}", method = RequestMethod.PUT)
	public String update(@PathVariable("bookId") Book bookId) {
		
		bookService.update(bookId);
		
		return "book_save";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("name") String bookName, Model model) {

		List<Book> books = bookService.search(bookName);

		model.addAttribute("books", bookName);

		return "book_list";
	}
}


