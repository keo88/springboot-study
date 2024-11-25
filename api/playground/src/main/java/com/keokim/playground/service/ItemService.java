package com.keokim.playground.service;

import java.util.List;

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

	public List<Item> findItems(long id) {
		return itemRepository.findAll();
	}

	public Item findById(long id) {
		return itemRepository.findOne(id);
	}

}
