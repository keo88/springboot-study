package com.keokim.playground.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
