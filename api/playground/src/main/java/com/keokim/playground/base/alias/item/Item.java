package com.keokim.playground.base.alias.item;

import java.util.ArrayList;
import java.util.List;

import com.keokim.playground.base.alias.Category;
import com.keokim.playground.exception.NotEnoughStockException;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private Integer price;
	private Integer stockQuantity;

	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();

	public void addStock(int quantity) {
		stockQuantity += quantity;
	}

	public void removeStock(int quantity) {
		int remainingStock = stockQuantity - quantity;

		if (remainingStock < 0) {
			throw new NotEnoughStockException("Need more stock for item name " + name);
		}

		stockQuantity = remainingStock;
	}
}
