package com.keokim.playground.base.dto;

import com.keokim.playground.base.alias.item.Book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

	private Long id;

	private String name;
	private Integer price;
	private Integer stockQuantity;

	private String author;
	private String isbn;

	public static BookForm of(Book book) {
		BookForm bookForm = new BookForm();
		bookForm.setId(book.getId());
		bookForm.setName(book.getName());
		bookForm.setPrice(book.getPrice());
		bookForm.setStockQuantity(book.getStockQuantity());
		bookForm.setAuthor(book.getAuthor());
		bookForm.setIsbn(book.getIsbn());

		return bookForm;
	}
}
