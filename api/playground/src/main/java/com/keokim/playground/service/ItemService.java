package com.keokim.playground.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keokim.playground.base.alias.item.Book;
import com.keokim.playground.base.alias.item.Item;
import com.keokim.playground.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional
	public long saveItem(Item item) {
		itemRepository.save(item);
		return item.getId();
	}

	public List<Item> findItems() {
		return itemRepository.findAll();
	}

	public Optional<Item> findById(long id) {
		return itemRepository.findById(id);
	}

	@Transactional
	public void updateItem(Book book) {

		Book fetchedBook = (Book) findById(book.getId())
			.orElseThrow(() -> new IllegalArgumentException("Invalid item Id: " + book.getId()));

		updateIfNull(book.getName(), fetchedBook::setName);
		updateIfNull(book.getPrice(), fetchedBook::setPrice);
		updateIfNull(book.getStockQuantity(), fetchedBook::setStockQuantity);
		updateIfNull(book.getIsbn(), fetchedBook::setIsbn);
		updateIfNull(book.getAuthor(), fetchedBook::setAuthor);
	}

	private <T> void updateIfNull(T value, Consumer<T> setter) {
		if (value != null) {
			setter.accept(value);
		}
	}
}
