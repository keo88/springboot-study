package com.keokim.playground.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.keokim.playground.base.alias.item.Item;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;

	public Item save(Item item) {
		if (item.getId() == null) {
			em.persist(item);
		} else {
			em.merge(item);
		}

		return item;
	}

	public Optional<Item> findById(Long id) {
		return Optional.ofNullable(em.find(Item.class, id));
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}
}
