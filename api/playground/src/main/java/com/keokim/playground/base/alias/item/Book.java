package com.keokim.playground.base.alias.item;

import com.keokim.playground.base.dto.BookForm;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("B")
public class Book extends Item {

	private String author;
	private String isbn;

	public static Book of(BookForm form) {
		Book book = new Book();
		book.setId(form.getId());
		book.setName(form.getName());
		book.setPrice(form.getPrice());
		book.setStockQuantity(form.getStockQuantity());
		book.setIsbn(form.getIsbn());
		book.setAuthor(form.getAuthor());

		return book;
	}
}
